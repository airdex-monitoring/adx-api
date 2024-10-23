package kz.hq.airdex.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import kz.hq.airdex.data.dto.request.MapSectorQuery;
import kz.hq.airdex.data.entity.LatLngPoint;
import kz.hq.airdex.data.entity.MapSector;
import kz.hq.airdex.data.entity.query.MapSectorAvg;
import kz.hq.airdex.data.repository.MapSectorRepository;
import kz.hq.airdex.data.repository.MapSectorStatsRepository;
import kz.hq.airdex.service.AirQualityIndexProvider;
import kz.hq.airdex.service.MapSectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MapSectorServiceImpl implements MapSectorService {
    private final MapSectorRepository sectorRepository;
    private final MapSectorStatsRepository sectorStatsRepository;

    private final AirQualityIndexProvider airQualityIndexProvider;

    @Override
    public List<MapSector> getAll() {
        return sectorRepository.findAll(
            Sort.by(Direction.ASC, "code"));
    }

    @Override
    public List<MapSectorAvg> getAllWithAvg(MapSectorQuery query) {
        return Optional.of(query)
            .filter(q -> q.getStartDate() != null || q.getEndDate() != null)
            .map(q -> sectorStatsRepository.findAllWithAvg(q.getStartDate(), q.getEndDate()))
            .orElse(sectorStatsRepository.findAllWithAvg())
            .stream()
            .peek(sector -> {
                var aqiLevel = airQualityIndexProvider.getAqiLevel(sector.getAqiAvg());
                sector.setAqiLevel(aqiLevel);
            })
            .toList();
    }

    @Override
    public Optional<MapSector> determineSector(LatLngPoint point) {
        if (point == null) {
            return Optional.empty();
        }

        return sectorRepository.findAll().stream()
            .filter(applyRayCasting(point))
            .findFirst();
    }

    private Predicate<MapSector> applyRayCasting(LatLngPoint point) {
        return sector -> {
            var points = sector.getPoints();
            int vertices = points.size();
            int crossings = 0;

            for (int i = 0; i < vertices; i++) {
                var p1 = points.get(i);
                var p2 = points.get((i + 1) % vertices);

                if (isPointIntersecting(point, p1, p2)) {
                    crossings++;
                }
            }

            return crossings % 2 == 1;
        };
    }

    /**
     * Determines if a point is intersecting with a line polygon formed by two other points.
     * <p>
     * The method checks two conditions:
     * 1. Whether the point's latitude is between the latitudes of the polygon's endpoints.
     * 2. Whether the point is to the left of the line polygon based on its longitude.
     *
     * @param point {@link LatLngPoint} The point to test for intersection.
     * @param p1 {@link LatLngPoint} The first endpoint of the line segment.
     * @param p2 {@link LatLngPoint} The second endpoint of the line segment.
     * @return {@code true} if the point is considered intersecting with the line segment, false otherwise.
     */
    private boolean isPointIntersecting(LatLngPoint point, LatLngPoint p1, LatLngPoint p2) {
        if (p1 == null || p2 == null) {
            throw new IllegalArgumentException("Line point appears to be null");
        }

        if (p1.getLat() > p2.getLat()) {
            LatLngPoint temp = p1;
            p1 = p2;
            p2 = temp;
        }

        if (point.getLat().equals(p1.getLat()) || point.getLat().equals(p2.getLat())) {
            point = new LatLngPoint(point.getLat() + 0.00001, point.getLon());
        }

        if (p1.getLat().equals(p2.getLat())) {
            return false;
        }

        final var isLatitudeBetween = p1.getLat() <= point.getLat() && point.getLat() < p2.getLat();
        final var isLeftOfIntersection = (point.getLon() - p1.getLon()) * (p2.getLat() - p1.getLat()) < (p2.getLon() - p1.getLon()) * (point.getLat() - p1.getLat());
        return isLatitudeBetween && isLeftOfIntersection;
    }
}

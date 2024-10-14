package kz.hq.airdex.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import kz.hq.airdex.data.entity.LatLngPoint;
import kz.hq.airdex.data.entity.MapSector;
import kz.hq.airdex.data.repository.MapSectorRepository;
import kz.hq.airdex.service.MapSectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MapSectorServiceImpl implements MapSectorService {
    private final MapSectorRepository sectorRepository;

    @Override
    public List<MapSector> getAll() {
        return sectorRepository.findAll(
            Sort.by(Direction.ASC, "code"));
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

            for (int i = 0; i < vertices - 1; i++) {
                var p1 = points.get(i);
                var p2 = points.get((i + 1) % vertices);

                if (isPointIntersecting(point, p1, p2)) {
                    crossings += 1;
                }
            }

            return crossings % 2 == 1;
        };
    }

    private boolean isPointIntersecting(LatLngPoint point, LatLngPoint p1, LatLngPoint p2) {
        return p1.getLat() <= point.getLat() && point.getLat() < p2.getLat() && (point.getLon() - p1.getLon())
            * (p2.getLat() - p1.getLat()) < (p2.getLon() - p1.getLon()) * (point.getLat() - p1.getLat());
    }
}

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
import kz.hq.airdex.spatialprocessors.BoundingBoxAlgorithm;
import kz.hq.airdex.spatialprocessors.abs.SpatialAlgorithm;
import kz.hq.airdex.service.AirQualityIndexProvider;
import kz.hq.airdex.service.MapSectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MapSectorServiceImpl implements MapSectorService {
    private final MapSectorRepository sectorRepository;
    private final MapSectorStatsRepository sectorStatsRepository;

    private final AirQualityIndexProvider airQualityIndexProvider;

    @Transactional(readOnly = true)
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

    @Transactional(readOnly = true)
    @Override
    public Optional<MapSector> determineSector(LatLngPoint point) {
        if (point == null) {
            return Optional.empty();
        }

        return sectorRepository.findAll().stream()
            .filter(applySpatialAlgorithm(point))
            .findFirst();
    }

    public Predicate<MapSector> applySpatialAlgorithm(LatLngPoint point) {
        return sector -> {
            SpatialAlgorithm spatial = new BoundingBoxAlgorithm(point, sector.getPoints());
            return spatial.isPointInsidePolygon();
        };
    }
}

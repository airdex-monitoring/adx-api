package kz.hq.airdex.service;

import java.util.List;
import java.util.Optional;
import kz.hq.airdex.data.entity.LatLngPoint;
import kz.hq.airdex.data.entity.MapSector;
import kz.hq.airdex.data.entity.query.MapSectorAvg;

public interface MapSectorService {

    List<MapSector> getAll();

    List<MapSectorAvg> getAllWithAvg();

    Optional<MapSector> determineSector(LatLngPoint point);
}

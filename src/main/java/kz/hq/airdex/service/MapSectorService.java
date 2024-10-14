package kz.hq.airdex.service;

import java.util.List;
import java.util.Optional;
import kz.hq.airdex.data.entity.LatLngPoint;
import kz.hq.airdex.data.entity.MapSector;

public interface MapSectorService {

    List<MapSector> getAll();

    Optional<MapSector> determineSector(LatLngPoint point);
}

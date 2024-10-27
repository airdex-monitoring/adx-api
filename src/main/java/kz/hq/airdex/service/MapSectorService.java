package kz.hq.airdex.service;

import java.util.List;
import java.util.Optional;

import kz.hq.airdex.data.dto.request.MapSectorQuery;
import kz.hq.airdex.data.entity.LatLngPoint;
import kz.hq.airdex.data.entity.MapSector;
import kz.hq.airdex.data.entity.query.MapSectorAvg;

public interface MapSectorService {

    List<MapSectorAvg> getAllWithAvg(MapSectorQuery query);

    Optional<MapSector> determineSector(LatLngPoint point);
}

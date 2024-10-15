package kz.hq.airdex.data.repository;

import java.util.List;
import kz.hq.airdex.data.entity.query.MapSectorAvg;

public interface MapSectorStatsRepository {

    List<MapSectorAvg> findAllWithAvg();
}

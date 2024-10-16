package kz.hq.airdex.data.repository;

import java.time.LocalDate;
import java.util.List;
import kz.hq.airdex.data.entity.query.MapSectorAvg;

public interface MapSectorStatsRepository {

    List<MapSectorAvg> findAllWithAvg();

    List<MapSectorAvg> findAllWithAvg(LocalDate startDate, LocalDate endDate);
}

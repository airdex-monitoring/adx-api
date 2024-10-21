package kz.hq.airdex.data.repository;

import java.time.LocalDate;
import java.util.List;
import kz.hq.airdex.data.entity.query.AqiEntryAvg;

public interface AirSensorSignalsStatsRepository {

    List<AqiEntryAvg> findAllWithAvg(LocalDate startDate, LocalDate endDate);
}

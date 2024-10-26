package kz.hq.airdex.data.repository;

import java.time.LocalDateTime;
import kz.hq.airdex.data.entity.query.AqiEntryAvg;

public interface AirSensorSignalsStatsRepository {

    AqiEntryAvg getAvg(LocalDateTime startDate, LocalDateTime endDate);

    AqiEntryAvg getAvg();
}

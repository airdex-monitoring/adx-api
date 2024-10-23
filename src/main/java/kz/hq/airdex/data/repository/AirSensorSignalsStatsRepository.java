package kz.hq.airdex.data.repository;

import java.time.LocalDate;

import kz.hq.airdex.data.entity.query.AqiEntryAvg;

public interface AirSensorSignalsStatsRepository {

    AqiEntryAvg getAvg(LocalDate startDate, LocalDate endDate);

    AqiEntryAvg getAvg();
}

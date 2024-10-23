package kz.hq.airdex.data.repository.impl;

import java.time.LocalDate;

import kz.hq.airdex.data.entity.query.AqiEntryAvg;
import kz.hq.airdex.data.repository.AirSensorSignalsStatsRepository;
import kz.hq.airdex.data.repository.Query.AqiStats;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AirSensorSignalsStatsRepositoryImpl implements AirSensorSignalsStatsRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public AqiEntryAvg getAvg(LocalDate startDate, LocalDate endDate) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("startDate", startDate);
        parameters.addValue("endDate", endDate);
        return jdbcTemplate.query(
            AqiStats.SELECT_AQI_WITH_AVG_DATE_RANGE,
            parameters,
            BeanPropertyRowMapper.newInstance(AqiEntryAvg.class)
            )
            .stream()
            .findFirst()
            .orElse(null);
    }

    @Override
    public AqiEntryAvg getAvg() {
        return jdbcTemplate.query(
                AqiStats.SELECT_AQI_WITH_AVG,
                BeanPropertyRowMapper.newInstance(AqiEntryAvg.class)
            )
            .stream()
            .findFirst()
            .orElse(null);
    }
}

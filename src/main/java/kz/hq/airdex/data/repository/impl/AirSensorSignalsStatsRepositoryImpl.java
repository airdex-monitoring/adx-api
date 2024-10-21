package kz.hq.airdex.data.repository.impl;

import java.time.LocalDate;
import java.util.List;
import kz.hq.airdex.data.entity.query.AqiEntryAvg;
import kz.hq.airdex.data.entity.query.MapSectorAvg;
import kz.hq.airdex.data.repository.AirSensorSignalsStatsRepository;
import kz.hq.airdex.data.repository.Query.Aqis;
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
    public List<AqiEntryAvg> findAllWithAvg(LocalDate startDate, LocalDate endDate) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("startDate", startDate);
        parameters.addValue("endDate", endDate);
        return jdbcTemplate.query(
            Aqis.SELECT_AQI_WITH_AVG,
            BeanPropertyRowMapper.newInstance(AqiEntryAvg.class));
    }
}

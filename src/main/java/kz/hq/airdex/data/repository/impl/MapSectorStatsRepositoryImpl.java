package kz.hq.airdex.data.repository.impl;

import java.time.LocalDateTime;
import java.util.List;
import kz.hq.airdex.data.entity.query.MapSectorAvg;
import kz.hq.airdex.data.repository.MapSectorStatsRepository;
import kz.hq.airdex.data.repository.Query.MapSectorStats;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MapSectorStatsRepositoryImpl implements MapSectorStatsRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<MapSectorAvg> findAllWithAvg() {
        return jdbcTemplate.query(
            MapSectorStats.SELECT_MAP_SECTORS_WITH_AVG,
            BeanPropertyRowMapper.newInstance(MapSectorAvg.class));
    }

    @Override
    public List<MapSectorAvg> findAllWithAvg(LocalDateTime startDate, LocalDateTime endDate) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("startDate", startDate);
        parameters.addValue("endDate", endDate);
        return jdbcTemplate.query(
            MapSectorStats.SELECT_MAP_SECTORS_WITH_AVG_DATE_RANGE, parameters,
            BeanPropertyRowMapper.newInstance(MapSectorAvg.class));
    }
}

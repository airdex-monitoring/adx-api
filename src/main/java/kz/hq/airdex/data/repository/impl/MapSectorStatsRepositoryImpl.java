package kz.hq.airdex.data.repository.impl;

import java.util.List;
import kz.hq.airdex.data.entity.query.MapSectorAvg;
import kz.hq.airdex.data.repository.MapSectorStatsRepository;
import kz.hq.airdex.data.repository.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MapSectorStatsRepositoryImpl implements MapSectorStatsRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<MapSectorAvg> findAllWithAvg() {
        return jdbcTemplate.query(
            Query.SELECT_MAP_SECTORS_WITH_AVG, BeanPropertyRowMapper.newInstance(MapSectorAvg.class));
    }
}

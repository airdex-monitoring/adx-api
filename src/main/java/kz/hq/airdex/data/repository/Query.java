package kz.hq.airdex.data.repository;

public class Query {

    public static final String SELECT_MAP_SECTORS_WITH_AVG =
        """
            SELECT
                ms.id,
                ms.code,
                ms.points,
                AVG(ass.aqi) aqiAvg,
                AVG(ass.pm_1_0) pm_1_0_avg,
                AVG(ass.pm_2_5) pm_2_5_avg,
                AVG(ass.pm_10) pm_10_avg
            FROM
                adx.air_sensor_signals ass
                    JOIN adx.map_sectors ms
                         ON ass.sector_id = ms.id
            WHERE
                ass.create_date >= TO_TIMESTAMP('11-10-2024', 'DD-MM-YYYY')
                    AND ass.create_date <= TO_TIMESTAMP('16-10-2024', 'DD-MM-YYYY') + INTERVAL '1 day'
            GROUP BY
                ms.id, ms.code, ms.points
            ORDER BY
                ms.code
        """;
}

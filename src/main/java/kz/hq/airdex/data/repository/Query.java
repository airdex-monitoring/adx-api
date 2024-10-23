package kz.hq.airdex.data.repository;

public class Query {

    public static class AqiStats {
        public static final String SELECT_AQI_WITH_AVG =
            """
                SELECT
                    MIN(ass.aqi) aqiMin,
                    MAX(ass.aqi) aqiMax,
                    AVG(ass.aqi) aqiAvg,
                    MIN(ass.pm_1_0) pm_1_0_min,
                    MAX(ass.pm_1_0) pm_1_0_max,
                    AVG(ass.pm_1_0) pm_1_0_avg,
                    MIN(ass.pm_2_5) pm_2_5_min,
                    MAX(ass.pm_2_5) pm_2_5_max,
                    AVG(ass.pm_2_5) pm_2_5_avg,
                    MIN(ass.pm_10) pm_10_min,
                    MAX(ass.pm_10) pm_10_max,
                    AVG(ass.pm_10) pm_10_avg
                    FROM adx.air_sensor_signals ass
            """;
        public static final String SELECT_AQI_WITH_AVG_DATE_RANGE =
            """
                SELECT
                    MIN(ass.aqi) aqiMin,
                    MAX(ass.aqi) aqiMax,
                    AVG(ass.aqi) aqiAvg,
                    MIN(ass.pm_1_0) pm_1_0_min,
                    MAX(ass.pm_1_0) pm_1_0_max,
                    AVG(ass.pm_1_0) pm_1_0_avg,
                    MIN(ass.pm_2_5) pm_2_5_min,
                    MAX(ass.pm_2_5) pm_2_5_max,
                    AVG(ass.pm_2_5) pm_2_5_avg,
                    MIN(ass.pm_10) pm_10_min,
                    MAX(ass.pm_10) pm_10_max,
                    AVG(ass.pm_10) pm_10_avg
                    FROM adx.air_sensor_signals ass
                    WHERE
                        ass.create_date >= :startDate
                      AND ass.create_date <= :endDate + INTERVAL '1 day'
            """;
    }

    public static class MapSectorStats {
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
                GROUP BY
                    ms.id, ms.code, ms.points
                ORDER BY
                    ms.code
            """;

        public static final String SELECT_MAP_SECTORS_WITH_AVG_DATE_RANGE =
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
                    ass.create_date >= :startDate
                        AND ass.create_date <= :endDate + INTERVAL '1 day'
                GROUP BY
                    ms.id, ms.code, ms.points
                ORDER BY
                    ms.code
            """;
    }
}

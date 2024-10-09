package kz.hq.airdex.service;

import kz.hq.airdex.data.constant.AqiLevel;

public interface AirQualityIndexProvider {

    /**
     * Calculate Air Quality Index (AQI) for PM 2.5 data.
     * @see <a href="https://metone.com/how-to-calculate-aqi-and-nowcast-indices/">https://metone.com</a>
     * @param pm_2_5 {@link Integer}
     * @return calculated aqi.
     */
    double getIndex(Integer pm_2_5);

    /**
     * Get Air Quality Index (AQI) classification level.
     * @see <a href="https://metone.com/how-to-calculate-aqi-and-nowcast-indices/">https://metone.com</a>
     * @param aqi {@link Double}
     * @return {@link AqiLevel} calculated aqi.
     */
    AqiLevel getAqiLevel(Double aqi);
}

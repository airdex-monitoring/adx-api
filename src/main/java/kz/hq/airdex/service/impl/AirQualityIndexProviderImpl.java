package kz.hq.airdex.service.impl;

import kz.hq.airdex.data.constant.Aqi;
import kz.hq.airdex.data.constant.AqiLevel;
import kz.hq.airdex.service.AirQualityIndexProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AirQualityIndexProviderImpl implements AirQualityIndexProvider {

    @Override
    public double getIndex(Integer pm_2_5) {
        double aqi_breakp_diff = (Aqi.Breakpoints.AQI_HIGH_UNHEALTHY_FOR_SENSITIVE - Aqi.Breakpoints.AQI_LOW_UNHEALTHY_FOR_SENSITIVE);
        double pm_breakp_diff = (Aqi.Breakpoints.PM_HIGH_UNHEALTHY_FOR_SENSITIVE - Aqi.Breakpoints.PM_LOW_UNHEALTHY_FOR_SENSITIVE);
        double pm_2_5_actual_low_diff = (pm_2_5 - Aqi.Breakpoints.PM_HIGH_UNHEALTHY_FOR_SENSITIVE);
        double aqi_pm_diff = aqi_breakp_diff / pm_breakp_diff;
        double aqi_pm = aqi_pm_diff * pm_2_5_actual_low_diff;
        double aqi = aqi_pm + Aqi.Breakpoints.AQI_LOW_UNHEALTHY_FOR_SENSITIVE;
        return aqi < 0 ? 0 : aqi;
    }

    @Override
    public AqiLevel getAqiLevel(Double aqi) {
        if (aqi == null) {
            return null;
        }

        if (aqi >= 0 && aqi <= 50) {
            return AqiLevel.GOOD;
        } else if (aqi >= 51 && aqi <= 100) {
            return AqiLevel.MODERATE;
        } else if (aqi >= 101 && aqi <= 150) {
            return AqiLevel.UNHEALTHY_FOR_SENSITIVE_GROUP;
        } else if (aqi >= 151 && aqi <= 200) {
            return AqiLevel.UNHEALTHY;
        } else if (aqi >= 201 && aqi <= 300) {
            return AqiLevel.VERY_UNHEALTHY;
        } else if (aqi > 300) {
            return AqiLevel.HAZARDOUS;
        }

        return null;
    }
}

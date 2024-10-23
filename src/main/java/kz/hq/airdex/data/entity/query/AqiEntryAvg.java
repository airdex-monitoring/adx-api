package kz.hq.airdex.data.entity.query;

import kz.hq.airdex.data.constant.AqiLevel;
import lombok.Data;

@Data
public class AqiEntryAvg {

    private Double aqiAvg;
    private AqiLevel aqiAvgLevel;

    private Double aqiMin;
    private AqiLevel aqiMinLevel;

    private Double aqiMax;
    private AqiLevel aqiMaxLevel;

    private Double pm_1_0_min;
    private Double pm_1_0_max;
    private Double pm_1_0_avg;

    private Double pm_2_5_min;
    private Double pm_2_5_max;
    private Double pm_2_5_avg;

    private Double pm_10_min;
    private Double pm_10_max;
    private Double pm_10_avg;
}

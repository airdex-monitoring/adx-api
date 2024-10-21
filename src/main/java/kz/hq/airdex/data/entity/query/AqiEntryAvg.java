package kz.hq.airdex.data.entity.query;

import kz.hq.airdex.data.constant.AqiLevel;
import lombok.Data;

@Data
public class AqiEntryAvg {

    private Double aqiAvg;
    private Double aqiMin;
    private Double aqiMax;

    private AqiLevel aqiLevel;

    private Double pm_1_0_avg;
    private Double pm_2_5_avg;
    private Double pm_10_avg;
}

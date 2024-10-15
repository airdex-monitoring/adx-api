package kz.hq.airdex.data.entity.query;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;
import kz.hq.airdex.data.constant.AqiLevel;
import kz.hq.airdex.data.entity.LatLngPoint;
import lombok.Data;
import lombok.SneakyThrows;

@Data
public class MapSectorAvg {

    private Long id;
    private String code;
    private List<LatLngPoint> points;

    private Double aqiAvg;
    private AqiLevel aqiLevel;

    private Double pm_1_0_avg;
    private Double pm_2_5_avg;
    private Double pm_10_avg;

    @SneakyThrows
    public void setPoints(String pointsStr) {
        var pointsArr = new ObjectMapper().readValue(pointsStr, LatLngPoint[].class);
        this.points = Arrays.asList(pointsArr);
    }
}

package kz.hq.airdex.data.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AirSensorSignalAcceptRequest {

    private Double lat;
    private Double lon;

    private Integer pm_1_0;
    private Integer pm_2_5;
    private Integer pm_10;
}

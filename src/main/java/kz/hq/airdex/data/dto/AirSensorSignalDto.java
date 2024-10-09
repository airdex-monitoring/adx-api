package kz.hq.airdex.data.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AirSensorSignalDto {

    private Long id;
    private LocalDateTime createDate;

    private Double lat;
    private Double lon;

    private Integer pm_1_0;
    private Integer pm_2_5;
    private Integer pm_10;

    private Integer aqi;
}

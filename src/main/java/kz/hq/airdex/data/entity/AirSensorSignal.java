package kz.hq.airdex.data.entity;

import jakarta.persistence.*;
import kz.hq.airdex.data.constant.AqiLevel;
import kz.hq.airdex.data.entity.abs.AbstractBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "air_sensor_signals", schema = "adx")
@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AirSensorSignal extends AbstractBaseEntity {

    @Id
    private Long id;

    @Column(name = "lat")
    private Double lat;
    @Column(name = "lon")
    private Double lon;

    @Column(name = "pm_1_0")
    private Integer pm_1_0;
    @Column(name = "pm_2_5")
    private Integer pm_2_5;
    @Column(name = "pm_10")
    private Integer pm_10;

    @Column(name = "aqi")
    private Double aqi;
    @Enumerated(EnumType.STRING)
    @Column(name = "aqi_level")
    private AqiLevel aqiLevel;
}

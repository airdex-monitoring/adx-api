package kz.hq.airdex.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kz.hq.airdex.data.entity.abs.AbstractBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "air_signal_signals", schema = "adx")
@Data
@RequiredArgsConstructor
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
    private Integer aqi;
}

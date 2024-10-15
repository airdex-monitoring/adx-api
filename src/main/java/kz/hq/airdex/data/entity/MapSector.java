package kz.hq.airdex.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.List;
import kz.hq.airdex.data.converter.LatLngPointConverter;
import kz.hq.airdex.data.entity.abs.AbstractBaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "map_sectors", schema = "adx")
@Data
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MapSector extends AbstractBaseEntity {

    @Column(name = "code")
    private String code;

    @Column(name = "points")
    @Convert(converter = LatLngPointConverter.class)
    private List<LatLngPoint> points;
}

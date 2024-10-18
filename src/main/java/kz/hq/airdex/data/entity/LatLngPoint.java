package kz.hq.airdex.data.entity;

import jakarta.persistence.Embeddable;
import kz.hq.airdex.data.entity.abs.SpatialPoint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LatLngPoint implements SpatialPoint {

    private Double lat;
    private Double lon;
}

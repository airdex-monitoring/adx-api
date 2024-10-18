package kz.hq.airdex.data.dto;

import kz.hq.airdex.data.entity.abs.SpatialPoint;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LatLngPointDto implements SpatialPoint {

    private Double lat;
    private Double lon;
}

package kz.hq.airdex.spatialprocessors.abs;

import kz.hq.airdex.data.entity.abs.SpatialPoint;

import java.util.List;

public abstract class SpatialAlgorithm {
    protected final SpatialPoint point;
    protected final List<? extends SpatialPoint> polygon;
    protected final int size;

    protected SpatialAlgorithm(SpatialPoint point, List<? extends SpatialPoint> polygon) {
        this.point = point;
        this.polygon = polygon;
        this.size = polygon.size();
    }

    public abstract boolean isPointInsidePolygon() throws IllegalArgumentException;
}

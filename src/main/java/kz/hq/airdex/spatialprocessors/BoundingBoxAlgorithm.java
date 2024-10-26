package kz.hq.airdex.spatialprocessors;

import kz.hq.airdex.data.entity.abs.SpatialPoint;
import kz.hq.airdex.spatialprocessors.abs.SpatialAlgorithm;

import java.util.List;

public class BoundingBoxAlgorithm extends SpatialAlgorithm {

    public BoundingBoxAlgorithm(SpatialPoint point, List<? extends SpatialPoint> polygon) {
        super(point, polygon);
    }

    /**
     * Determines if a given point is inside an axis-aligned square based on its bounding box.
     * <p>
     * The method checks whether the point's latitude and longitude fall within the minimum
     * and maximum latitude and longitude values that define the square's boundaries.
     *
     * @return {@code true} if the point is inside the square's bounding box, {@code false} otherwise.
     */
    @Override
    public boolean isPointInsidePolygon() {
        double minLat = Double.MAX_VALUE;
        double maxLat = Double.MIN_VALUE;
        double minLon = Double.MAX_VALUE;
        double maxLon = Double.MIN_VALUE;

        for (SpatialPoint vertex : super.polygon) {
            minLat = Math.min(minLat, vertex.getLat());
            maxLat = Math.max(maxLat, vertex.getLat());
            minLon = Math.min(minLon, vertex.getLon());
            maxLon = Math.max(maxLon, vertex.getLon());
        }

        boolean isWithinLatitudeBounds = point.getLat() >= minLat && point.getLat() <= maxLat;
        boolean isWithinLongitudeBounds = point.getLon() >= minLon && point.getLon() <= maxLon;

        return isWithinLatitudeBounds && isWithinLongitudeBounds;
    }
}

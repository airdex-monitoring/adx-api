package kz.hq.airdex.spatialprocessors;

import kz.hq.airdex.data.entity.LatLngPoint;
import kz.hq.airdex.data.entity.abs.SpatialPoint;
import kz.hq.airdex.spatialprocessors.abs.SpatialAlgorithm;

import java.util.List;

public class RayCastingAlgorithm extends SpatialAlgorithm {

    public RayCastingAlgorithm(SpatialPoint point, List<? extends SpatialPoint> polygon) {
        super(point, polygon);
    }

    /**
     * Determines if a point is intersecting with a line polygon formed by two other points.
     * <p>
     * The method checks two conditions:
     * 1. Whether the point's latitude is between the latitudes of the polygon's endpoints.
     * 2. Whether the point is to the left of the line polygon based on its longitude.
     *
     * @return {@code true} if the point is considered intersecting with the line segment, false otherwise.
     */
    @Override
    public boolean isPointInsidePolygon() {
        int vertices = super.polygon.size();
        int crossings = 0;

        for (int i = 0; i < vertices; i++) {
            var p1 = polygon.get(i);
            var p2 = polygon.get((i + 1) % vertices);

            if (isPointIntersecting(point, p1, p2)) {
                crossings++;
            }
        }

        return crossings % 2 == 1;
    }

    private boolean isPointIntersecting(SpatialPoint point, SpatialPoint p1, SpatialPoint p2) {
        if (p1 == null || p2 == null) {
            throw new IllegalArgumentException("Line point appears to be null");
        }

        if (p1.getLat() > p2.getLat()) {
            SpatialPoint temp = p1;
            p1 = p2;
            p2 = temp;
        }

        if (point.getLat().equals(p1.getLat()) || point.getLat().equals(p2.getLat())) {
            point = new LatLngPoint(point.getLat() + 0.00001, point.getLon());
        }

        if (p1.getLat().equals(p2.getLat())) {
            return false;
        }

        final var isLatitudeBetween = p1.getLat() <= point.getLat() && point.getLat() < p2.getLat();
        final var isLeftOfIntersection = (point.getLon() - p1.getLon()) * (p2.getLat() - p1.getLat()) < (p2.getLon() - p1.getLon()) * (point.getLat() - p1.getLat());
        return isLatitudeBetween && isLeftOfIntersection;
    }
}

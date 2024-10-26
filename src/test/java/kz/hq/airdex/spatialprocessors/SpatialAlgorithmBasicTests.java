package kz.hq.airdex.spatialprocessors;

import kz.hq.airdex.data.entity.LatLngPoint;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

import static kz.hq.airdex.utils.MapSectorUtils.POLYGON_A2;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class SpatialAlgorithmBasicTests {
    private static final LatLngPoint DEFAULT_POINT =
        new LatLngPoint(51.094991, 71.418199);

    @Test
    public void testRayCastingAlgorithm() {
        long totalDuration = 0;
        for (int i = 0; i < 10_000; i++) {
            long startTime = System.nanoTime();
            var spatial = new RayCastingAlgorithm(DEFAULT_POINT, POLYGON_A2);
            spatial.isPointInsidePolygon();
            totalDuration += System.nanoTime() - startTime;
        }
        double averageDuration = (double) totalDuration / 1_000_000;
        System.out.println("[Basic-Polygon Test, 4 edges] Average duration for ray-casting algorithm: " + averageDuration + " ms");
    }

    @Test
    public void testBoundingBoxAlgorithm() {
        long totalDuration = 0;
        for (int i = 0; i < 10_000; i++) {
            long startTime = System.nanoTime();
            var spatial = new BoundingBoxAlgorithm(DEFAULT_POINT, POLYGON_A2);
            spatial.isPointInsidePolygon();
            totalDuration += System.nanoTime() - startTime;
        }
        double averageDuration = (double) totalDuration / 1_000_000;
        System.out.println("[Basic-Polygon Test, 4 edges] Average duration for bounding-box algorithm: " + averageDuration + " ms");
    }
}

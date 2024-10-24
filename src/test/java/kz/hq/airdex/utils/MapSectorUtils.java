package kz.hq.airdex.utils;

import kz.hq.airdex.data.entity.LatLngPoint;

import java.util.List;
import java.util.stream.Stream;

public class MapSectorUtils {
    public static final List<LatLngPoint> POLYGON_A1 = List.of(
        new LatLngPoint(51.097297, 71.414138),
        new LatLngPoint(51.093427, 71.412509),
        new LatLngPoint(51.095740, 71.424906),
        new LatLngPoint(51.087019, 71.409959),
        new LatLngPoint(51.085373, 71.421010)
    );
    public static final List<LatLngPoint> POLYGON_A2 = List.of(
        new LatLngPoint(51.093427, 71.412509),
        new LatLngPoint(51.087019, 71.409959),
        new LatLngPoint(51.085373, 71.421010),
        new LatLngPoint(51.092003, 71.423560)
    );
    public static final List<LatLngPoint> POLYGON_B1 = List.of(
        new LatLngPoint(51.099655, 71.426465),
        new LatLngPoint(51.098899, 71.431778),
        new LatLngPoint(51.094951, 71.430486),
        new LatLngPoint(51.095740, 71.424906)
    );
    public static final List<LatLngPoint> POLYGON_B2 = List.of(
        new LatLngPoint(51.092003, 71.423560),
        new LatLngPoint(51.095740, 71.424906),
        new LatLngPoint(51.094951, 71.430486),
        new LatLngPoint(51.091291, 71.428944)
    );
    public static final List<LatLngPoint> SUPER_POLYGON = Stream.of(
        POLYGON_A1,
        POLYGON_A2,
        POLYGON_B1,
        POLYGON_B2
    )
        .flatMap(List::stream)
        .distinct()
        .toList();
}

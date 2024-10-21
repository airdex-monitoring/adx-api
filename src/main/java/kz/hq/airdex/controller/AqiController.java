package kz.hq.airdex.controller;

import java.time.LocalDate;
import java.util.List;
import kz.hq.airdex.data.dto.AirSensorSignalDto;
import kz.hq.airdex.data.dto.request.AqiQuery;
import kz.hq.airdex.data.dto.request.MapSectorQuery;
import kz.hq.airdex.data.entity.query.MapSectorAvg;
import kz.hq.airdex.service.AirSensorSignalService;
import kz.hq.airdex.service.MapSectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/aqi")
@RequiredArgsConstructor
public class AqiController {
    private final AirSensorSignalService airSensorSignalService;
    private final MapSectorService mapSectorService;

    @GetMapping("/entries")
    public List<AirSensorSignalDto> findAll(
        @RequestParam(required = false) Long sectorId,
        AqiQuery query
    ) {
        return airSensorSignalService.findAll(sectorId, query);
    }

    @GetMapping("/entries-avg")
    public List<AirSensorSignalDto> findAllAvg(
        @RequestParam(required = false) Long sectorId,
        AqiQuery query
    ) {
        return airSensorSignalService.findAll(sectorId, query);
    }

    @GetMapping("/map-sectors-avg")
    public List<?> findAllMapSectorsAvg(
        MapSectorQuery query
    ) {
        return mapSectorService.getAllWithAvg(query);
    }
}

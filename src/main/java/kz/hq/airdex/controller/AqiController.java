package kz.hq.airdex.controller;

import java.util.List;
import kz.hq.airdex.data.dto.AirSensorSignalDto;
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

    @GetMapping
    public List<AirSensorSignalDto> findAll(
        @RequestParam(required = false) Long sectorId
    ) {
        return airSensorSignalService.findAll(sectorId);
    }

    @GetMapping("/map-sectors")
    public List<?> findAllMapSectors() {
        return mapSectorService.getAll();
    }
}

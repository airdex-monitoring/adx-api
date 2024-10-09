package kz.hq.airdex.controller;

import kz.hq.airdex.data.dto.AirSensorSignalDto;
import kz.hq.airdex.data.dto.request.AirSensorSignalAcceptRequest;
import kz.hq.airdex.data.dto.response.ResponseWrap;
import kz.hq.airdex.service.AirSensorSignalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/air-signals")
@RequiredArgsConstructor
public class AirSignalController {
    private final AirSensorSignalService sensorSignalService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseWrap<AirSensorSignalDto> save(@RequestBody AirSensorSignalAcceptRequest payload) {
        var result = sensorSignalService.save(payload);
        return ResponseWrap.of(result);
    }

    @GetMapping
    public ResponseWrap<List<AirSensorSignalDto>> findAll() {
        var signals = sensorSignalService.findAll();
        return ResponseWrap.of(signals);
    }
}

package kz.hq.airdex.service;

import kz.hq.airdex.data.dto.AirSensorSignalDto;
import kz.hq.airdex.data.dto.request.AirSensorSignalAcceptRequest;

import java.util.List;
import kz.hq.airdex.data.entity.AirSensorSignal;

public interface AirSensorSignalService {

    AirSensorSignalDto save(AirSensorSignalAcceptRequest payload);

    List<AirSensorSignal> findAll();

    List<AirSensorSignalDto> findAll(Long sectorId);
}

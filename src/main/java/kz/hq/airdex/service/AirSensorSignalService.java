package kz.hq.airdex.service;

import kz.hq.airdex.data.dto.AirSensorSignalDto;
import kz.hq.airdex.data.dto.request.AirSensorSignalAcceptRequest;

import java.util.List;

public interface AirSensorSignalService {

    AirSensorSignalDto save(AirSensorSignalAcceptRequest payload);

    List<AirSensorSignalDto> findAll();
}

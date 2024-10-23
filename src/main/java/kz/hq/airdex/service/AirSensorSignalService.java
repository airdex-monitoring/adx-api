package kz.hq.airdex.service;

import kz.hq.airdex.data.dto.AirSensorSignalDto;
import kz.hq.airdex.data.dto.request.AirSensorSignalAcceptRequest;

import java.util.List;
import kz.hq.airdex.data.dto.request.AqiQuery;
import kz.hq.airdex.data.entity.AirSensorSignal;
import kz.hq.airdex.data.entity.query.AqiEntryAvg;

public interface AirSensorSignalService {

    AirSensorSignalDto save(AirSensorSignalAcceptRequest payload);

    List<AirSensorSignal> findAll();

    List<AirSensorSignalDto> findAll(Long sectorId, AqiQuery query);

    AqiEntryAvg getAvg(AqiQuery query);
}

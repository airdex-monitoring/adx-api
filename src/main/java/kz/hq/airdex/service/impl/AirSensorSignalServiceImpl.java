package kz.hq.airdex.service.impl;


import kz.hq.airdex.data.dto.AirSensorSignalDto;
import kz.hq.airdex.data.dto.request.AirSensorSignalAcceptRequest;
import kz.hq.airdex.data.dto.request.AqiQuery;
import kz.hq.airdex.data.entity.AirSensorSignal;
import kz.hq.airdex.data.entity.MapSector;
import kz.hq.airdex.data.entity.query.AqiEntryAvg;
import kz.hq.airdex.data.mapper.AirSensorSignalMapper;
import kz.hq.airdex.data.repository.AirSensorSignalRepository;
import kz.hq.airdex.data.repository.AirSensorSignalsStatsRepository;
import kz.hq.airdex.service.AirQualityIndexProvider;
import kz.hq.airdex.service.AirSensorSignalService;
import kz.hq.airdex.service.MapSectorService;
import kz.hq.airdex.service.specification.AqiSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AirSensorSignalServiceImpl implements AirSensorSignalService {
    private final AirSensorSignalRepository sensorSignalRepository;
    private final AirSensorSignalsStatsRepository sensorSignalsStatsRepository;
    private final AirSensorSignalMapper sensorSignalMapper;

    private final AirQualityIndexProvider airQualityIndexProvider;
    private final MapSectorService mapSectorService;

    @Override
    public AirSensorSignalDto save(AirSensorSignalAcceptRequest payload) {
        return Optional.ofNullable(payload)
            .map(sensorSignalMapper::map)
            .filter(signal -> signal.getPm_2_5() != null)
            .map(this::setAqiData)
            .map(this::setSector)
            .map(sensorSignalRepository::save)
            .map(sensorSignalMapper::map)
            .orElse(null);
    }

    @Override
    public List<AirSensorSignalDto> findAll(AqiQuery query) {
        var signals = Optional.ofNullable(query)
            .map(AqiSpecification::getParameters)
            .map(sensorSignalRepository::findAll)
            .orElseGet(sensorSignalRepository::findAll);
        return sensorSignalMapper.map(signals);
    }

    @Override
    public AqiEntryAvg getAvg(AqiQuery query) {
        var data = Optional.ofNullable(query)
            .filter(q -> q.getStartDate() != null || q.getEndDate() != null)
            .map(q -> sensorSignalsStatsRepository.getAvg(q.getStartDate(), q.getEndDate()))
            .orElseGet(sensorSignalsStatsRepository::getAvg);

        data.setAqiAvgLevel(airQualityIndexProvider.getAqiLevel(data.getAqiAvg()));
        data.setAqiMinLevel(airQualityIndexProvider.getAqiLevel(data.getAqiMin()));
        data.setAqiMaxLevel(airQualityIndexProvider.getAqiLevel(data.getAqiMax()));

        return data;
    }

    private AirSensorSignal setAqiData(AirSensorSignal signal) {
        var aqi = airQualityIndexProvider.getIndex(signal.getPm_2_5());
        var aqiLevel = airQualityIndexProvider.getAqiLevel(aqi);
        signal.setAqi(aqi);
        signal.setAqiLevel(aqiLevel);
        return signal;
    }

    private AirSensorSignal setSector(AirSensorSignal signal) {
        Optional<MapSector> mapSector = mapSectorService.determineSector(signal.getPoint());
        mapSector.ifPresent(signal::setSector);
        return signal;
    }
}

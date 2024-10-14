package kz.hq.airdex.service.impl;

import kz.hq.airdex.data.dto.AirSensorSignalDto;
import kz.hq.airdex.data.dto.request.AirSensorSignalAcceptRequest;
import kz.hq.airdex.data.entity.AirSensorSignal;
import kz.hq.airdex.data.mapper.AirSensorSignalMapper;
import kz.hq.airdex.data.repository.AirSensorSignalRepository;
import kz.hq.airdex.service.AirQualityIndexProvider;
import kz.hq.airdex.service.AirSensorSignalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AirSensorSignalServiceImpl implements AirSensorSignalService {
    private final AirSensorSignalRepository sensorSignalRepository;
    private final AirSensorSignalMapper sensorSignalMapper;

    private final AirQualityIndexProvider airQualityIndexProvider;

    @Override
    public AirSensorSignalDto save(AirSensorSignalAcceptRequest payload) {
        return Optional.ofNullable(payload)
            .map(sensorSignalMapper::map)
            .filter(signal -> signal.getPm_2_5() != null)
            .map(this::setAqiData)
            .map(sensorSignalRepository::save)
            .map(sensorSignalMapper::map)
            .orElse(null);
    }

    @Override
    public List<AirSensorSignalDto> findAll() {
        var signals = sensorSignalRepository.findAll(
            Sort.by(Sort.Direction.DESC, "createDate"));
        return sensorSignalMapper.map(signals);
    }

    private AirSensorSignal setAqiData(AirSensorSignal signal) {
        var aqi = airQualityIndexProvider.getIndex(signal.getPm_2_5());
        var aqiLevel = airQualityIndexProvider.getAqiLevel(aqi);
        signal.setAqi(aqi);
        signal.setAqiLevel(aqiLevel);
        return signal;
    }
}

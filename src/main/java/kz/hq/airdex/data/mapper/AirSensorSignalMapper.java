package kz.hq.airdex.data.mapper;

import kz.hq.airdex.data.dto.AirSensorSignalDto;
import kz.hq.airdex.data.dto.request.AirSensorSignalAcceptRequest;
import kz.hq.airdex.data.entity.AirSensorSignal;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AirSensorSignalMapper {

    AirSensorSignal map(AirSensorSignalAcceptRequest source);

    AirSensorSignalDto map(AirSensorSignal source);

    List<AirSensorSignalDto> map(List<AirSensorSignal> source);
}

package kz.hq.airdex.data.mapper;

import kz.hq.airdex.data.dto.AirSensorSignalDto;
import kz.hq.airdex.data.dto.request.AirSensorSignalAcceptRequest;
import kz.hq.airdex.data.entity.AirSensorSignal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AirSensorSignalMapper {

    @Mapping(source = "source.lat", target = "point.lat")
    @Mapping(source = "source.lon", target = "point.lon")
    AirSensorSignal map(AirSensorSignalAcceptRequest source);

    AirSensorSignalDto map(AirSensorSignal source);

    List<AirSensorSignalDto> map(List<AirSensorSignal> source);
}

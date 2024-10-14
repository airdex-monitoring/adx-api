package kz.hq.airdex.data.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import kz.hq.airdex.data.entity.LatLngPoint;
import lombok.extern.slf4j.Slf4j;

@Converter
@Slf4j
public class LatLngPointConverter implements AttributeConverter<List<LatLngPoint>, String> {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<LatLngPoint> zoneCoordinates) {
        try {
            return objectMapper.writeValueAsString(zoneCoordinates);
        } catch (JsonProcessingException e) {
            log.error("Failed to convert an array of ZoneCoordinate into JSON: {}", e.getMessage());
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public List<LatLngPoint> convertToEntityAttribute(String zoneCoordinates) {
        try {
            if (zoneCoordinates == null || zoneCoordinates.equals("null")) {
                return Collections.emptyList();
            }

            return Arrays.asList(objectMapper.readValue(zoneCoordinates, LatLngPoint[].class));
        } catch (JsonProcessingException e) {
            log.error("Failed to convert JSON into an array of ZoneCoordinate: {}", e.getMessage());
            return null;
        }
    }
}

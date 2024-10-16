package kz.hq.airdex.data.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MapSectorQuery {

    private LocalDate from;
    private LocalDate to;
}

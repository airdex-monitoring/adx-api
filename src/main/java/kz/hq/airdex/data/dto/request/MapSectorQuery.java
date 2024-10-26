package kz.hq.airdex.data.dto.request;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MapSectorQuery {

    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime startDate;
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime endDate;
}

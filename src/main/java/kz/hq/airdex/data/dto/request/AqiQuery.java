package kz.hq.airdex.data.dto.request;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AqiQuery {

    private Long sectorId;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate endDate;
}

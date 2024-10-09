package kz.hq.airdex.data.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class ResponseWrap<T> {

    private T content;
}

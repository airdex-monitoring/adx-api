package kz.hq.airdex.data.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseWrap<T> {

    private T content;
}

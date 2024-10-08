package kz.hq.airdex.data.entity.abs;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Getter
@Setter(AccessLevel.PRIVATE)
@MappedSuperclass
public abstract class AbstractBaseEntity {

    @Column(name = "create_date")
    @CreationTimestamp
    private LocalDateTime createDate;
}

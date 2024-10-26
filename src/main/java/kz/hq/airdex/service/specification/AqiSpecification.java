package kz.hq.airdex.service.specification;

import static org.springframework.data.jpa.domain.Specification.allOf;

import java.time.LocalDateTime;
import java.util.Optional;
import kz.hq.airdex.data.dto.request.AqiQuery;
import kz.hq.airdex.data.entity.AirSensorSignal;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;

public class AqiSpecification {

    public static Specification<AirSensorSignal> bySectorId(Long sectorId) {
        return (root, query, criteriaBuilder) -> Optional.ofNullable(sectorId)
            .map(id -> {
                var sectorJoin = root.join("sector");
                return criteriaBuilder.equal(sectorJoin.get("id"), id);
            })
            .orElse(criteriaBuilder.conjunction());
    }

    public static Specification<AirSensorSignal> byCreateDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return (root, query, criteriaBuilder) -> {
            if (startDate == null || endDate == null) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.between(
                root.get("createDate"), startDate, endDate);
        };
    }

    public static Specification<AirSensorSignal> sortByCreateDate(Direction sortDirection) {
        return (root, query, criteriaBuilder) -> {
            if (query == null) {
                return criteriaBuilder.conjunction();
            }

            if (sortDirection.isAscending()) {
                query.orderBy(criteriaBuilder.asc(root.get("createDate")));
            } else {
                query.orderBy(criteriaBuilder.desc(root.get("createDate")));
            }
            return criteriaBuilder.conjunction();
        };
    }

    public static Specification<AirSensorSignal> getParameters(AqiQuery query) {
        return Optional.of(query)
            .map(q -> allOf(
                bySectorId(q.getSectorId()),
                byCreateDateRange(q.getStartDate(), q.getEndDate()),
                sortByCreateDate(Direction.DESC)
            ))
            .orElse(sortByCreateDate(Direction.DESC));
    }
}

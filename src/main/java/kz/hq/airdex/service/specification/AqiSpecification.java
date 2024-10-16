package kz.hq.airdex.service.specification;

import static org.springframework.data.jpa.domain.Specification.allOf;

import java.time.LocalDate;
import java.util.Optional;
import kz.hq.airdex.data.dto.request.AqiQuery;
import kz.hq.airdex.data.entity.AirSensorSignal;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
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

    public static Specification<AirSensorSignal> byDateRange(LocalDate startDate, LocalDate endDate) {
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
            query.;
            return criteriaBuilder.conjunction();
        }
    }

    public static Specification<AirSensorSignal> getParameters(AqiQuery query) {
        return Optional.of(query)
            .map(q -> allOf(
                bySectorId(q.getSectorId()),
                byDateRange(q.getStartDate(), q.getEndDate())
            ));
    }
}

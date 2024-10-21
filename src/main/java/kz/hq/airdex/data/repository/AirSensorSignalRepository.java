package kz.hq.airdex.data.repository;

import java.time.LocalDate;
import java.util.List;
import kz.hq.airdex.data.entity.AirSensorSignal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AirSensorSignalRepository extends
    JpaRepository<AirSensorSignal, Long>,
    JpaSpecificationExecutor<AirSensorSignal> {

    @Query("""
        SELECT a FROM AirSensorSignal a
            WHERE
                a.sector.id = :sectorId AND
                (a.createDate >= :startDate AND a.createDate <= :endDate)
    """)
    List<AirSensorSignal> findAllBySector(Long sectorId, LocalDate startDate, LocalDate endDate);
}

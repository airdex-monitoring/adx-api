package kz.hq.airdex.data.repository;

import java.util.List;
import kz.hq.airdex.data.entity.AirSensorSignal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AirSensorSignalRepository extends JpaRepository<AirSensorSignal, Long> {

    @Query("SELECT a FROM AirSensorSignal a WHERE a.sector.id = :sectorId")
    List<AirSensorSignal> findAll(Long sectorId);
}

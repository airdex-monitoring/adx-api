package kz.hq.airdex.data.repository;

import kz.hq.airdex.data.entity.AirSensorSignal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirSensorSignalRepository extends JpaRepository<AirSensorSignal, Long> {
}

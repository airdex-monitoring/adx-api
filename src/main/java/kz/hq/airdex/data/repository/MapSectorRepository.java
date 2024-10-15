package kz.hq.airdex.data.repository;

import kz.hq.airdex.data.entity.MapSector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MapSectorRepository extends JpaRepository<MapSector, Long> {
}

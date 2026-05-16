package hackerton.caudex.domain.garden.repository;

import hackerton.caudex.domain.garden.entity.Garden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GardenRepository extends JpaRepository<Garden, Long> {
}

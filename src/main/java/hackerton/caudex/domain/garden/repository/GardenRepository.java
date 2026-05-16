package hackerton.caudex.domain.garden.repository;

import hackerton.caudex.domain.garden.entity.Garden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GardenRepository extends JpaRepository<Garden, Long> {
    Optional<Garden> findByUuid(String uuid);

    String uuid(String uuid);
}

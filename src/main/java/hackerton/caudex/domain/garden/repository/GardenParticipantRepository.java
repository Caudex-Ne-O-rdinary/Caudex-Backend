package hackerton.caudex.domain.garden.repository;

import hackerton.caudex.domain.garden.entity.GardenParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GardenParticipantRepository extends JpaRepository<GardenParticipant, Long> {
    Optional<GardenParticipant> findByUuid(String uuid);
}

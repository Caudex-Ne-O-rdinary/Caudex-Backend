package hackerton.caudex.domain.garden.repository;

import hackerton.caudex.domain.garden.entity.GardenParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GardenParticipantRepository extends JpaRepository<GardenParticipant, Long> {
}

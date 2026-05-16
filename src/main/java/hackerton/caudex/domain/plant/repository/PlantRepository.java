package hackerton.caudex.domain.plant.repository;

import hackerton.caudex.domain.garden.entity.Garden;
import hackerton.caudex.domain.plant.entity.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Long> {

    List<Plant> findAllByGardenParticipant_Garden(Garden garden);
}

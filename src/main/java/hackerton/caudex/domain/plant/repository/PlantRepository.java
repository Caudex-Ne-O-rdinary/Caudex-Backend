package hackerton.caudex.domain.plant.repository;

import hackerton.caudex.domain.plant.entity.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Long> {

    @Query("SELECT p FROM Plant p LEFT JOIN FETCH p.diaries WHERE p.id = :plantId")
    Optional<Plant> findByWithDiaries(@Param("plantId") Long plantId);
}

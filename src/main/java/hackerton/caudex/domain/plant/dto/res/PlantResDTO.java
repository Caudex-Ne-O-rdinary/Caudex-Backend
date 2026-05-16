package hackerton.caudex.domain.plant.dto.res;

import lombok.Builder;

public class PlantResDTO {

    @Builder
    public record PlantUploadDTO(
        Long plantId,
        String imageUrl
    ) {}
}

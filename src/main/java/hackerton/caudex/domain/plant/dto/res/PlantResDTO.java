package hackerton.caudex.domain.plant.dto.res;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class PlantResDTO {

    @Builder
    public record PlantUploadDTO(
        Long plantId,
        String imageUrl
    ) {}

    @Builder
    public record PlantDeatilDTO(
       Long plantId,
       String name,
       String imageUrl,
       String managementTip,
       List<DiaryDetailDTO> diaries
    ) {}

    @Builder
    public record DiaryDetailDTO(
       Long diaryId,
       String content,
       LocalDateTime createdAt
    ) {}
}

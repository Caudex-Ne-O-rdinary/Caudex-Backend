package hackerton.caudex.domain.garden.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class GardenResDto {

    // 정원 생성 화면 응답
    @Builder
    public record GardenCreatePageRes(
            List<TemplateDto> templates
    ){}

    @Builder
    public record TemplateDto(
            Long templateId,
            String name,
            String imageUrl
    ){}

    // 정원 생성 성공 응답
    @Builder
    public record SuccessCreateRes(
            String gardenUrl,
            String name,
            String templateUrl,
            LocalDateTime createdAt
    ){}

    // 생성된 정원 조회 응답
    @Builder
    public record GetGarden(
            String templateUrl,
            List<PlantDto> plants
    ){}

    @Builder
    public record PlantDto(
            Long plantId,
            Double ratioX,
            Double ratioY,
            Integer scale,
            String plantUrl
    ){}

    // 정원 식물 수정 응답
    @Builder
    public record PlantBatchDto(
            LocalDateTime modifiedAt
    ){}
}

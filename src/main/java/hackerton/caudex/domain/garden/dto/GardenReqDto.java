package hackerton.caudex.domain.garden.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class GardenReqDto {

    // 정원 생성
    public record CreateGardenReq(

        @NotBlank
        String name,

        @NotNull
        Long templateId
    ){}

    // 식물 옮기기 요청
    public record MovePlant(
            @NotNull
            Long plantId,

            @NotNull
            Double ratioX,

            @NotNull
            Double ratioY,

            @NotNull
            Double scale
    ){}
}

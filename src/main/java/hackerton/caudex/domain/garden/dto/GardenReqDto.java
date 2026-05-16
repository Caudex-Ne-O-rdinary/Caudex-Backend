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
}

package hackerton.caudex.domain.garden.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class GardenReqDto {

    // 정원 생성
    public record CreateGardenReq(

        @NotBlank(message = "닉네임을 입력해주세요.")
        @Size(max = 4, message = "닉네임은 최대 4자까지 입력 가능합니다.")
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

    // 초대 링크로 정원 참여 시 닉네임 입력
    public record JoinGardenReq(
            @NotBlank(message = "닉네임을 입력해주세요.")
            @Size(max = 4, message = "닉네임은 최대 4자까지 입력 가능합니다.")
            String name
    ){}
}

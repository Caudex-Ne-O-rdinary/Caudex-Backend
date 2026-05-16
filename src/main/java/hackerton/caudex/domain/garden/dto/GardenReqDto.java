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

        @NotNull(message = "템플릿 ID는 null일 수 없습니다.")
        Long templateId
    ){}

    // 식물 옮기기 요청
    public record MovePlant(
            @NotNull(message = "식물ID는 null일 수 없습니다.")
            Long plantId,

            @NotNull(message = "x 좌표는 null일 수 없습니다.")
            Double ratioX,

            @NotNull(message = "y 좌표는 null일 수 없습니다.")
            Double ratioY,

            @NotNull(message = "scale은 null일 수 없습니다.")
            Integer scale
    ){}

    // 초대 링크로 정원 참여 시 닉네임 입력
    public record JoinGardenReq(
            @NotBlank(message = "닉네임을 입력해주세요.")
            @Size(max = 4, message = "닉네임은 최대 4자까지 입력 가능합니다.")
            String name
    ){}
}

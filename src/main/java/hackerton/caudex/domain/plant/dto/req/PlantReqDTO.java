package hackerton.caudex.domain.plant.dto.req;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PlantReqDTO {
    public record PlantUploadDTO(
        @NotBlank(message="식물 이름을 입력해주세요.")
        String name,
        @NotBlank(message="관리 TIP을 입력해주세요.")
        String managementTip,
        @NotNull(message="식물의 X 좌표는 반드시 존재해야 합니다.")
        Double ratioX,
        @NotNull(message="식물의 Y 좌표는 반드시 존재해야 합니다.")
        Double ratioY,
        @NotNull(message="식물의 크기는 반드시 존재해야 합니다.")
        Integer scale
    ) {}
}

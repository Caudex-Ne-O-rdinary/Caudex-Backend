package hackerton.caudex.domain.plant.exception.code;

import hackerton.caudex.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum PlantSuccessCode implements BaseSuccessCode {

    GET_PLANT_INFO(HttpStatus.OK, "PLANT200_1", "식물 상세 정보 조회를 성공했습니다."),
    CREATE_PLANT_SUCCESS(HttpStatus.CREATED, "PLANT201_1", "식물이 성공적으로 등록되었습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}

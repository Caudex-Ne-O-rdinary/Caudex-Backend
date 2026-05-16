package hackerton.caudex.global.apiPayload.exception.code;

import hackerton.caudex.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum PlantSuccessCode implements BaseSuccessCode {

    CREATE_PLANT_SUCCESS(HttpStatus.CREATED, "PLANT201_1", "식물이 성공적으로 등록되었습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}

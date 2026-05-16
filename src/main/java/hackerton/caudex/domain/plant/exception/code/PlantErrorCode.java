package hackerton.caudex.domain.plant.exception.code;

import hackerton.caudex.global.apiPayload.code.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum PlantErrorCode implements BaseErrorCode {

    FILE_EMPTY(HttpStatus.BAD_REQUEST, "PLANT400_1", "업로드할 파일이 비어있습니다."),
    FILE_IO_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "PLANT500_1", "S3 파일 업로드 중 내부 입출력 에러가 발생했습니다."),

    PLANT_NOT_FOUND(HttpStatus.NOT_FOUND, "PLANT404_1", "존재하지 않는 식물입니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;

}

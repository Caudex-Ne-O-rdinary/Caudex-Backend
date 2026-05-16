package hackerton.caudex.domain.garden.exception.code;

import hackerton.caudex.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum GardenErrorCode implements BaseErrorCode {

    BAD_REQUEST(HttpStatus.BAD_REQUEST, "GARDEN400_1", "조회된 정원이 존재하지 않습니다."),
    TEMPLATE_NOT_FOUND(HttpStatus.NOT_FOUND, "GARDEN404_1", "조회된 정원 템플릿이 존재하지 않습니다."),
    GARDEN_NOT_FOUND(HttpStatus.NOT_FOUND, "GARDEN404_2", "조회된 정원 방이 존재하지 않습니다."),
    GARDEN_PARTICIPANT_NOT_FOUND(HttpStatus.NOT_FOUND, "GARDEN404_3", "존재하지 않는 정원 참여자입니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}

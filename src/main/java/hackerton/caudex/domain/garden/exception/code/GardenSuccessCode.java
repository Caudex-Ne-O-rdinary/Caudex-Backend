package hackerton.caudex.domain.garden.exception.code;

import hackerton.caudex.global.apiPayload.code.BaseErrorCode;
import hackerton.caudex.global.apiPayload.code.GeneralSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum GardenSuccessCode implements BaseErrorCode {

    OK(HttpStatus.OK, "GARDEN200_1", "성공적으로 정원이 조회되었습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}

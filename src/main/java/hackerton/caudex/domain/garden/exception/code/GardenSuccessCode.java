package hackerton.caudex.domain.garden.exception.code;

import hackerton.caudex.global.apiPayload.code.BaseErrorCode;
import hackerton.caudex.global.apiPayload.code.BaseSuccessCode;
import hackerton.caudex.global.apiPayload.code.GeneralSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum GardenSuccessCode implements BaseSuccessCode {

    CREATED(HttpStatus.CREATED, "GARDEN201_1", "나의 정원이 성공적으로 생성되었습니다."),
    OK(HttpStatus.OK, "GARDEN200_1", "나의 정원 생성하기 페이지가 성공적으로 조회되었습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}

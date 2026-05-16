package hackerton.caudex.domain.diary.exception.code;

import hackerton.caudex.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum DiarySuccessCode implements BaseSuccessCode {

    CREATED(HttpStatus.CREATED, "DIARY201_1", "다이어리 작성을 성공했습니다.");

    private final HttpStatus status;
    private final String message;
    private final String code;
}

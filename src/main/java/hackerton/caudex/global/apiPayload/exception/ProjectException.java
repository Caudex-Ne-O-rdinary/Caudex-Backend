package hackerton.caudex.global.apiPayload.exception;

import hackerton.caudex.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProjectException extends RuntimeException {
    private final BaseErrorCode baseErrorCode;
}

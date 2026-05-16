package hackerton.caudex.domain.garden.exception;

import hackerton.caudex.global.apiPayload.code.BaseErrorCode;
import hackerton.caudex.global.apiPayload.exception.ProjectException;

public class GardenException extends ProjectException {
  public GardenException(BaseErrorCode baseErrorCode) {
    super(baseErrorCode);
  }
}

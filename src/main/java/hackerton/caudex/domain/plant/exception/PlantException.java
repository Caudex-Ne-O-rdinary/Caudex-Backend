package hackerton.caudex.domain.plant.exception;

import hackerton.caudex.global.apiPayload.code.BaseErrorCode;
import hackerton.caudex.global.apiPayload.exception.ProjectException;

public class PlantException extends ProjectException {
    public PlantException(BaseErrorCode code) {
        super(code);
    }
}

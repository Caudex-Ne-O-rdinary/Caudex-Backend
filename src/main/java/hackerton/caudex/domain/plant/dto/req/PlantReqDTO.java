package hackerton.caudex.domain.plant.dto.req;

public class PlantReqDTO {
    public record PlantUploadDTO(
        String name,
        String managementTip,
        Double ratioX,
        Double ratioY
    ) {}
}

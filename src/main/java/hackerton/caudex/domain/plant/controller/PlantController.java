package hackerton.caudex.domain.plant.controller;

import hackerton.caudex.domain.plant.dto.req.PlantReqDTO;
import hackerton.caudex.domain.plant.dto.res.PlantResDTO;
import hackerton.caudex.domain.plant.service.command.PlantCommandService;
import hackerton.caudex.domain.plant.service.query.PlantQueryService;
import hackerton.caudex.global.apiPayload.ApiResponse;
import hackerton.caudex.domain.plant.exception.code.PlantSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/plants")
@RequiredArgsConstructor
public class PlantController {

    private final PlantCommandService plantCommandService;
    private final PlantQueryService plantQueryService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<PlantResDTO.PlantUploadDTO> uploadPlant(
            @RequestHeader("X-Device-UID") String deviceUid,
            @RequestHeader("X-OS-Type") String osType,
            @RequestPart("image") MultipartFile image,
            @RequestPart("dto") @Valid PlantReqDTO.PlantUploadDTO dto
            ) {
        PlantResDTO.PlantUploadDTO response = plantCommandService.uploadPlant(deviceUid, image, dto);
        return ApiResponse.onSuccess(PlantSuccessCode.CREATE_PLANT_SUCCESS, response);
    }

    @GetMapping("/{plantId}")
    public ApiResponse<PlantResDTO.PlantDeatilDTO> getPlantDetail(
            @PathVariable Long plantId
    ) {
        PlantResDTO.PlantDeatilDTO result = plantQueryService.getPlantDeatil(plantId);
        return ApiResponse.onSuccess(PlantSuccessCode.GET_PLANT_INFO, result);
    }
}

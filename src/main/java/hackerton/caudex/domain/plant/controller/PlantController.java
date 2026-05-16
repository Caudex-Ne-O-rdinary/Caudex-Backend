package hackerton.caudex.domain.plant.controller;

import hackerton.caudex.domain.plant.dto.req.PlantReqDTO;
import hackerton.caudex.domain.plant.dto.res.PlantResDTO;
import hackerton.caudex.domain.plant.service.command.PlantCommandService;
import hackerton.caudex.global.apiPayload.ApiResponse;
import hackerton.caudex.domain.plant.exception.code.PlantSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/plants")
@RequiredArgsConstructor
public class PlantController {

    private final PlantCommandService plantCommandService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<PlantResDTO.PlantUploadDTO> uploadPlant(
            @RequestPart("image") MultipartFile image,
            @RequestPart("dto") PlantReqDTO.PlantUploadDTO dto
            ) {
        PlantResDTO.PlantUploadDTO response = plantCommandService.uploadPlant(image, dto);
        return ApiResponse.onSuccess(PlantSuccessCode.CREATE_PLANT_SUCCESS, response);
    }
}

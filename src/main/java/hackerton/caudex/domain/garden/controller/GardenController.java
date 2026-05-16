package hackerton.caudex.domain.garden.controller;

import hackerton.caudex.domain.garden.dto.GardenReqDto;
import hackerton.caudex.domain.garden.dto.GardenResDto;
import hackerton.caudex.domain.garden.exception.code.GardenSuccessCode;
import hackerton.caudex.domain.garden.service.GardenService;
import hackerton.caudex.domain.plant.exception.code.PlantSuccessCode;
import hackerton.caudex.global.apiPayload.ApiResponse;
import hackerton.caudex.global.apiPayload.code.BaseSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/gardens")
public class GardenController {

    private final GardenService gardenService;

    /***
     * 함수 기능: 정원을 생성하는 페이지 (폼)
     * @return
     */
    @GetMapping
    public ApiResponse<GardenResDto.GardenCreatePageRes> getGardenPage(){
        BaseSuccessCode successCode = GardenSuccessCode.OK;

        return ApiResponse.onSuccess(successCode, gardenService.gardenCreatePage());
    }

    /***
     * 함수 기능: 정원을 생성하고 해당 URL을 사용자에게 제공합니다.
     * @param deviceUid
     * @param osType
     * @param dto
     * @return
     */
    @PostMapping
    public ApiResponse<GardenResDto.SuccessCreateRes> createGarden(
            @RequestHeader("X-Device-UID") String deviceUid,
            @RequestHeader("X-OS-Type") String osType,
            @RequestBody GardenReqDto.CreateGardenReq dto

    ){
        BaseSuccessCode successCode = GardenSuccessCode.CREATED;
        return ApiResponse.onSuccess(successCode, gardenService.createGarden(deviceUid, dto));
    }

    /***
     * 생성된 정원을 조회하는 기능
     * @param gardenUuid
     * @return
     */
    @GetMapping("/{gardenUuid}")
    public ApiResponse<GardenResDto.GetGarden> getGarden(
            @PathVariable String gardenUuid
    ){
        BaseSuccessCode successCode = GardenSuccessCode.OK_2;
        return ApiResponse.onSuccess(successCode, gardenService.joinGarden(gardenUuid));
    }

    @PatchMapping("/{gardenUuid}")
    public ApiResponse<GardenResDto.PlantBatchDto> movePlant(
            @PathVariable String gardenUuid,
            @RequestBody GardenReqDto.MovePlant dto
    ){
        BaseSuccessCode successCode = PlantSuccessCode.OK;
        return ApiResponse.onSuccess(successCode, gardenService.movePlant(gardenUuid, dto));
    }
}

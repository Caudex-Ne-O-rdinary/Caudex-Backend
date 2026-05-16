package hackerton.caudex.domain.garden.controller;

import hackerton.caudex.domain.garden.dto.GardenReqDto;
import hackerton.caudex.domain.garden.dto.GardenResDto;
import hackerton.caudex.domain.garden.exception.code.GardenSuccessCode;
import hackerton.caudex.domain.garden.service.GardenService;
import hackerton.caudex.domain.plant.exception.code.PlantSuccessCode;
import hackerton.caudex.global.apiPayload.ApiResponse;
import hackerton.caudex.global.apiPayload.code.BaseSuccessCode;
import jakarta.validation.Valid;
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
            @RequestBody @Valid GardenReqDto.CreateGardenReq dto

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

    /***
     * 정원 내 식물 위치 옮기는 기능
     * @param gardenUuid
     * @param dto
     * @return
     */
    @PatchMapping("/{gardenUuid}")
    public ApiResponse<GardenResDto.PlantBatchDto> movePlant(
            @PathVariable String gardenUuid,
            @RequestBody GardenReqDto.MovePlant dto
    ){
        BaseSuccessCode successCode = PlantSuccessCode.OK;
        return ApiResponse.onSuccess(successCode, gardenService.movePlant(gardenUuid, dto));
    }

    /***
     * 초대 링크를 받고, 닉네임을 입력하는 기능
     * @param gardenUuid
     * @param deviceUid
     * @param dto
     * @return
     */
    @PostMapping("/{gardenUuid}/join")
    public ApiResponse<String> joinGarden(
            @PathVariable String gardenUuid,
            @RequestHeader(value = "X-Device-UID") String deviceUid,
            @RequestBody @Valid GardenReqDto.JoinGardenReq dto
    ){
        gardenService.joinGardenParticipant(gardenUuid, deviceUid, dto);
        BaseSuccessCode successCode = GardenSuccessCode.OK;
        return ApiResponse.onSuccess(successCode, "정원에 성공적으로 참여했습니다.");
    }


}

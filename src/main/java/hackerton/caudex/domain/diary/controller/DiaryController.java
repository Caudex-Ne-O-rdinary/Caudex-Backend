package hackerton.caudex.domain.diary.controller;

import hackerton.caudex.domain.diary.dto.DiaryReqDto;
import hackerton.caudex.domain.diary.dto.DiaryResDto;
import hackerton.caudex.domain.diary.exception.code.DiarySuccessCode;
import hackerton.caudex.domain.diary.service.DiaryService;
import hackerton.caudex.global.apiPayload.ApiResponse;
import hackerton.caudex.global.apiPayload.code.BaseSuccessCode;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/plants")
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;

    @Operation(description = "다이어리 생성 API")
    @PostMapping("/{plantId}/diary")
    public ApiResponse<DiaryResDto.CreateDiaryRes> createDiary(
            @PathVariable Long plantId,
            @RequestBody @Valid DiaryReqDto.CreateDiary dto
    ){
        BaseSuccessCode successCode = DiarySuccessCode.CREATED;
        return ApiResponse.onSuccess(successCode, diaryService.createDiary(plantId, dto));
    }
}

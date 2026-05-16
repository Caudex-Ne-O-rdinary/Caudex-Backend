package hackerton.caudex.domain.diary.controller;

import hackerton.caudex.domain.diary.dto.DiaryReqDto;
import hackerton.caudex.domain.diary.dto.DiaryResDto;
import hackerton.caudex.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Diary", description = "식물 일기 관련 API")
public interface DiaryControllerDocs {

    @Operation(summary = "다이어리 생성 API", description = "특정 식물에 대한 새로운 다이어리(일기)를 작성합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "201",
                    description = "성공 - 다이어리 등록 완료",
                    content = @Content(
                            schema = @Schema(implementation = ApiResponse.class),
                            examples = @ExampleObject(
                                    name = "성공 예시",
                                    value = """
                                            {
                                              "isSuccess": true,
                                              "code": "DIARY201_1",
                                              "message": "일기가 성공적으로 등록되었습니다.",
                                              "result": {
                                                "diaryId": 1
                                              }
                                            }
                                            """
                            )
                    )
            )
            // 403 권한 없음 에러 케이스 삭제 완료
    })
    ApiResponse<DiaryResDto.CreateDiaryRes> createDiary(
            @Parameter(description = "일기를 작성할 식물의 고유 ID", required = true) Long plantId,
            // 기기 식별자(deviceUid) 파라미터 삭제 완료
            DiaryReqDto.CreateDiary dto
    );
}
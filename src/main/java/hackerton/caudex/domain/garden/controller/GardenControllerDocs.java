package hackerton.caudex.domain.garden.controller;

import hackerton.caudex.domain.garden.dto.GardenReqDto;
import hackerton.caudex.domain.garden.dto.GardenResDto;
import hackerton.caudex.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Garden", description = "정원 관련 API")
public interface GardenControllerDocs {

    @Operation(summary = "정원 생성 페이지 조회 API", description = "정원을 생성하기 위한 기본 템플릿 목록 등을 조회합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "성공 - 정원 생성 폼 데이터 반환",
                    content = @Content(
                            schema = @Schema(implementation = ApiResponse.class),
                            examples = @ExampleObject(
                                    name = "성공 예시",
                                    value = """
                                            {
                                              "isSuccess": true,
                                              "code": "GARDEN200_1",
                                              "message": "정원 생성 폼 조회 성공",
                                              "result": {
                                                "templates": [
                                                  { "templateId": 1, "name": "사막", "imageUrl": "https://s3.../desert.png" },
                                                  { "templateId": 2, "name": "남극", "imageUrl": "https://s3.../antarctic.png" }
                                                ]
                                              }
                                            }
                                            """
                            )
                    )
            )
    })
    ApiResponse<GardenResDto.GardenCreatePageRes> getGardenPage();

    @Operation(summary = "정원 생성 API", description = "새로운 정원을 생성하고 공유용 URL 정보를 반환합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "201",
                    description = "성공 - 정원 생성 완료",
                    content = @Content(
                            schema = @Schema(implementation = ApiResponse.class),
                            examples = @ExampleObject(
                                    name = "성공 예시",
                                    value = """
                                            {
                                              "isSuccess": true,
                                              "code": "GARDEN201_1",
                                              "message": "정원이 성공적으로 생성되었습니다.",
                                              "result": {
                                                "gardenUrl": "0bbaaa0b-7a11-4d31-9e8d-602280d32eaa",
                                                "name": "stri",
                                                "templateUrl": "https://caudex-storage.s3.ap-northeast-2.amazonaws.com/template1.png",
                                                "createdAt": "2026-05-17T06:21:34.7615276"
                                              }
                                            }
                                            """
                            )
                    )
            )
    })
    ApiResponse<GardenResDto.SuccessCreateRes> createGarden(
            @Parameter(description = "기기 고유 식별자", required = true) String deviceUid,
            @Parameter(description = "운영체제 타입 (예: ios, android)") String osType,
            GardenReqDto.CreateGardenReq dto
    );

    @Operation(summary = "생성된 정원 단건 조회 API", description = "공유받은 UUID를 통해 특정 팀 정원의 배경과 식물 배치 정보를 조회합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "성공 - 정원 정보 반환",
                    content = @Content(
                            schema = @Schema(implementation = ApiResponse.class),
                            examples = @ExampleObject(
                                    name = "성공 예시",
                                    value = """
                                            {
                                              "isSuccess": true,
                                              "code": "GARDEN200_2",
                                              "message": "팀 정원이 성공적으로 조회되었습니다.",
                                              "result": {
                                                "templateUrl": "https://caudex-bucket.s3.../desert.png",
                                                "plants": [
                                                  {
                                                    "plantId": 1,
                                                    "ratioX": 2.50,
                                                    "ratioY": 1.80,
                                                    "plantUrl": "https://caudex-bucket.s3.../plant1.png"
                                                  }
                                                ]
                                              }
                                            }
                                            """
                            )
                    )
            )
    })
    ApiResponse<GardenResDto.GetGarden> getGarden(
            @Parameter(description = "정원 고유 UUID", required = true) String gardenUuid
    );

    @Operation(summary = "정원 내 식물 위치 수정 API", description = "정원에 배치된 특정 식물의 X, Y 좌표(비율)를 변경합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "성공 - 식물 위치 변경 완료",
                    content = @Content(
                            schema = @Schema(implementation = ApiResponse.class),
                            examples = @ExampleObject(
                                    name = "성공 예시",
                                    value = """
                                            {
                                              "isSuccess": true,
                                              "code": "GARDEN201_2",
                                              "message": "정원이 성공적으로 수정되었습니다.",
                                              "result": {
                                                "modifiedAt": "2026-10-20T10:10:00"
                                              }
                                            }
                                            """
                            )
                    )
            )
    })
    ApiResponse<GardenResDto.PlantBatchDto> movePlant(
            @Parameter(description = "정원 고유 UUID", required = true) String gardenUuid,
            GardenReqDto.MovePlant dto
    );

    @Operation(summary = "정원 초대 링크 참여 API", description = "초대 링크를 타고 들어온 사용자가 닉네임을 입력하여 정원 참여자로 등록됩니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "성공 - 정원 참여자 등록 완료",
                    content = @Content(
                            schema = @Schema(implementation = ApiResponse.class),
                            examples = @ExampleObject(
                                    name = "성공 예시",
                                    value = """
                                            {
                                              "isSuccess": true,
                                              "code": "GARDEN200",
                                              "message": "정원에 성공적으로 참여했습니다.",
                                              "result": "정원에 성공적으로 참여했습니다."
                                            }
                                            """
                            )
                    )
            )
    })
    ApiResponse<String> joinGarden(
            @Parameter(description = "정원 고유 UUID", required = true) String gardenUuid,
            @Parameter(description = "기기 고유 식별자", required = true) String deviceUid,
            GardenReqDto.JoinGardenReq dto
    );
}
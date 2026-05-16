package hackerton.caudex.domain.plant.controller;

import hackerton.caudex.domain.plant.dto.req.PlantReqDTO;
import hackerton.caudex.domain.plant.dto.res.PlantResDTO;
import hackerton.caudex.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "Plant", description = "식물 관련 API")
public interface PlantControllerDocs {

    /***
     * 1. 식물 업로드 (이미지 + JSON DTO)
     */
    @Operation(summary = "식물 등록 API", description = "식물의 이미지 파일과 상세 정보(이름, 관리 팁 등)를 함께 업로드하여 등록합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "201",
                    description = "성공 - 식물 등록 완료",
                    content = @Content(
                            schema = @Schema(implementation = ApiResponse.class),
                            examples = @ExampleObject(
                                    name = "성공 예시",
                                    value = """
                                            {
                                              "isSuccess": true,
                                              "code": "PLANT201_1",
                                              "message": "식물이 성공적으로 등록되었습니다.",
                                              "result": {
                                                "plantId": 1,
                                                "imageUrl": "https://caudex-storage.s3.ap-northeast-2.amazonaws.com/d86b1603-e343-4bfb-a609-31dd972a77cc_2026-03-23.png"
                                              }
                                            }
                                            """
                            )
                    )
            )
    })
    ApiResponse<PlantResDTO.PlantUploadDTO> uploadPlant(
            @Parameter(description = "업로드할 식물 이미지 파일", required = true) MultipartFile image,
            @Parameter(description = "식물 등록 정보 (이름, 관리 팁 등)", required = true) PlantReqDTO.PlantUploadDTO dto
    );


    /***
     * 2. 식물 상세 조회
     */
    @Operation(summary = "식물 상세 조회 API", description = "특정 식물의 상세 정보(이름, 이미지, 관리 팁 등)를 조회합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "성공 - 식물 상세 정보 반환",
                    content = @Content(
                            schema = @Schema(implementation = ApiResponse.class),
                            examples = @ExampleObject(
                                    name = "성공 예시",
                                    value = """
                                            {
                                              "isSuccess": true,
                                              "code": "PLANT200_1",
                                              "message": "식물 상세 정보 조회를 성공했습니다.",
                                              "result": {
                                                "plantId": 7,
                                            	  "name": "파키포디움 그라실리우스",
                                            	  "imageUrl": "https://s3.../gracilius.png",
                                            	  "managementTip": "성장기에는 흙이 마르면 듬뿍, 휴면기에는 단수하세요.",
                                            
                                            	  "diaries": [
                                            	    {
                                            	      "diaryId": 101,
                                            	      "content": "오늘 첫 화분 갈이를 해주었다. 뿌리가 건강함.",
                                            	      "createdAt": "2026-05-10T16:32:04"\s
                                            	    },
                                            	    {
                                            	      "diaryId": 102,
                                            	      "content": "부쩍 날씨가 따뜻해져서 베란다 명당으로 옮김.",
                                            	      "createdAt": "2026-05-16T13:17:48"
                                            	    }
                                            	  ]
                                              }
                                            }
                                            """
                            )
                    )
            )
    })
    ApiResponse<PlantResDTO.PlantDeatilDTO> getPlantDetail(
            @Parameter(description = "조회할 식물의 고유 ID", required = true) Long plantId
    );
}
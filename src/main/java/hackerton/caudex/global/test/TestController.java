package hackerton.caudex.global.test;

import hackerton.caudex.global.apiPayload.ApiResponse;
import hackerton.caudex.global.apiPayload.code.BaseSuccessCode;
import hackerton.caudex.global.apiPayload.code.GeneralSuccessCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping
    public ApiResponse<String> UuidController(
            @RequestHeader(value = "X-Device-UID") String deviceUid,
            @RequestHeader(value = "X-OS-Type") String osType
    ){
        String responseBody = String.format("Device UID: %s, OS Type: %s", deviceUid, osType);
        BaseSuccessCode code = GeneralSuccessCode.OK;

        return ApiResponse.onSuccess(code, responseBody);
    }
}

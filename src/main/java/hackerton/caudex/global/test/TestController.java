package hackerton.caudex.global.test;

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
    public ResponseEntity<String> UuidController(
            @RequestHeader(value = "X-Device-UID") String deviceUid,
            @RequestHeader(value = "X-OS-Type") String osType
    ){
        String responseBody = String.format("Device UID: %s, OS Type: %s", deviceUid, osType);

        return ResponseEntity.status(HttpStatus.OK)
                .body(responseBody);
    }
}

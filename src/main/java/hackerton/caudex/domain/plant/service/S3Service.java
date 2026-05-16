package hackerton.caudex.domain.plant.service;

import hackerton.caudex.domain.plant.exception.PlantException;
import hackerton.caudex.domain.plant.exception.code.PlantErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final S3Client s3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String uploadImage(MultipartFile image) {
        if(image.isEmpty()) {
            throw new PlantException(PlantErrorCode.FILE_EMPTY);
        }

        String originalFileName = image.getOriginalFilename();
        String s3FileName = UUID.randomUUID() + "_" + originalFileName;

        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucket)
                    .key(s3FileName)
                    .contentType(image.getContentType())
                    .build();

            s3Client.putObject(putObjectRequest,
                    RequestBody.fromInputStream(image.getInputStream(), image.getSize())
            );

            return s3Client.utilities().getUrl(GetUrlRequest.builder()
                            .bucket(bucket)
                            .key(s3FileName)
                            .build()).toString();
        } catch (IOException e) {
            throw new PlantException(PlantErrorCode.FILE_IO_ERROR);
        }
    }
}

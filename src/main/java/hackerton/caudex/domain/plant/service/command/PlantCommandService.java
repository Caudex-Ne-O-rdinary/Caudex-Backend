package hackerton.caudex.domain.plant.service.command;

import hackerton.caudex.domain.plant.dto.req.PlantReqDTO;
import hackerton.caudex.domain.plant.dto.res.PlantResDTO;
import hackerton.caudex.domain.plant.entity.Plant;
import hackerton.caudex.domain.plant.repository.PlantRepository;
import hackerton.caudex.domain.plant.service.S3Service;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional
public class PlantCommandService {

    private final PlantRepository plantRepository;
    private final S3Service s3Service;

    public PlantResDTO.PlantUploadDTO uploadPlant(
            MultipartFile image,
            PlantReqDTO.PlantUploadDTO dto
    ){
        String uploadedImageUrl = s3Service.uploadImage(image);

        Plant plant = Plant.builder()
                .name(dto.name())
                .managementTip(dto.managementTip())
                .ratioX(dto.ratioX())
                .ratioY(dto.ratioY())
                .scale(dto.scale())
                .imageUrl(uploadedImageUrl)
                .build();

        Plant savaedPlant = plantRepository.save(plant);

        return PlantResDTO.PlantUploadDTO.builder()
                .plantId(savaedPlant.getId())
                .imageUrl(savaedPlant.getImageUrl())
                .build();
    }
}

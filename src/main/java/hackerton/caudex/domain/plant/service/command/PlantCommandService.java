package hackerton.caudex.domain.plant.service.command;

import hackerton.caudex.domain.garden.entity.GardenParticipant;
import hackerton.caudex.domain.garden.exception.GardenException;
import hackerton.caudex.domain.garden.exception.code.GardenErrorCode;
import hackerton.caudex.domain.garden.repository.GardenParticipantRepository;
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
    private final GardenParticipantRepository gardenParticipantRepository;
    private final S3Service s3Service;

    public PlantResDTO.PlantUploadDTO uploadPlant(
            String deviceUid,
            MultipartFile image,
            PlantReqDTO.PlantUploadDTO dto
    ){
        String uploadedImageUrl = s3Service.uploadImage(image);

        GardenParticipant participant = gardenParticipantRepository.findByUuid(deviceUid)
                .orElseThrow(() -> new GardenException(GardenErrorCode.GARDEN_PARTICIPANT_NOT_FOUND));

        Plant plant = Plant.builder()
                .name(dto.name())
                .managementTip(dto.managementTip())
                .ratioX(dto.ratioX())
                .ratioY(dto.ratioY())
                .scale(dto.scale())
                .imageUrl(uploadedImageUrl)
                .gardenParticipant(participant)
                .build();

        Plant savaedPlant = plantRepository.save(plant);

        return PlantResDTO.PlantUploadDTO.builder()
                .plantId(savaedPlant.getId())
                .imageUrl(savaedPlant.getImageUrl())
                .build();
    }
}

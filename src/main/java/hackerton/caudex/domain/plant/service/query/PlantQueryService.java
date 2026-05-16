package hackerton.caudex.domain.plant.service.query;

import hackerton.caudex.domain.plant.dto.res.PlantResDTO;
import hackerton.caudex.domain.plant.entity.Plant;
import hackerton.caudex.domain.plant.exception.PlantException;
import hackerton.caudex.domain.plant.exception.code.PlantErrorCode;
import hackerton.caudex.domain.plant.repository.PlantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlantQueryService {

    private final PlantRepository plantRepository;

    public PlantResDTO.PlantDeatilDTO getPlantDeatil(Long plantId) {
        Plant plant = plantRepository.findByWithDiaries(plantId)
                .orElseThrow(() -> new PlantException(PlantErrorCode.PLANT_NOT_FOUND));

        List<PlantResDTO.DiaryDetailDTO> diarieDTO = plant.getDiaries().stream()
                .map(diary -> PlantResDTO.DiaryDetailDTO.builder()
                        .diaryId(diary.getId())
                        .content(diary.getContent())
                        .createdAt(diary.getCreatedAt())
                        .build())
                .toList();

        return PlantResDTO.PlantDeatilDTO.builder()
                .plantId(plant.getId())
                .name(plant.getName())
                .imageUrl(plant.getImageUrl())
                .managementTip(plant.getManagementTip())
                .diaries(diarieDTO)
                .build();
    }
}

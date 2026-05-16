package hackerton.caudex.domain.garden.service;

import hackerton.caudex.domain.garden.dto.GardenReqDto;
import hackerton.caudex.domain.garden.dto.GardenResDto;
import hackerton.caudex.domain.garden.entity.Garden;
import hackerton.caudex.domain.garden.entity.GardenParticipant;
import hackerton.caudex.domain.garden.entity.Template;
import hackerton.caudex.domain.garden.exception.GardenException;
import hackerton.caudex.domain.garden.exception.code.GardenErrorCode;
import hackerton.caudex.domain.garden.repository.GardenParticipantRepository;
import hackerton.caudex.domain.garden.repository.GardenRepository;
import hackerton.caudex.domain.garden.repository.TemplateRepository;
import hackerton.caudex.domain.plant.entity.Plant;
import hackerton.caudex.domain.plant.repository.PlantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Transactional
public class GardenService {

    private final GardenRepository gardenRepository;
    private final GardenParticipantRepository gardenParticipantRepository;
    private final TemplateRepository templateRepository;
    private final PlantRepository plantRepository;

    /***
     * 함수 기능: 정원 생성 화면 조회
     * @return
     */
    public GardenResDto.GardenCreatePageRes gardenCreatePage(){

        List<Template> templates = templateRepository.findAll();

        List<GardenResDto.TemplateDto> templateDtos = templates.stream()
                .map(template -> GardenResDto.TemplateDto.builder()
                        .templateId(template.getId())
                        .name(template.getName())
                        .imageUrl(template.getImageUrl())
                        .build())
                .toList();

        return GardenResDto.GardenCreatePageRes.builder()
                .templates(templateDtos)
                .build();
    }

    /***
     * 함수 기능: 정원 생성 기능
     * @param deviceUuid
     * @param dto
     * @return
     */
    public GardenResDto.SuccessCreateRes createGarden(String deviceUuid, GardenReqDto.CreateGardenReq dto){

        // 요청된 템플릿 ID가 DB에 존재하는 지 확인
        Template template = templateRepository.findById(dto.templateId())
                .orElseThrow(() -> new GardenException(GardenErrorCode.TEMPLATE_NOT_FOUND));

        // 정원 고유 URL 생성
        String generatedUuid = UUID.randomUUID().toString();

        // Garden 엔티티 생성 및 저장
        Garden garden = Garden.builder()
                .template(template)
                .uuid(generatedUuid)
                .build();
        Garden savedGarden = gardenRepository.save(garden);

        // GardenParticipant 엔티티 생성 및 저장
        GardenParticipant gardenParticipant = GardenParticipant.builder()
                .garden(savedGarden)
                .uuid(deviceUuid)
                .nickname(dto.name())
                .build();
        gardenParticipantRepository.save(gardenParticipant);

        return GardenResDto.SuccessCreateRes.builder()
                .gardenUrl(generatedUuid)
                .name(gardenParticipant.getNickname())
                .templateUrl(template.getImageUrl())
                .createdAt(LocalDateTime.now())
                .build();
    }

    /***
     * 생성된 정원을 조회한다.
     * @param gardenUuid
     * @return
     */
    public GardenResDto.GetGarden joinGarden(String gardenUuid){

        Garden garden = gardenRepository.findByUuid(gardenUuid)
                .orElseThrow(() -> new GardenException(GardenErrorCode.GARDEN_NOT_FOUND));

        String templateUrl = garden.getTemplate().getImageUrl();

        // 해당 정원에 심어진 모든 식물 조회
        List<Plant> plants = plantRepository.findAllByGardenParticipant_Garden(garden);

        List<GardenResDto.PlantDto> plantDtos = plants.stream()
                .map(plant -> GardenResDto.PlantDto.builder()
                        .plantId(plant.getId())
                        .ratioX(plant.getRatioX())
                        .ratioY(plant.getRatioY())
                        .plantUrl(plant.getImageUrl())
                        .build())
                .toList();

        return GardenResDto.GetGarden.builder()
                .templateUrl(templateUrl)
                .plants(plantDtos)
                .build();
    }

    public GardenResDto.PlantBatchDto movePlant(GardenReqDto.MovePlant dto){
        Optional<Plant> byId = plantRepository.findById(dto.plantId());
    }
}

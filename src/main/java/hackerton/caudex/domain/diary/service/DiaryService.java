package hackerton.caudex.domain.diary.service;

import hackerton.caudex.domain.diary.dto.DiaryReqDto;
import hackerton.caudex.domain.diary.dto.DiaryResDto;
import hackerton.caudex.domain.diary.entity.Diary;
import hackerton.caudex.domain.diary.repository.DiaryRepository;
import hackerton.caudex.domain.plant.entity.Plant;
import hackerton.caudex.domain.plant.exception.PlantException;
import hackerton.caudex.domain.plant.exception.code.PlantErrorCode;
import hackerton.caudex.domain.plant.repository.PlantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final PlantRepository plantRepository;

    public DiaryResDto.CreateDiaryRes createDiary(Long plantId, DiaryReqDto.CreateDiary dto){

        Plant plant = plantRepository.findById(plantId)
                .orElseThrow(() -> new PlantException(PlantErrorCode.PLANT_NOT_FOUND));

        Diary diary = Diary.builder()
                .content(dto.context())
                .plant(plant)
                .build();

        Diary savedDiary = diaryRepository.save(diary);
        log.info("일기가 등록되었습니다. 내용: + {}", savedDiary.getContent());

        return DiaryResDto.CreateDiaryRes.builder()
                .diaryId(diary.getId())
                .build();
    }
}

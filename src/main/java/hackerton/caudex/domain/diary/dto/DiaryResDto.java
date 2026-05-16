package hackerton.caudex.domain.diary.dto;

import lombok.Builder;

public class DiaryResDto {

    // 다이어리 생성 응답
    @Builder
    public record CreateDiaryRes(
            Long diaryId
    ){}
}

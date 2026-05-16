package hackerton.caudex.domain.diary.dto;

import lombok.Builder;

public class DiaryReqDto {

    // 다이어리 생성
    @Builder
    public record CreateDiary(
            String content
    ){}
}

package hackerton.caudex.domain.diary.dto;

import jakarta.validation.constraints.Size;
import lombok.Builder;

public class DiaryReqDto {

    // 다이어리 생성
    @Builder
    public record CreateDiary(

            @Size(max = 100, message = "최대 100자까지 입력할 수 있어요.")
            String context
    ){}
}

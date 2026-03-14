package ge.tbc.testautomation.api.data.models.response.quiz;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import java.util.List;


@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetChildQuestHistoryResponseDto {
    @JsonProperty("childId")   private String childId;
    @JsonProperty("childName") private String childName;
    @JsonProperty("quests")    private List<QuestHistoryDto> quests;
    @JsonProperty("quizzes")   private List<QuizSessionResponse> quizzes;
}
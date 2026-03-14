package ge.tbc.testautomation.api.data.models.response.quiz;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnswerResponse {
    @JsonProperty("questionId")       private String questionId;
    @JsonProperty("selectedOptionId") private String selectedOptionId;
    @JsonProperty("isCorrect")        private Boolean isCorrect;
    @JsonProperty("correctOptionId")  private String correctOptionId;
}
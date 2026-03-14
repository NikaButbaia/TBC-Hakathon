package ge.tbc.testautomation.api.data.models.response.quiz;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuizOptionResponse {
    @JsonProperty("id")         private String id;
    @JsonProperty("optionText") private String optionText;
    @JsonProperty("isCorrect")  private Boolean isCorrect;
}
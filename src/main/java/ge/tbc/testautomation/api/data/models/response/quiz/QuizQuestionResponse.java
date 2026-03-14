package ge.tbc.testautomation.api.data.models.response.quiz;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuizQuestionResponse {
    @JsonProperty("id")           private String id;
    @JsonProperty("questionText") private String questionText;
    @JsonProperty("options")      private List<QuizOptionResponse> options;
}
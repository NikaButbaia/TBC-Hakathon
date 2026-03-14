package ge.tbc.testautomation.api.data.models.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CreateChildDto(
        String childId,
        String title,
        String description,
        double rewardAmount,
        List<QuizQuestionRequest> questions
) {
    public record QuizQuestionRequest(
            String questionText,
            List<QuizOptionRequest> options
    ) {}

    public record QuizOptionRequest(
            String optionText,
            boolean isCorrect
    ) {}
}
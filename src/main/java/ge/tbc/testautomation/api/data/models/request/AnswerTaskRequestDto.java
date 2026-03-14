package ge.tbc.testautomation.api.data.models.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record AnswerTaskRequestDto(
        List<SubmittedAnswer> answers
) {
    public record SubmittedAnswer(
            String questionId,
            String selectedOptionId
    ) {}
}
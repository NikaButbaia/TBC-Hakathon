package ge.tbc.testautomation.api.data.models.request;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CreateParentDto(
        String title,
        String description,
        double rewardAmount,
        String deadline
) {}
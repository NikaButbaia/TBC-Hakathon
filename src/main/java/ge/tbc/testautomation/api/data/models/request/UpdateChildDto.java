package ge.tbc.testautomation.api.data.models.request;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UpdateChildDto(
        String title,
        String description,
        double rewardAmount
) {}
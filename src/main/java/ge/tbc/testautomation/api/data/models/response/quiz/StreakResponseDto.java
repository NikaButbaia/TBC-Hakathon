package ge.tbc.testautomation.api.data.models.response.quiz;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class StreakResponseDto {
    @JsonProperty("currentStreak")   private Integer currentStreak;
    @JsonProperty("longestStreak")   private Integer longestStreak;
    @JsonProperty("lastCompletedAt") private String lastCompletedAt;
}
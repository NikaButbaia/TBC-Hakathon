package ge.tbc.testautomation.api.data.models.response.quiz;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import ge.tbc.testautomation.api.data.models.response.quiz.QuestHistoryDto;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuestHistoryDto {
    @JsonProperty("id")           private String id;
    @JsonProperty("childId")      private String childId;
    @JsonProperty("title")        private String title;
    @JsonProperty("description")  private String description;
    @JsonProperty("rewardAmount") private Double rewardAmount;
    @JsonProperty("type")         private String type;
    @JsonProperty("status")       private String status;
    @JsonProperty("deadline")     private String deadline;
    @JsonProperty("isActive")     private Boolean isActive;
}
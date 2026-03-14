package ge.tbc.testautomation.api.data.models.response.child;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChildResponseDto {
    @JsonProperty("childId")         private String childId;
    @JsonProperty("childName")       private String childName;
    @JsonProperty("currentBalance")  private Double currentBalance;
    @JsonProperty("totalEarned")     private Double totalEarned;
    @JsonProperty("level")           private Integer level;
    @JsonProperty("nextLevelXp")     private Double nextLevelXp;
    @JsonProperty("earningsHistory") private List<EarningItem> earningsHistory;

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class EarningItem {
        @JsonProperty("amount")    private Double amount;
        @JsonProperty("source")    private String source;
        @JsonProperty("createdAt") private String createdAt;
    }
}
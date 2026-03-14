package ge.tbc.testautomation.api.data.models.response.parent;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import ge.tbc.testautomation.api.data.models.response.child.ChildResponseDto;
import lombok.Getter;
import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParentResponseDto {
    @JsonProperty("role")       private String role;
    @JsonProperty("parentData") private ParentWalletData parentData;
    @JsonProperty("childData")  private ChildWalletData childData;

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ParentWalletData {
        @JsonProperty("balance")       private Double balance;
        @JsonProperty("lockedBalance") private Double lockedBalance;
        @JsonProperty("children")      private List<ChildResponseDto> children;
    }

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ChildWalletData {
        @JsonProperty("balance")         private Double balance;
        @JsonProperty("totalEarned")     private Double totalEarned;
        @JsonProperty("earningsHistory") private List<ChildResponseDto.EarningItem> earningsHistory;
        @JsonProperty("level")           private Integer level;
        @JsonProperty("nextLevelXp")     private Double nextLevelXp;
    }
}
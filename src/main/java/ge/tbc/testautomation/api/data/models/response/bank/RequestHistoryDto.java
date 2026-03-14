package ge.tbc.testautomation.api.data.models.response.bank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestHistoryDto {
    @JsonProperty("id")        private String id;
    @JsonProperty("amount")    private Double amount;
    @JsonProperty("source")    private String source;
    @JsonProperty("status")    private String status;
    @JsonProperty("createdAt") private String createdAt;
}
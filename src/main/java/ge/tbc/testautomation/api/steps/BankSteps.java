package ge.tbc.testautomation.api.steps;

import ge.tbc.testautomation.api.client.BankAPI;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static ge.tbc.testautomation.api.client.BaseAPIClient.waitBetweenRequests;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class BankSteps {

    private final BankAPI api = new BankAPI();
    private Response rawResponse;

    public Response getRawResponse() { return rawResponse; }

    @Step("Get wallet dashboard as parent")
    public BankSteps getParentDashboard() {
        waitBetweenRequests();
        this.rawResponse = api.getParentDashboard();
        return this;
    }

    @Step("Get wallet dashboard as child")
    public BankSteps getChildDashboard() {
        waitBetweenRequests();
        this.rawResponse = api.getChildDashboard();
        return this;
    }

    @Step("Get wallet dashboard without auth")
    public BankSteps getDashboardNoAuth() {
        waitBetweenRequests();
        this.rawResponse = api.getDashboardNoAuth();
        return this;
    }

    // ─── Validators ───

    @Step("Validate status code is {expectedCode}")
    public BankSteps validateStatusCode(int expectedCode) {
        assertThat("Status code", rawResponse.statusCode(), equalTo(expectedCode));
        return this;
    }

    @Step("Validate Content-Type is application/json")
    public BankSteps validateContentTypeIsJson() {
        assertThat("Content-Type", rawResponse.contentType(), containsString("application/json"));
        return this;
    }

    @Step("Validate response time is under {maxMillis}ms")
    public BankSteps validateResponseTimeIsUnder(long maxMillis) {
        assertThat(String.format("Response time %dms exceeds %dms", rawResponse.getTime(), maxMillis),
                rawResponse.getTime(), lessThan(maxMillis));
        return this;
    }

    @Step("Validate response body is not empty")
    public BankSteps validateResponseBodyIsNotEmpty() {
        assertThat("Response body", rawResponse.getBody().asString(), not(emptyString()));
        return this;
    }

    @Step("Validate role is not null")
    public BankSteps validateRoleIsNotNull() {
        assertThat("role", rawResponse.jsonPath().getString("role"), notNullValue());
        return this;
    }

    @Step("Validate parentData is not null")
    public BankSteps validateParentDataIsNotNull() {
        assertThat("parentData", rawResponse.jsonPath().get("parentData"), notNullValue());
        return this;
    }

    @Step("Validate parentData.balance is not null")
    public BankSteps validateParentBalanceIsNotNull() {
        assertThat("parentData.balance", rawResponse.jsonPath().get("parentData.balance"), notNullValue());
        return this;
    }

    @Step("Validate parent balance is non-negative")
    public BankSteps validateParentBalanceIsNotNegative() {
        Double balance = rawResponse.jsonPath().getDouble("parentData.balance");
        assertThat("Parent balance >= 0", balance, greaterThanOrEqualTo(0.0));
        return this;
    }

    @Step("Validate childData is not null")
    public BankSteps validateChildDataIsNotNull() {
        assertThat("childData", rawResponse.jsonPath().get("childData"), notNullValue());
        return this;
    }

    @Step("Validate childData.balance is not null")
    public BankSteps validateChildBalanceIsNotNull() {
        assertThat("childData.balance", rawResponse.jsonPath().get("childData.balance"), notNullValue());
        return this;
    }

    @Step("Validate child balance is non-negative")
    public BankSteps validateChildBalanceIsNotNegative() {
        Double balance = rawResponse.jsonPath().getDouble("childData.balance");
        assertThat("Child balance >= 0", balance, greaterThanOrEqualTo(0.0));
        return this;
    }

    @Step("Validate children array is not null")
    public BankSteps validateChildrenArrayIsNotNull() {
        assertThat("parentData.children", rawResponse.jsonPath().get("parentData.children"), notNullValue());
        return this;
    }

    @Step("Validate child level is not null")
    public BankSteps validateChildLevelIsNotNull() {
        assertThat("childData.level", rawResponse.jsonPath().get("childData.level"), notNullValue());
        return this;
    }

    @Step("Validate child nextLevelXp is not null")
    public BankSteps validateChildNextLevelXpIsNotNull() {
        assertThat("childData.nextLevelXp", rawResponse.jsonPath().get("childData.nextLevelXp"), notNullValue());
        return this;
    }
}
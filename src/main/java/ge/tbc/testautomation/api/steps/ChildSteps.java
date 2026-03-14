package ge.tbc.testautomation.api.steps;

import ge.tbc.testautomation.api.client.ChildAPI;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static ge.tbc.testautomation.api.client.BaseAPIClient.waitBetweenRequests;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ChildSteps {

    private final ChildAPI api = new ChildAPI();
    private Response rawResponse;

    public Response getRawResponse() { return rawResponse; }

    @Step("Child completes quest id={questId}")
    public ChildSteps completeQuest(String questId) {
        waitBetweenRequests();
        this.rawResponse = api.completeQuest(questId);
        return this;
    }

    @Step("Child gets all quests")
    public ChildSteps getAllQuests() {
        waitBetweenRequests();
        this.rawResponse = api.getAllQuests();
        return this;
    }

    @Step("Child gets quest by id={questId}")
    public ChildSteps getQuestById(String questId) {
        waitBetweenRequests();
        this.rawResponse = api.getQuestById(questId);
        return this;
    }

    @Step("Get current child user")
    public ChildSteps getCurrentUser() {
        waitBetweenRequests();
        this.rawResponse = api.getCurrentUser();
        return this;
    }

    @Step("Get child family members")
    public ChildSteps getFamilyMembers() {
        waitBetweenRequests();
        this.rawResponse = api.getFamilyMembers();
        return this;
    }

    @Step("Get wallet dashboard as child")
    public ChildSteps getWalletDashboard() {
        waitBetweenRequests();
        this.rawResponse = api.getWalletDashboard();
        return this;
    }

    // ─── Validators ───

    @Step("Validate status code is {expectedCode}")
    public ChildSteps validateStatusCode(int expectedCode) {
        assertThat("Status code", rawResponse.statusCode(), equalTo(expectedCode));
        return this;
    }

    @Step("Validate response body is not empty")
    public ChildSteps validateResponseBodyIsNotEmpty() {
        assertThat("Response body", rawResponse.getBody().asString(), not(emptyString()));
        return this;
    }

    @Step("Validate Content-Type is application/json")
    public ChildSteps validateContentTypeIsJson() {
        assertThat("Content-Type", rawResponse.contentType(), containsString("application/json"));
        return this;
    }

    @Step("Validate response time is under {maxMillis}ms")
    public ChildSteps validateResponseTimeIsUnder(long maxMillis) {
        assertThat(String.format("Response time %dms exceeds %dms", rawResponse.getTime(), maxMillis),
                rawResponse.getTime(), lessThan(maxMillis));
        return this;
    }

    @Step("Validate childData is not null")
    public ChildSteps validateChildDataIsNotNull() {
        assertThat("childData", rawResponse.jsonPath().get("childData"), notNullValue());
        return this;
    }

    @Step("Validate child balance is not null")
    public ChildSteps validateChildBalanceIsNotNull() {
        assertThat("childData.balance", rawResponse.jsonPath().get("childData.balance"), notNullValue());
        return this;
    }

    @Step("Validate child balance is not negative")
    public ChildSteps validateChildBalanceIsNotNegative() {
        Double balance = rawResponse.jsonPath().getDouble("childData.balance");
        assertThat("Child balance must be >= 0", balance, greaterThanOrEqualTo(0.0));
        return this;
    }

    @Step("Validate child level is not null")
    public ChildSteps validateChildLevelIsNotNull() {
        assertThat("childData.level", rawResponse.jsonPath().get("childData.level"), notNullValue());
        return this;
    }

    @Step("Validate child nextLevelXp is not null")
    public ChildSteps validateChildNextLevelXpIsNotNull() {
        assertThat("childData.nextLevelXp", rawResponse.jsonPath().get("childData.nextLevelXp"), notNullValue());
        return this;
    }

    @Step("Validate role is not null")
    public ChildSteps validateRoleIsNotNull() {
        assertThat("role", rawResponse.jsonPath().getString("role"), notNullValue());
        return this;
    }
}
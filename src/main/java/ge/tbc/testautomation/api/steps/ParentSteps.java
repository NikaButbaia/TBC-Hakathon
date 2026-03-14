package ge.tbc.testautomation.api.steps;

import ge.tbc.testautomation.api.client.ParentAPI;
import ge.tbc.testautomation.api.data.constants.Constants;
import ge.tbc.testautomation.api.data.models.request.CreateRequestDto;
import ge.tbc.testautomation.api.data.models.request.UpdateParentDto;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import static ge.tbc.testautomation.api.client.BaseAPIClient.waitBetweenRequests;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ParentSteps {

    private static final DateTimeFormatter ISO = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    private final ParentAPI api = new ParentAPI();
    private Response rawResponse;
    private String lastQuestId;

    public Response getRawResponse() { return rawResponse; }
    public String getLastQuestId()   { return lastQuestId; }

    // ─── Quest Actions ───

    @Step("Get all quests")
    public ParentSteps getAllQuests() {
        waitBetweenRequests();
        this.rawResponse = api.getAllQuests();
        return this;
    }

    @Step("Get all quests with activeOnly={activeOnly}")
    public ParentSteps getAllQuests(boolean activeOnly) {
        waitBetweenRequests();
        this.rawResponse = api.getAllQuests(activeOnly);
        return this;
    }

    @Step("Get quest by id={questId}")
    public ParentSteps getQuestById(String questId) {
        waitBetweenRequests();
        this.rawResponse = api.getQuestById(questId);
        return this;
    }

    @Step("Create quest: title={title}, reward={reward}")
    public ParentSteps createQuest(String title, String description, double reward, int type, String childId, String deadline) {
        waitBetweenRequests();
        this.rawResponse = api.createQuest(new CreateRequestDto(title, description, reward, type, childId, deadline));
        if (rawResponse.statusCode() == 200) {
            this.lastQuestId = rawResponse.jsonPath().getString("id");
        }
        return this;
    }

    @Step("Create default daily quest for child")
    public ParentSteps createDefaultQuest() {
        return createQuest(
                "Clean Your Room",
                "Tidy up your room and make the bed",
                Constants.TestData.VALID_REWARD,
                Constants.QuestTypes.DAILY,
                Constants.TestData.CHILD_USER_ID,
                futureDeadline(7)
        );
    }

    @Step("Create quest with type={type} and reward={reward}")
    public ParentSteps createQuestWithType(int type, double reward) {
        return createQuest(
                "Test Quest Type " + type,
                "Quest description for type " + type,
                reward, type,
                Constants.TestData.CHILD_USER_ID,
                futureDeadline(14)
        );
    }

    @Step("Create quest with null title (negative test)")
    public ParentSteps createQuestNoTitle() {
        return createQuest(null, "Quest without a title", 5.0,
                Constants.QuestTypes.DAILY, Constants.TestData.CHILD_USER_ID, futureDeadline(7));
    }

    @Step("Create quest with negative reward (negative test)")
    public ParentSteps createQuestNegativeReward() {
        return createQuest("Negative Reward", "desc", Constants.TestData.NEGATIVE_REWARD,
                Constants.QuestTypes.DAILY, Constants.TestData.CHILD_USER_ID, futureDeadline(7));
    }

    @Step("Create quest with past deadline (negative test)")
    public ParentSteps createQuestPastDeadline() {
        return createQuest("Past Deadline", "desc", 5.0,
                Constants.QuestTypes.DAILY, Constants.TestData.CHILD_USER_ID, pastDeadline(7));
    }

    @Step("Create quest with zero reward")
    public ParentSteps createQuestZeroReward() {
        return createQuest("Zero Reward", "desc", Constants.TestData.ZERO_REWARD,
                Constants.QuestTypes.DAILY, Constants.TestData.CHILD_USER_ID, futureDeadline(7));
    }

    @Step("Create quest with invalid type=999 (negative test)")
    public ParentSteps createQuestInvalidType() {
        return createQuest("Invalid Type", "desc", 5.0,
                Constants.TestData.INVALID_QUEST_TYPE, Constants.TestData.CHILD_USER_ID, futureDeadline(7));
    }

    @Step("Create quest with non-existent childId (negative test)")
    public ParentSteps createQuestInvalidChild() {
        return createQuest("Invalid Child", "desc", 5.0,
                Constants.QuestTypes.DAILY, UUID.randomUUID().toString(), futureDeadline(7));
    }

    @Step("Create quest with empty body (negative test)")
    public ParentSteps createQuestEmptyBody() {
        waitBetweenRequests();
        this.rawResponse = api.createQuestRawBody("{}");
        return this;
    }

    @Step("Update quest id={questId}")
    public ParentSteps updateQuest(String questId, String title, String desc, double reward) {
        waitBetweenRequests();
        this.rawResponse = api.updateQuest(questId, new UpdateParentDto(title, desc, reward, futureDeadline(30)));
        return this;
    }

    @Step("Delete quest id={questId}")
    public ParentSteps deleteQuest(String questId) {
        waitBetweenRequests();
        this.rawResponse = api.deleteQuest(questId);
        return this;
    }

    @Step("Resolve quest id={questId}, approve={approve}")
    public ParentSteps resolveQuest(String questId, boolean approve) {
        waitBetweenRequests();
        this.rawResponse = api.resolveQuest(questId, approve);
        return this;
    }

    // ─── User Actions ───

    @Step("Get current parent user")
    public ParentSteps getCurrentUser() {
        waitBetweenRequests();
        this.rawResponse = api.getCurrentUser();
        return this;
    }

    @Step("Get current user without auth")
    public ParentSteps getCurrentUserNoAuth() {
        waitBetweenRequests();
        this.rawResponse = api.getCurrentUserNoAuth();
        return this;
    }

    @Step("Get current user with invalid auth")
    public ParentSteps getCurrentUserInvalidAuth() {
        waitBetweenRequests();
        this.rawResponse = api.getCurrentUserInvalidAuth();
        return this;
    }

    @Step("Get family members")
    public ParentSteps getFamilyMembers() {
        waitBetweenRequests();
        this.rawResponse = api.getFamilyMembers();
        return this;
    }

    @Step("Get family members without auth")
    public ParentSteps getFamilyMembersNoAuth() {
        waitBetweenRequests();
        this.rawResponse = api.getFamilyMembersNoAuth();
        return this;
    }

    // ─── Wallet ───

    @Step("Get wallet dashboard as parent")
    public ParentSteps getWalletDashboard() {
        waitBetweenRequests();
        this.rawResponse = api.getWalletDashboard();
        return this;
    }

    @Step("Get wallet dashboard without auth")
    public ParentSteps getWalletDashboardNoAuth() {
        waitBetweenRequests();
        this.rawResponse = api.getWalletDashboardNoAuth();
        return this;
    }

    // ─── Extract Helpers ───

    @Step("Extract quest ID from last response")
    public String extractQuestId() {
        String id = rawResponse.jsonPath().getString("id");
        assertThat("Quest ID must not be null", id, notNullValue());
        this.lastQuestId = id;
        return id;
    }

    // ─── Validators ───

    @Step("Validate status code is {expectedCode}")
    public ParentSteps validateStatusCode(int expectedCode) {
        assertThat("Status code", rawResponse.statusCode(), equalTo(expectedCode));
        return this;
    }

    @Step("Validate status code is one of expected codes")
    public ParentSteps validateStatusCodeIsOneOf(Integer... codes) {
        assertThat("Status code", rawResponse.statusCode(), anyOf(
                java.util.Arrays.stream(codes).map(org.hamcrest.Matchers::equalTo).toArray(org.hamcrest.Matcher[]::new)
        ));
        return this;
    }

    @Step("Validate response body is not empty")
    public ParentSteps validateResponseBodyIsNotEmpty() {
        assertThat("Response body", rawResponse.getBody().asString(), not(emptyString()));
        return this;
    }

    @Step("Validate Content-Type is application/json")
    public ParentSteps validateContentTypeIsJson() {
        assertThat("Content-Type", rawResponse.contentType(), containsString("application/json"));
        return this;
    }

    @Step("Validate response time is under {maxMillis}ms")
    public ParentSteps validateResponseTimeIsUnder(long maxMillis) {
        assertThat(String.format("Response time %dms exceeds %dms", rawResponse.getTime(), maxMillis),
                rawResponse.getTime(), lessThan(maxMillis));
        return this;
    }

    @Step("Validate quest title is {expectedTitle}")
    public ParentSteps validateQuestTitle(String expectedTitle) {
        assertThat("Quest title", rawResponse.jsonPath().getString("title"), equalTo(expectedTitle));
        return this;
    }

    @Step("Validate quest reward is {expectedReward}")
    public ParentSteps validateQuestReward(double expectedReward) {
        assertThat("Quest reward", rawResponse.jsonPath().getDouble("rewardAmount"), equalTo(expectedReward));
        return this;
    }

    @Step("Validate quest ID is not null")
    public ParentSteps validateQuestIdIsNotNull() {
        assertThat("Quest ID", rawResponse.jsonPath().getString("id"), notNullValue());
        return this;
    }

    @Step("Validate quest childId is {expectedChildId}")
    public ParentSteps validateQuestChildId(String expectedChildId) {
        assertThat("childId", rawResponse.jsonPath().getString("childId"), equalTo(expectedChildId));
        return this;
    }

    @Step("Validate response is array")
    public ParentSteps validateResponseIsArray() {
        assertThat("Response body must be array", rawResponse.jsonPath().getList("$"), notNullValue());
        return this;
    }

    @Step("Validate all quests are active")
    public ParentSteps validateAllQuestsAreActive() {
        assertThat("All isActive must be true", rawResponse.jsonPath().getList("isActive"), everyItem(is(true)));
        return this;
    }

    @Step("Validate role is not null")
    public ParentSteps validateRoleIsNotNull() {
        assertThat("role", rawResponse.jsonPath().getString("role"), notNullValue());
        return this;
    }

    @Step("Validate parentData is not null")
    public ParentSteps validateParentDataIsNotNull() {
        assertThat("parentData", rawResponse.jsonPath().get("parentData"), notNullValue());
        return this;
    }

    @Step("Validate parentData.children is not null")
    public ParentSteps validateChildrenArrayIsNotNull() {
        assertThat("parentData.children", rawResponse.jsonPath().get("parentData.children"), notNullValue());
        return this;
    }

    @Step("Validate parent balance is non-negative")
    public ParentSteps validateParentBalanceIsNotNegative() {
        Double balance = rawResponse.jsonPath().getDouble("parentData.balance");
        assertThat("Parent balance must be >= 0", balance, greaterThanOrEqualTo(0.0));
        return this;
    }

    // ─── Date Helpers ───

    public static String futureDeadline(int daysFromNow) {
        return LocalDateTime.now().plusDays(daysFromNow).format(ISO);
    }

    public static String pastDeadline(int daysAgo) {
        return LocalDateTime.now().minusDays(daysAgo).format(ISO);
    }
}
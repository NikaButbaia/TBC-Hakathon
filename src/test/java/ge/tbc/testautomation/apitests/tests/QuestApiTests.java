package ge.tbc.testautomation.apitests.tests;

import ge.tbc.testautomation.api.base.BaseTest;
import ge.tbc.testautomation.api.data.constants.Constants;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

@Feature("Quest API")
public class QuestApiTests extends BaseTest {

    private String createdQuestId;

    @Story("List Quests")
    @Description("GET /api/Quests - returns all quests as array")
    @Test(description = "Get All Quests Returns 200")
    public void getAllQuestsReturns200() {
        parentSteps
                .getAllQuests()
                .validateStatusCode(200)
                .validateResponseIsArray();
    }

    @Story("List Quests")
    @Description("GET /api/Quests?activeOnly=true - returns only active quests")
    @Test(description = "Get Active Only Quests Returns Only Active")
    public void getActiveOnlyQuestsReturnsOnlyActive() {
        parentSteps
                .getAllQuests(true)
                .validateStatusCode(200)
                .validateAllQuestsAreActive();
    }

    @Story("List Quests")
    @Description("GET /api/Quests?activeOnly=false - returns all quests")
    @Test(description = "Get Quests Active Only False Returns All")
    public void getQuestsActiveOnlyFalseReturnsAll() {
        parentSteps
                .getAllQuests(false)
                .validateStatusCode(200);
    }

    @Story("Create Quest")
    @Description("POST /api/Quests - create daily quest with valid data")
    @Test(description = "Create Daily Quest Returns 200")
    public void createDailyQuestReturns200() {
        parentSteps
                .createDefaultQuest()
                .validateStatusCode(200)
                .validateQuestIdIsNotNull()
                .validateQuestTitle("Clean Your Room")
                .validateQuestChildId(Constants.TestData.CHILD_USER_ID);

        createdQuestId = parentSteps.getLastQuestId();
    }

    @Story("Create Quest")
    @Description("POST /api/Quests - create weekly quest type=2")
    @Test(description = "Create Weekly Quest Returns 200")
    public void createWeeklyQuestReturns200() {
        parentSteps
                .createQuestWithType(Constants.QuestTypes.WEEKLY, 25.0)
                .validateStatusCode(200)
                .validateQuestIdIsNotNull();
    }

    @Story("Create Quest")
    @Description("POST /api/Quests - create custom quest type=3")
    @Test(description = "Create Custom Quest Returns 200")
    public void createCustomQuestReturns200() {
        parentSteps
                .createQuestWithType(Constants.QuestTypes.CUSTOM, Constants.TestData.HIGH_REWARD)
                .validateStatusCode(200)
                .validateQuestIdIsNotNull();
    }

    @Story("Create Quest")
    @Description("POST /api/Quests - create quest with zero reward")
    @Test(description = "Create Quest With Zero Reward Returns 200")
    public void createQuestWithZeroRewardReturns200() {
        parentSteps
                .createQuestZeroReward()
                .validateStatusCode(200)
                .validateQuestReward(0.0);
    }

    @Story("Create Quest - Negative")
    @Description("POST /api/Quests - null title should fail")
    @Test(description = "Create Quest No Title Returns 400")
    public void createQuestNoTitleReturns400() {
        parentSteps
                .createQuestNoTitle()
                .validateStatusCodeIsOneOf(400, 422);
    }

    @Story("Create Quest - Negative")
    @Description("POST /api/Quests - negative reward should fail")
    @Test(description = "Create Quest Negative Reward Returns 400")
    public void createQuestNegativeRewardReturns400() {
        parentSteps
                .createQuestNegativeReward()
                .validateStatusCodeIsOneOf(400, 422);
    }

    @Story("Create Quest - Negative")
    @Description("POST /api/Quests - past deadline should fail")
    @Test(description = "Create Quest Past Deadline Returns 400")
    public void createQuestPastDeadlineReturns400() {
        parentSteps
                .createQuestPastDeadline()
                .validateStatusCodeIsOneOf(400, 422);
    }

    @Story("Create Quest - Negative")
    @Description("POST /api/Quests - invalid type=999 should fail")
    @Test(description = "Create Quest Invalid Type Returns 400")
    public void createQuestInvalidTypeReturns400() {
        parentSteps
                .createQuestInvalidType()
                .validateStatusCodeIsOneOf(400, 422);
    }

    @Story("Create Quest - Negative")
    @Description("POST /api/Quests - non-existent childId should fail")
    @Test(description = "Create Quest Invalid ChildId Returns 400 Or 404")
    public void createQuestInvalidChildIdReturns400Or404() {
        parentSteps
                .createQuestInvalidChild()
                .validateStatusCodeIsOneOf(400, 404);
    }

    @Story("Create Quest - Negative")
    @Description("POST /api/Quests - empty body should fail")
    @Test(description = "Create Quest Empty Body Returns 400")
    public void createQuestEmptyBodyReturns400() {
        parentSteps
                .createQuestEmptyBody()
                .validateStatusCodeIsOneOf(400, 422);
    }

    @Story("Get Quest By ID")
    @Description("GET /api/Quests/{id} - valid ID returns 200")
    @Test(description = "Get Quest By Id Returns 200",
            dependsOnMethods = "createDailyQuestReturns200")
    public void getQuestByIdReturns200() {
        parentSteps
                .getQuestById(createdQuestId)
                .validateStatusCode(200)
                .validateQuestIdIsNotNull();
    }

    @Story("Get Quest By ID - Negative")
    @Description("GET /api/Quests/{id} - non-existent UUID returns 404")
    @Test(description = "Get Quest Non Existent Id Returns 404")
    public void getQuestNonExistentIdReturns404() {
        parentSteps
                .getQuestById(Constants.TestData.NON_EXISTENT_UUID)
                .validateStatusCode(404);
    }

    @Story("Get Quest By ID - Negative")
    @Description("GET /api/Quests/{id} - malformed UUID returns 400")
    @Test(description = "Get Quest Malformed Id Returns 400")
    public void getQuestMalformedIdReturns400() {
        parentSteps
                .getQuestById(Constants.TestData.MALFORMED_UUID)
                .validateStatusCode(400);
    }

    @Story("Update Quest")
    @Description("PUT /api/Quests/{id} - update returns 200")
    @Test(description = "Update Quest Returns 200",
            dependsOnMethods = "createDailyQuestReturns200")
    public void updateQuestReturns200() {
        parentSteps
                .updateQuest(createdQuestId, "Updated Title", "Updated desc", 20.0)
                .validateStatusCode(200);
    }

    @Story("Update Quest")
    @Description("GET /api/Quests/{id} - verify update reflected")
    @Test(description = "Verify Quest Update Reflected",
            dependsOnMethods = "updateQuestReturns200")
    public void verifyQuestUpdateReflected() {
        parentSteps
                .getQuestById(createdQuestId)
                .validateStatusCode(200)
                .validateQuestTitle("Updated Title")
                .validateQuestReward(20.0);
    }

    @Story("Update Quest - Negative")
    @Description("PUT /api/Quests/{id} - non-existent ID returns 404")
    @Test(description = "Update Non Existent Quest Returns 404")
    public void updateNonExistentQuestReturns404() {
        parentSteps
                .updateQuest(Constants.TestData.NON_EXISTENT_UUID, "Title", "desc", 5.0)
                .validateStatusCode(404);
    }

    @Story("Complete Quest")
    @Description("PATCH /api/Quests/{id}/complete - child completes quest")
    @Test(description = "Child Completes Quest Returns 200",
            dependsOnMethods = "createDailyQuestReturns200")
    public void childCompletesQuestReturns200() {
        childSteps
                .completeQuest(createdQuestId)
                .validateStatusCode(200);
    }

    @Story("Complete Quest - Negative")
    @Description("PATCH /api/Quests/{id}/complete - non-existent quest returns 404")
    @Test(description = "Complete Non Existent Quest Returns 404")
    public void completeNonExistentQuestReturns404() {
        childSteps
                .completeQuest(Constants.TestData.NON_EXISTENT_UUID)
                .validateStatusCode(404);
    }

    @Story("Resolve Quest")
    @Description("PATCH /api/Quests/{id}/resolve?approve=true - parent approves")
    @Test(description = "Parent Approves Quest Returns 200",
            dependsOnMethods = "childCompletesQuestReturns200")
    public void parentApprovesQuestReturns200() {
        parentSteps
                .resolveQuest(createdQuestId, true)
                .validateStatusCode(200);
    }

    @Story("Resolve Quest")
    @Description("PATCH /api/Quests/{id}/resolve?approve=false - parent rejects")
    @Test(description = "Parent Rejects Quest Returns 200")
    public void parentRejectsQuestReturns200() {
        parentSteps.createDefaultQuest().validateStatusCode(200);
        String questId = parentSteps.getLastQuestId();

        childSteps.completeQuest(questId).validateStatusCode(200);

        parentSteps
                .resolveQuest(questId, false)
                .validateStatusCode(200);
    }

    @Story("Resolve Quest - Negative")
    @Description("PATCH /api/Quests/{id}/resolve - non-existent quest returns 404")
    @Test(description = "Resolve Non Existent Quest Returns 404")
    public void resolveNonExistentQuestReturns404() {
        parentSteps
                .resolveQuest(Constants.TestData.NON_EXISTENT_UUID, true)
                .validateStatusCode(404);
    }

    @Story("Delete Quest")
    @Description("DELETE /api/Quests/{id} - returns 200")
    @Test(description = "Delete Quest Returns 200")
    public void deleteQuestReturns200() {
        parentSteps.createDefaultQuest().validateStatusCode(200);
        String questId = parentSteps.getLastQuestId();

        parentSteps
                .deleteQuest(questId)
                .validateStatusCode(200);
    }

    @Story("Delete Quest")
    @Description("GET /api/Quests/{id} - deleted quest returns 404")
    @Test(description = "Get Deleted Quest Returns 404")
    public void getDeletedQuestReturns404() {
        parentSteps.createDefaultQuest().validateStatusCode(200);
        String questId = parentSteps.getLastQuestId();
        parentSteps.deleteQuest(questId).validateStatusCode(200);

        parentSteps
                .getQuestById(questId)
                .validateStatusCode(404);
    }

    @Story("Delete Quest - Negative")
    @Description("DELETE /api/Quests/{id} - non-existent returns 404")
    @Test(description = "Delete Non Existent Quest Returns 404")
    public void deleteNonExistentQuestReturns404() {
        parentSteps
                .deleteQuest(Constants.TestData.NON_EXISTENT_UUID)
                .validateStatusCode(404);
    }

    @Story("E2E Quest Flow")
    @Description("Full flow: create -> complete -> approve -> verify")
    @Test(description = "E2E Quest Create Complete Approve Flow")
    public void e2eQuestCreateCompleteApproveFlow() {
        parentSteps.createDefaultQuest().validateStatusCode(200);
        String questId = parentSteps.getLastQuestId();

        childSteps.completeQuest(questId).validateStatusCode(200);

        parentSteps.resolveQuest(questId, true).validateStatusCode(200);

        parentSteps
                .getQuestById(questId)
                .validateStatusCode(200)
                .validateQuestIdIsNotNull();
    }
}
package ge.tbc.testautomation.apitests.tests;

import ge.tbc.testautomation.api.base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

@Feature("Quest + Quiz Integration Flow")
public class QuestQuizFlowTest extends BaseTest {

    private String flowQuestId;
    private String flowQuizId;

    @Story("Quest Creation")
    @Description("POST /api/Quests - parent creates quest for child")
    @Test(description = "Parent Creates Quest For Child")
    public void parentCreatesQuestForChild() {
        parentSteps
                .createDefaultQuest()
                .validateStatusCode(200)
                .validateQuestIdIsNotNull();

        flowQuestId = parentSteps.getLastQuestId();
    }

    @Story("Quest Completion")
    @Description("PATCH /api/Quests/{id}/complete - child completes quest")
    @Test(description = "Child Completes Quest",
            dependsOnMethods = "parentCreatesQuestForChild")
    public void childCompletesQuest() {
        childSteps
                .completeQuest(flowQuestId)
                .validateStatusCode(200);
    }

    @Story("Quest Approval")
    @Description("PATCH /api/Quests/{id}/resolve?approve=true - parent approves")
    @Test(description = "Parent Approves Completed Quest",
            dependsOnMethods = "childCompletesQuest")
    public void parentApprovesCompletedQuest() {
        parentSteps
                .resolveQuest(flowQuestId, true)
                .validateStatusCode(200);
    }

    @Story("Balance Check")
    @Description("GET /api/Wallet/dashboard - child balance not negative after approval")
    @Test(description = "Child Balance Not Negative After Quest Approval",
            dependsOnMethods = "parentApprovesCompletedQuest")
    public void childBalanceNotNegativeAfterQuestApproval() {
        bankSteps
                .getChildDashboard()
                .validateStatusCode(200)
                .validateChildBalanceIsNotNull()
                .validateChildBalanceIsNotNegative();
    }

    @Story("Quiz After Quest")
    @Description("POST /api/Quizzes - parent creates quiz after quest flow")
    @Test(description = "Parent Creates Quiz After Quest Flow",
            dependsOnMethods = "childBalanceNotNegativeAfterQuestApproval")
    public void parentCreatesQuizAfterQuestFlow() {
        quizSteps
                .createDefaultQuiz()
                .validateStatusCode(200)
                .validateQuizIdIsNotNull()
                .validateFirstQuestionIsNotNull();

        flowQuizId = quizSteps.getLastQuizId();
    }

    @Story("Quiz After Quest")
    @Description("POST /api/Quizzes/{id}/submit - child submits quiz answer")
    @Test(description = "Child Submits Quiz Answer After Quest Flow",
            dependsOnMethods = "parentCreatesQuizAfterQuestFlow")
    public void childSubmitsQuizAnswerAfterQuestFlow() {
        quizSteps
                .submitCurrentAnswer(flowQuizId)
                .validateStatusCode(200);
    }

    @Story("Quiz Approval After Quest")
    @Description("PATCH /api/Quizzes/{id}/resolve?approve=true - parent approves quiz")
    @Test(description = "Parent Approves Quiz After Full Flow",
            dependsOnMethods = "childSubmitsQuizAnswerAfterQuestFlow")
    public void parentApprovesQuizAfterFullFlow() {
        quizSteps
                .resolveQuiz(flowQuizId, true)
                .validateStatusCode(200);
    }

    @Story("Final Balance Check")
    @Description("GET /api/Wallet/dashboard - child balance after full quest+quiz flow")
    @Test(description = "Child Balance Valid After Full Quest Quiz Flow",
            dependsOnMethods = "parentApprovesQuizAfterFullFlow")
    public void childBalanceValidAfterFullQuestQuizFlow() {
        bankSteps
                .getChildDashboard()
                .validateStatusCode(200)
                .validateChildBalanceIsNotNull()
                .validateChildBalanceIsNotNegative();
    }
}
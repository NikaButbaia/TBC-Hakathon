package ge.tbc.testautomation.apitests.tests;

import ge.tbc.testautomation.api.base.BaseTest;
import ge.tbc.testautomation.api.data.constants.Constants;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

@Feature("Quiz API")
public class QuizApiTests extends BaseTest {

    private String createdQuizId;

    @Story("List Quizzes")
    @Description("GET /api/Quizzes - returns 200")
    @Test(description = "Get All Quizzes Returns 200")
    public void getAllQuizzesReturns200() {
        quizSteps
                .getAllQuizzes()
                .validateStatusCode(200);
    }

    @Story("Create Quiz")
    @Description("POST /api/Quizzes - create quiz with questions and options")
    @Test(description = "Create Quiz Returns 200")
    public void createQuizReturns200() {
        quizSteps
                .createDefaultQuiz()
                .validateStatusCode(200)
                .validateQuizIdIsNotNull();

        createdQuizId = quizSteps.getLastQuizId();
    }

    @Story("Create Quiz - Negative")
    @Description("POST /api/Quizzes - no questions should fail")
    @Test(description = "Create Quiz No Questions Returns 400")
    public void createQuizNoQuestionsReturns400() {
        quizSteps
                .createQuizNoQuestions()
                .validateStatusCodeIsOneOf(400, 422);
    }

    @Story("Create Quiz - Negative")
    @Description("POST /api/Quizzes - empty options should fail")
    @Test(description = "Create Quiz Empty Options Returns 400")
    public void createQuizEmptyOptionsReturns400() {
        quizSteps
                .createQuizEmptyOptions()
                .validateStatusCodeIsOneOf(400, 422);
    }

    @Story("Create Quiz - Negative")
    @Description("POST /api/Quizzes - no correct answer should fail")
    @Test(description = "Create Quiz No Correct Answer Returns 400")
    public void createQuizNoCorrectAnswerReturns400() {
        quizSteps
                .createQuizNoCorrectAnswer()
                .validateStatusCodeIsOneOf(400, 422);
    }

    @Story("Create Quiz - Negative")
    @Description("POST /api/Quizzes - invalid childId should fail")
    @Test(description = "Create Quiz Invalid ChildId Returns 400 Or 404")
    public void createQuizInvalidChildIdReturns400Or404() {
        quizSteps
                .createQuizInvalidChild()
                .validateStatusCodeIsOneOf(400, 404);
    }

    @Story("Create Quiz - Negative")
    @Description("POST /api/Quizzes - empty body should fail")
    @Test(description = "Create Quiz Empty Body Returns 400")
    public void createQuizEmptyBodyReturns400() {
        quizSteps
                .createQuizEmptyBody()
                .validateStatusCodeIsOneOf(400, 422);
    }

    @Story("Get Quiz By ID")
    @Description("GET /api/Quizzes/{id} - valid ID returns 200")
    @Test(description = "Get Quiz By Id Returns 200",
            dependsOnMethods = "createQuizReturns200")
    public void getQuizByIdReturns200() {
        quizSteps
                .getQuizById(createdQuizId)
                .validateStatusCode(200);
    }

    @Story("Get Quiz By ID - Negative")
    @Description("GET /api/Quizzes/{id} - non-existent returns 404")
    @Test(description = "Get Quiz Non Existent Id Returns 404")
    public void getQuizNonExistentIdReturns404() {
        quizSteps
                .getQuizById(Constants.TestData.NON_EXISTENT_UUID)
                .validateStatusCode(404);
    }

    @Story("Get Quiz By ID - Negative")
    @Description("GET /api/Quizzes/{id} - malformed UUID returns 400")
    @Test(description = "Get Quiz Malformed Id Returns 400")
    public void getQuizMalformedIdReturns400() {
        quizSteps
                .getQuizById(Constants.TestData.MALFORMED_UUID)
                .validateStatusCode(400);
    }

    @Story("Update Quiz")
    @Description("PUT /api/Quizzes/{id} - update returns 200")
    @Test(description = "Update Quiz Returns 200",
            dependsOnMethods = "createQuizReturns200")
    public void updateQuizReturns200() {
        quizSteps
                .updateQuiz(createdQuizId, "Updated Quiz", "Updated desc", 25.0)
                .validateStatusCode(200);
    }

    @Story("Update Quiz - Negative")
    @Description("PUT /api/Quizzes/{id} - non-existent returns 404")
    @Test(description = "Update Non Existent Quiz Returns 404")
    public void updateNonExistentQuizReturns404() {
        quizSteps
                .updateQuiz(Constants.TestData.NON_EXISTENT_UUID, "Title", "desc", 5.0)
                .validateStatusCode(404);
    }

    @Story("Submit Quiz")
    @Description("POST /api/Quizzes/{id}/submit - child submits answers")
    @Test(description = "Child Submits Quiz Answers Returns 200",
            dependsOnMethods = "createQuizReturns200")
    public void childSubmitsQuizAnswersReturns200() {
        quizSteps
                .submitCurrentAnswer(createdQuizId)
                .validateStatusCode(200);
    }

    @Story("Submit Quiz - Negative")
    @Description("POST /api/Quizzes/{id}/submit - non-existent quiz returns 404")
    @Test(description = "Submit Non Existent Quiz Returns 404")
    public void submitNonExistentQuizReturns404() {
        quizSteps
                .submitNonExistentQuiz()
                .validateStatusCode(404);
    }

    @Story("Submit Quiz - Negative")
    @Description("POST /api/Quizzes/{id}/submit - empty answers should fail")
    @Test(description = "Submit Quiz Empty Answers Returns 400")
    public void submitQuizEmptyAnswersReturns400() {
        quizSteps.createDefaultQuiz().validateStatusCode(200);
        String quizId = quizSteps.getLastQuizId();

        quizSteps
                .submitEmptyAnswers(quizId)
                .validateStatusCodeIsOneOf(400, 422);
    }

    @Story("Resolve Quiz")
    @Description("PATCH /api/Quizzes/{id}/resolve?approve=true - parent approves")
    @Test(description = "Parent Approves Quiz Returns 200",
            dependsOnMethods = "childSubmitsQuizAnswersReturns200")
    public void parentApprovesQuizReturns200() {
        quizSteps
                .resolveQuiz(createdQuizId, true)
                .validateStatusCode(200);
    }

    @Story("Resolve Quiz")
    @Description("PATCH /api/Quizzes/{id}/resolve?approve=false - parent rejects")
    @Test(description = "Parent Rejects Quiz Returns 200")
    public void parentRejectsQuizReturns200() {
        quizSteps.createDefaultQuiz().validateStatusCode(200);
        String quizId = quizSteps.getLastQuizId();
        quizSteps.submitCurrentAnswer(quizId).validateStatusCode(200);

        quizSteps
                .resolveQuiz(quizId, false)
                .validateStatusCode(200);
    }

    @Story("Resolve Quiz - Negative")
    @Description("PATCH /api/Quizzes/{id}/resolve - non-existent returns 404")
    @Test(description = "Resolve Non Existent Quiz Returns 404")
    public void resolveNonExistentQuizReturns404() {
        quizSteps
                .resolveQuiz(Constants.TestData.NON_EXISTENT_UUID, true)
                .validateStatusCode(404);
    }

    @Story("Delete Quiz")
    @Description("DELETE /api/Quizzes/{id} - returns 200")
    @Test(description = "Delete Quiz Returns 200")
    public void deleteQuizReturns200() {
        quizSteps.createDefaultQuiz().validateStatusCode(200);
        String quizId = quizSteps.getLastQuizId();

        quizSteps
                .deleteQuiz(quizId)
                .validateStatusCode(200);
    }

    @Story("Delete Quiz")
    @Description("GET /api/Quizzes/{id} - deleted quiz returns 404")
    @Test(description = "Get Deleted Quiz Returns 404")
    public void getDeletedQuizReturns404() {
        quizSteps.createDefaultQuiz().validateStatusCode(200);
        String quizId = quizSteps.getLastQuizId();
        quizSteps.deleteQuiz(quizId).validateStatusCode(200);

        quizSteps
                .getQuizById(quizId)
                .validateStatusCode(404);
    }

    @Story("Delete Quiz - Negative")
    @Description("DELETE /api/Quizzes/{id} - non-existent returns 404")
    @Test(description = "Delete Non Existent Quiz Returns 404")
    public void deleteNonExistentQuizReturns404() {
        quizSteps
                .deleteQuiz(Constants.TestData.NON_EXISTENT_UUID)
                .validateStatusCode(404);
    }

    @Story("E2E Quiz Flow")
    @Description("Full flow: create -> submit -> approve -> verify")
    @Test(description = "E2E Quiz Create Submit Approve Flow")
    public void e2eQuizCreateSubmitApproveFlow() {
        quizSteps.createDefaultQuiz().validateStatusCode(200);
        String quizId = quizSteps.getLastQuizId();

        quizSteps.submitCurrentAnswer(quizId).validateStatusCode(200);

        quizSteps.resolveQuiz(quizId, true).validateStatusCode(200);

        quizSteps
                .getQuizById(quizId)
                .validateStatusCode(200)
                .validateQuizIdIsNotNull();
    }
}
package ge.tbc.testautomation.api.steps;

import ge.tbc.testautomation.api.client.QuizAPI;
import ge.tbc.testautomation.api.data.constants.Constants;
import ge.tbc.testautomation.api.data.models.request.AnswerTaskRequestDto;
import ge.tbc.testautomation.api.data.models.request.CreateChildDto;
import ge.tbc.testautomation.api.data.models.request.UpdateChildDto;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static ge.tbc.testautomation.api.client.BaseAPIClient.waitBetweenRequests;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class QuizSteps {

    private final QuizAPI api = new QuizAPI();
    private Response rawResponse;
    private String lastQuizId;
    private String lastQuestionId;
    private String lastOptionId;

    public Response getRawResponse() { return rawResponse; }
    public String getLastQuizId()    { return lastQuizId; }

    // ─── Quiz Actions ───

    @Step("Get all quizzes")
    public QuizSteps getAllQuizzes() {
        waitBetweenRequests();
        this.rawResponse = api.getAllQuizzes();
        return this;
    }

    @Step("Get quiz by id={quizId}")
    public QuizSteps getQuizById(String quizId) {
        waitBetweenRequests();
        this.rawResponse = api.getQuizById(quizId);
        return this;
    }

    @Step("Get quiz by id={quizId} as child")
    public QuizSteps getQuizByIdAsChild(String quizId) {
        waitBetweenRequests();
        this.rawResponse = api.getQuizByIdAsChild(quizId);
        return this;
    }

    @Step("Create default quiz with 2 questions")
    public QuizSteps createDefaultQuiz() {
        waitBetweenRequests();

        List<CreateChildDto.QuizOptionRequest> q1Opts = Arrays.asList(
                new CreateChildDto.QuizOptionRequest("Paris", true),
                new CreateChildDto.QuizOptionRequest("London", false),
                new CreateChildDto.QuizOptionRequest("Berlin", false),
                new CreateChildDto.QuizOptionRequest("Madrid", false)
        );
        List<CreateChildDto.QuizOptionRequest> q2Opts = Arrays.asList(
                new CreateChildDto.QuizOptionRequest("Mars", false),
                new CreateChildDto.QuizOptionRequest("Jupiter", true),
                new CreateChildDto.QuizOptionRequest("Saturn", false),
                new CreateChildDto.QuizOptionRequest("Neptune", false)
        );
        List<CreateChildDto.QuizQuestionRequest> questions = Arrays.asList(
                new CreateChildDto.QuizQuestionRequest("What is the capital of France?", q1Opts),
                new CreateChildDto.QuizQuestionRequest("What is the largest planet?", q2Opts)
        );

        CreateChildDto dto = new CreateChildDto(
                Constants.TestData.CHILD_USER_ID,
                "Geography & Science Quiz",
                "Test your knowledge!",
                Constants.TestData.QUIZ_REWARD,
                questions
        );

        this.rawResponse = api.createQuiz(dto);
        if (rawResponse.statusCode() == 200) {
            this.lastQuizId = rawResponse.jsonPath().getString("id");
        }
        return this;
    }

    @Step("Create quiz with no questions (negative test)")
    public QuizSteps createQuizNoQuestions() {
        waitBetweenRequests();
        CreateChildDto dto = new CreateChildDto(
                Constants.TestData.CHILD_USER_ID, "Empty Quiz", "No questions", 5.0, null);
        this.rawResponse = api.createQuiz(dto);
        return this;
    }

    @Step("Create quiz with empty options (negative test)")
    public QuizSteps createQuizEmptyOptions() {
        waitBetweenRequests();
        List<CreateChildDto.QuizQuestionRequest> questions = Collections.singletonList(
                new CreateChildDto.QuizQuestionRequest("Question?", null)
        );
        CreateChildDto dto = new CreateChildDto(
                Constants.TestData.CHILD_USER_ID, "Empty Options Quiz", "desc", 5.0, questions);
        this.rawResponse = api.createQuiz(dto);
        return this;
    }

    @Step("Create quiz with no correct answer (negative test)")
    public QuizSteps createQuizNoCorrectAnswer() {
        waitBetweenRequests();
        List<CreateChildDto.QuizOptionRequest> opts = Arrays.asList(
                new CreateChildDto.QuizOptionRequest("A", false),
                new CreateChildDto.QuizOptionRequest("B", false),
                new CreateChildDto.QuizOptionRequest("C", false)
        );
        List<CreateChildDto.QuizQuestionRequest> questions = Collections.singletonList(
                new CreateChildDto.QuizQuestionRequest("All wrong?", opts)
        );
        CreateChildDto dto = new CreateChildDto(
                Constants.TestData.CHILD_USER_ID, "No Correct Quiz", "desc", 5.0, questions);
        this.rawResponse = api.createQuiz(dto);
        return this;
    }

    @Step("Create quiz with invalid childId (negative test)")
    public QuizSteps createQuizInvalidChild() {
        waitBetweenRequests();
        CreateChildDto dto = new CreateChildDto(
                UUID.randomUUID().toString(), "Bad Child Quiz", "desc", 5.0, null);
        this.rawResponse = api.createQuiz(dto);
        return this;
    }

    @Step("Create quiz with empty body (negative test)")
    public QuizSteps createQuizEmptyBody() {
        waitBetweenRequests();
        this.rawResponse = api.createQuizRawBody("{}");
        return this;
    }

    @Step("Update quiz id={quizId}")
    public QuizSteps updateQuiz(String quizId, String title, String desc, double reward) {
        waitBetweenRequests();
        this.rawResponse = api.updateQuiz(quizId, new UpdateChildDto(title, desc, reward));
        return this;
    }

    @Step("Delete quiz id={quizId}")
    public QuizSteps deleteQuiz(String quizId) {
        waitBetweenRequests();
        this.rawResponse = api.deleteQuiz(quizId);
        return this;
    }

    @Step("Submit quiz answers for quizId={quizId}")
    public QuizSteps submitCurrentAnswer(String quizId) {
        waitBetweenRequests();
        Response quizDetails = api.getQuizByIdAsChild(quizId);
        this.lastQuestionId = quizDetails.jsonPath().getString("questions[0].id");
        this.lastOptionId   = quizDetails.jsonPath().getString("questions[0].options[0].id");

        assertThat("questionId must exist", lastQuestionId, notNullValue());
        assertThat("optionId must exist", lastOptionId, notNullValue());

        AnswerTaskRequestDto dto = new AnswerTaskRequestDto(
                Collections.singletonList(new AnswerTaskRequestDto.SubmittedAnswer(lastQuestionId, lastOptionId))
        );
        this.rawResponse = api.submitQuiz(quizId, dto);
        return this;
    }

    @Step("Submit quiz with empty answers for quizId={quizId} (negative test)")
    public QuizSteps submitEmptyAnswers(String quizId) {
        waitBetweenRequests();
        this.rawResponse = api.submitQuizRawBody(quizId, "{\"answers\": []}");
        return this;
    }

    @Step("Submit quiz for non-existent quizId (negative test)")
    public QuizSteps submitNonExistentQuiz() {
        waitBetweenRequests();
        String fakeId = UUID.randomUUID().toString();
        this.rawResponse = api.submitQuizRawBody(fakeId,
                "{\"answers\": [{\"questionId\":\"" + UUID.randomUUID() + "\",\"selectedOptionId\":\"" + UUID.randomUUID() + "\"}]}");
        return this;
    }

    @Step("Resolve quiz id={quizId}, approve={approve}")
    public QuizSteps resolveQuiz(String quizId, boolean approve) {
        waitBetweenRequests();
        this.rawResponse = api.resolveQuiz(quizId, approve);
        return this;
    }

    // ─── Extract Helpers ───

    @Step("Extract quiz ID from last response")
    public String extractQuizId() {
        String id = rawResponse.jsonPath().getString("id");
        assertThat("Quiz ID must not be null", id, notNullValue());
        this.lastQuizId = id;
        return id;
    }

    // ─── Validators ───

    @Step("Validate status code is {expectedCode}")
    public QuizSteps validateStatusCode(int expectedCode) {
        assertThat("Status code", rawResponse.statusCode(), equalTo(expectedCode));
        return this;
    }

    @Step("Validate status code is one of expected codes")
    public QuizSteps validateStatusCodeIsOneOf(Integer... codes) {
        assertThat("Status code", rawResponse.statusCode(), anyOf(
                java.util.Arrays.stream(codes).map(org.hamcrest.Matchers::equalTo).toArray(org.hamcrest.Matcher[]::new)
        ));
        return this;
    }

    @Step("Validate response body is not empty")
    public QuizSteps validateResponseBodyIsNotEmpty() {
        assertThat("Response body", rawResponse.getBody().asString(), not(emptyString()));
        return this;
    }

    @Step("Validate Content-Type is application/json")
    public QuizSteps validateContentTypeIsJson() {
        assertThat("Content-Type", rawResponse.contentType(), containsString("application/json"));
        return this;
    }

    @Step("Validate quiz ID is not null")
    public QuizSteps validateQuizIdIsNotNull() {
        assertThat("Quiz ID", rawResponse.jsonPath().getString("id"), notNullValue());
        return this;
    }

    @Step("Validate first question is not null")
    public QuizSteps validateFirstQuestionIsNotNull() {
        assertThat("First question", rawResponse.jsonPath().get("questions[0]"), notNullValue());
        return this;
    }

    @Step("Validate quiz title is {expectedTitle}")
    public QuizSteps validateQuizTitle(String expectedTitle) {
        assertThat("Quiz title", rawResponse.jsonPath().getString("title"), equalTo(expectedTitle));
        return this;
    }
}
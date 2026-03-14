package ge.tbc.testautomation.api.client;

import ge.tbc.testautomation.api.data.constants.Constants;
import ge.tbc.testautomation.api.data.models.request.AnswerTaskRequestDto;
import ge.tbc.testautomation.api.data.models.request.CreateChildDto;
import ge.tbc.testautomation.api.data.models.request.UpdateChildDto;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class QuizAPI extends BaseAPIClient {

    public Response getAllQuizzes() {
        return given()
                .baseUri(Constants.URI.BASE)
                .header("Authorization", Constants.TestData.PARENT_USER_ID)
                .get(Constants.Paths.QUIZZES);
    }

    public Response getQuizById(String quizId) {
        return given()
                .baseUri(Constants.URI.BASE)
                .header("Authorization", Constants.TestData.PARENT_USER_ID)
                .pathParam(Constants.Params.ID, quizId)
                .get(Constants.Paths.QUIZZES + Constants.Endpoints.QUIZ_BY_ID);
    }

    public Response getQuizByIdAsChild(String quizId) {
        return given()
                .baseUri(Constants.URI.BASE)
                .header("Authorization", Constants.TestData.CHILD_USER_ID)
                .pathParam(Constants.Params.ID, quizId)
                .get(Constants.Paths.QUIZZES + Constants.Endpoints.QUIZ_BY_ID);
    }

    public Response createQuiz(CreateChildDto dto) {
        return given()
                .baseUri(Constants.URI.BASE)
                .header("Authorization", Constants.TestData.PARENT_USER_ID)
                .contentType(ContentType.JSON)
                .body(dto)
                .post(Constants.Paths.QUIZZES);
    }

    public Response createQuizRawBody(String rawJson) {
        return given()
                .baseUri(Constants.URI.BASE)
                .header("Authorization", Constants.TestData.PARENT_USER_ID)
                .contentType(ContentType.JSON)
                .body(rawJson)
                .post(Constants.Paths.QUIZZES);
    }

    public Response updateQuiz(String quizId, UpdateChildDto dto) {
        return given()
                .baseUri(Constants.URI.BASE)
                .header("Authorization", Constants.TestData.PARENT_USER_ID)
                .contentType(ContentType.JSON)
                .pathParam(Constants.Params.ID, quizId)
                .body(dto)
                .put(Constants.Paths.QUIZZES + Constants.Endpoints.QUIZ_BY_ID);
    }

    public Response deleteQuiz(String quizId) {
        return given()
                .baseUri(Constants.URI.BASE)
                .header("Authorization", Constants.TestData.PARENT_USER_ID)
                .pathParam(Constants.Params.ID, quizId)
                .delete(Constants.Paths.QUIZZES + Constants.Endpoints.QUIZ_BY_ID);
    }

    public Response submitQuiz(String quizId, AnswerTaskRequestDto dto) {
        return given()
                .baseUri(Constants.URI.BASE)
                .header("Authorization", Constants.TestData.CHILD_USER_ID)
                .contentType(ContentType.JSON)
                .pathParam(Constants.Params.ID, quizId)
                .body(dto)
                .post(Constants.Paths.QUIZZES + Constants.Endpoints.QUIZ_SUBMIT);
    }

    public Response submitQuizRawBody(String quizId, String rawJson) {
        return given()
                .baseUri(Constants.URI.BASE)
                .header("Authorization", Constants.TestData.CHILD_USER_ID)
                .contentType(ContentType.JSON)
                .pathParam(Constants.Params.ID, quizId)
                .body(rawJson)
                .post(Constants.Paths.QUIZZES + Constants.Endpoints.QUIZ_SUBMIT);
    }

    public Response resolveQuiz(String quizId, boolean approve) {
        return given()
                .baseUri(Constants.URI.BASE)
                .header("Authorization", Constants.TestData.PARENT_USER_ID)
                .pathParam(Constants.Params.ID, quizId)
                .queryParam(Constants.Params.APPROVE, approve)
                .patch(Constants.Paths.QUIZZES + Constants.Endpoints.QUIZ_RESOLVE);
    }
}
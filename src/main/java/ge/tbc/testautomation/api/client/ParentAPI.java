package ge.tbc.testautomation.api.client;

import ge.tbc.testautomation.api.data.constants.Constants;
import ge.tbc.testautomation.api.data.models.request.CreateRequestDto;
import ge.tbc.testautomation.api.data.models.request.UpdateParentDto;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ParentAPI extends BaseAPIClient {

    public Response getAllQuests() {
        return given()
                .baseUri(Constants.URI.BASE)
                .header("Authorization", Constants.TestData.PARENT_USER_ID)
                .get(Constants.Paths.QUESTS);
    }

    public Response getAllQuests(boolean activeOnly) {
        return given()
                .baseUri(Constants.URI.BASE)
                .header("Authorization", Constants.TestData.PARENT_USER_ID)
                .queryParam(Constants.Params.ACTIVE_ONLY, activeOnly)
                .get(Constants.Paths.QUESTS);
    }

    public Response getQuestById(String questId) {
        return given()
                .baseUri(Constants.URI.BASE)
                .header("Authorization", Constants.TestData.PARENT_USER_ID)
                .pathParam(Constants.Params.ID, questId)
                .get(Constants.Paths.QUESTS + Constants.Endpoints.QUEST_BY_ID);
    }

    public Response createQuest(CreateRequestDto dto) {
        return given()
                .baseUri(Constants.URI.BASE)
                .header("Authorization", Constants.TestData.PARENT_USER_ID)
                .contentType(ContentType.JSON)
                .body(dto)
                .post(Constants.Paths.QUESTS);
    }

    public Response createQuestRawBody(String rawJson) {
        return given()
                .baseUri(Constants.URI.BASE)
                .header("Authorization", Constants.TestData.PARENT_USER_ID)
                .contentType(ContentType.JSON)
                .body(rawJson)
                .post(Constants.Paths.QUESTS);
    }

    public Response updateQuest(String questId, UpdateParentDto dto) {
        return given()
                .baseUri(Constants.URI.BASE)
                .header("Authorization", Constants.TestData.PARENT_USER_ID)
                .contentType(ContentType.JSON)
                .pathParam(Constants.Params.ID, questId)
                .body(dto)
                .put(Constants.Paths.QUESTS + Constants.Endpoints.QUEST_BY_ID);
    }

    public Response deleteQuest(String questId) {
        return given()
                .baseUri(Constants.URI.BASE)
                .header("Authorization", Constants.TestData.PARENT_USER_ID)
                .pathParam(Constants.Params.ID, questId)
                .delete(Constants.Paths.QUESTS + Constants.Endpoints.QUEST_BY_ID);
    }

    public Response resolveQuest(String questId, boolean approve) {
        return given()
                .baseUri(Constants.URI.BASE)
                .header("Authorization", Constants.TestData.PARENT_USER_ID)
                .pathParam(Constants.Params.ID, questId)
                .queryParam(Constants.Params.APPROVE, approve)
                .patch(Constants.Paths.QUESTS + Constants.Endpoints.QUEST_RESOLVE);
    }

    public Response getCurrentUser() {
        return given()
                .baseUri(Constants.URI.BASE)
                .header("Authorization", Constants.TestData.PARENT_USER_ID)
                .get(Constants.Paths.USERS + Constants.Endpoints.ME);
    }

    public Response getCurrentUserNoAuth() {
        return given()
                .baseUri(Constants.URI.BASE)
                .get(Constants.Paths.USERS + Constants.Endpoints.ME);
    }

    public Response getCurrentUserInvalidAuth() {
        return given()
                .baseUri(Constants.URI.BASE)
                .header("Authorization", "invalid-user-id-12345")
                .get(Constants.Paths.USERS + Constants.Endpoints.ME);
    }

    public Response getFamilyMembers() {
        return given()
                .baseUri(Constants.URI.BASE)
                .header("Authorization", Constants.TestData.PARENT_USER_ID)
                .get(Constants.Paths.USERS + Constants.Endpoints.FAMILY_MEMBERS);
    }

    public Response getFamilyMembersNoAuth() {
        return given()
                .baseUri(Constants.URI.BASE)
                .get(Constants.Paths.USERS + Constants.Endpoints.FAMILY_MEMBERS);
    }

    public Response getWalletDashboard() {
        return given()
                .baseUri(Constants.URI.BASE)
                .header("Authorization", Constants.TestData.PARENT_USER_ID)
                .get(Constants.Paths.WALLET + Constants.Endpoints.DASHBOARD);
    }

    public Response getWalletDashboardNoAuth() {
        return given()
                .baseUri(Constants.URI.BASE)
                .get(Constants.Paths.WALLET + Constants.Endpoints.DASHBOARD);
    }
}
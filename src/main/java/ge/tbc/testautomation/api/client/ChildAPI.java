package ge.tbc.testautomation.api.client;

import ge.tbc.testautomation.api.data.constants.Constants;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ChildAPI extends BaseAPIClient {

    public Response completeQuest(String questId) {
        return given()
                .baseUri(Constants.URI.BASE)
                .header("Authorization", Constants.TestData.CHILD_USER_ID)
                .pathParam(Constants.Params.ID, questId)
                .patch(Constants.Paths.QUESTS + Constants.Endpoints.QUEST_COMPLETE);
    }

    public Response getAllQuests() {
        return given()
                .baseUri(Constants.URI.BASE)
                .header("Authorization", Constants.TestData.CHILD_USER_ID)
                .get(Constants.Paths.QUESTS);
    }

    public Response getQuestById(String questId) {
        return given()
                .baseUri(Constants.URI.BASE)
                .header("Authorization", Constants.TestData.CHILD_USER_ID)
                .pathParam(Constants.Params.ID, questId)
                .get(Constants.Paths.QUESTS + Constants.Endpoints.QUEST_BY_ID);
    }

    public Response getCurrentUser() {
        return given()
                .baseUri(Constants.URI.BASE)
                .header("Authorization", Constants.TestData.CHILD_USER_ID)
                .get(Constants.Paths.USERS + Constants.Endpoints.ME);
    }

    public Response getFamilyMembers() {
        return given()
                .baseUri(Constants.URI.BASE)
                .header("Authorization", Constants.TestData.CHILD_USER_ID)
                .get(Constants.Paths.USERS + Constants.Endpoints.FAMILY_MEMBERS);
    }

    public Response getWalletDashboard() {
        return given()
                .baseUri(Constants.URI.BASE)
                .header("Authorization", Constants.TestData.CHILD_USER_ID)
                .get(Constants.Paths.WALLET + Constants.Endpoints.DASHBOARD);
    }
}
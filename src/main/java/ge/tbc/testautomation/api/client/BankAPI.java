package ge.tbc.testautomation.api.client;

import ge.tbc.testautomation.api.data.constants.Constants;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BankAPI extends BaseAPIClient {

    public Response getParentDashboard() {
        return given()
                .baseUri(Constants.URI.BASE)
                .header("Authorization", Constants.TestData.PARENT_USER_ID)
                .get(Constants.Paths.WALLET + Constants.Endpoints.DASHBOARD);
    }

    public Response getChildDashboard() {
        return given()
                .baseUri(Constants.URI.BASE)
                .header("Authorization", Constants.TestData.CHILD_USER_ID)
                .get(Constants.Paths.WALLET + Constants.Endpoints.DASHBOARD);
    }

    public Response getDashboardNoAuth() {
        return given()
                .baseUri(Constants.URI.BASE)
                .get(Constants.Paths.WALLET + Constants.Endpoints.DASHBOARD);
    }
}
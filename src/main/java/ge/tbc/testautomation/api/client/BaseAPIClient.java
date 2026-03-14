package ge.tbc.testautomation.api.client;

import ge.tbc.testautomation.api.data.constants.Constants;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;

public class BaseAPIClient {

    private static final long REQUEST_DELAY_MS = 300;

    static {
        RestAssured.baseURI = Constants.URI.BASE;
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    public static void waitBetweenRequests() {
        try {
            Thread.sleep(REQUEST_DELAY_MS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
package by.teachmeskills.api.client;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.not;

public class BaseApiClient {
    private static final String BASE_URL = "https://petstore.swagger.io";

    public BaseApiClient() {
        //        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.baseURI = BASE_URL;
    }

    protected RequestSpecification getRequestSpecification() {
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);
    }

    public Response post(String path, Object body) {
        return getRequestSpecification()
                .body(body)
                .when()
                .post(path)
                .then()
                .extract()
                .response();
    }

    public <T> T post(String path, Object body, Class<T> responseClass) {
        return getRequestSpecification()
                .body(body)
                .when()
                .post(path)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .as(responseClass);
    }

    public Response put(String path, Object body) {
        return getRequestSpecification()
                .body(body)
                .when()
                .put(path)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();
    }

    public Response get(String path, Map<String, ?> parameterNameValuePairs) {
        return getRequestSpecification()
                .pathParams(parameterNameValuePairs)
                .when()
                .get(path)
                .then()
                .extract()
                .response();
    }

    public Response delete(String path, Map<String, ?> parameterNameValuePairs) {
        return getRequestSpecification()
                .pathParams(parameterNameValuePairs)
                .when()
                .delete(path)
                .then()
                .extract()
                .response();
    }
}

package stepDefinitionsAPI;

import APIUtilities.APIPayload;
import APIUtilities.GenericUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AddPlaceAPI extends GenericUtils {

    Response response;
    RequestSpecification request;
    APIPayload apiPayload = new APIPayload();

    @Given("user prepares the payload with {string}, {string}")
    public void user_prepares_the_payload_with(String testDataSheet, String testDataKey) {

        try {
            request = given().spec(googleAPIRequestSpec())
                    .body(apiPayload.getAddPlacePayload(testDataSheet, testDataKey));
        } catch (Exception e) {
            System.out.println("Error in prepares the payload " + e.getMessage());
        }


    }

    @When("user makes a POST request to endpoint")
    public void user_makes_a_post_request_to_endpoint() {
        response = request.when().post(getProperties("addPlace"))
                .then().extract().response();

    }

    @Then("user receives the status code as {int}")
    public void user_receives_the_status_code_as(Integer statusCode) {
        response.then().assertThat().statusCode(statusCode);
    }

    @And("user validates the response body")
    public void user_validates_the_response_body() {
        JsonPath jsonResponse = response.jsonPath();
        Assert.assertEquals(jsonResponse.getString("status"), "OK");
        Assert.assertEquals(jsonResponse.getString("scope"), "APP");
        String placeId = jsonResponse.getString("place_id");
        Assert.assertNotNull(placeId, "Response should contain a place_id");
        Assert.assertFalse(placeId.isBlank(), "place_id should not be blank");
        Assert.assertEquals(response.getHeader("Server"), "Apache/2.4.52 (Ubuntu)");

    }
}

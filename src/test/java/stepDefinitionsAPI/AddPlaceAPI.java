package stepDefinitionsAPI;

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

public class AddPlaceAPI {

    Response response;
    RequestSpecification request;

    @Given("user prepares the payload with {string}, {string}, {string}, {string}, {string}")
    public void user_prepares_the_payload_with(String lat, String lng, String name, String address, String language) {

        Map<String, Object> body = new HashMap<>();
        Map<String, Object> location = new HashMap<>();
        location.put("lat", Double.parseDouble(lat));
        location.put("lng", Double.parseDouble(lng));
        body.put("location", location);
        body.put("accuracy", 50);
        body.put("name", name);
        body.put("phone_number", "(+91) 983 893 3937");
        body.put("address", address);
        List<String> types = new ArrayList<>();
        types.add("shoe park");
        types.add("shop");
        body.put("types", types);
        body.put("website", "https://google.com");
        body.put("language", language);

        RestAssured.baseURI = "https://rahulshettyacademy.com";

        request = given().log().all().header("Content-Type", "application/json")
                .queryParam("key", "qaclick123")
                .body(body);

    }

    @When("user makes a POST request to endpoint")
    public void user_makes_a_post_request_to_endpoint() {
        response = request.when().post("/maps/api/place/add/json")
                .then().log().all().extract().response();

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

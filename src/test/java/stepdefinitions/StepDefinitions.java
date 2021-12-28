package stepdefinitions;

import static io.restassured.RestAssured.given;

import java.io.PrintStream;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import junit.framework.Assert;
import pojo.GetPlaceid;
import resources.PlaceValidations;
import resources.TestData;
import resources.Utils;

public class StepDefinitions extends Utils {

	RequestSpecification req = null;
	RequestSpecification reqspec = null;
	ResponseSpecification res = null;
	String response = null;
	Response resspec = null;
	JsonPath js = null;
	PrintStream log = null;
	public static String place_id = null;
	String address = "31, side layout, cohen 09";
	GetPlaceid gc=null;

	
	@Given("Add place payload for {string} with {string} {string} {string}")
	public void add_place_payload_for_with(String httpRequest, String name, String accuracy, String language) {
		if (httpRequest.equalsIgnoreCase("post")) {
			reqspec = given().spec(requestSpecification()).contentType("application/json").body(TestData.addPlaceData(name,accuracy,language));
		} else if (httpRequest.equalsIgnoreCase("get")) {
			reqspec = given().spec(requestSpecification()).queryParam("place_id", place_id);
		}
	   
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String apicall, String httprequest) {

		PlaceValidations resource = PlaceValidations.valueOf(apicall);
		if (httprequest.equalsIgnoreCase("post")) {
			// gc= reqspec.when().post(resource.getResource()).as(GetPlaceid.class);
			resspec=reqspec.when().post(resource.getResource());
			response = resspec.asString();
			js = Utils.getJsonPath(response);
			place_id = js.getString("place_id");
		} else if (httprequest.equalsIgnoreCase("get")) {
			resspec = reqspec.when().get(resource.getResource());
			response = resspec.asString();
			js = Utils.getJsonPath(response);
		}
	}

	@Then("the API call is success with status code {string}")
	public void the_API_call_is_success_with_status_code(String statuscode) {
		Assert.assertEquals(resspec.getStatusCode(), 200);

	}

	@Then("{string} is {string}")
	public void is(String key, String value) {
		//System.out.println(gc.getPlace_id());
		if (key.equals("scope")) {
			System.out.println("the value is" + js.getString(key));
			Assert.assertEquals(value, js.getString(key));
		} else if (key.equals("status")) {
			System.out.println("the value is" + js.getString(key));
			Assert.assertEquals(value, js.getString(key));
		}

	}

	@Then("I validate the address is matching with post request")
	public void i_validate_the_address_is_matching_with_post_request() {
		String getAddress = fetchValueResponse(js, "address");
		System.out.println("the get address"+getAddress);
		Assert.assertEquals(address, getAddress);
	}
	
	
}
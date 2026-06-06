package StepDefinations;

import PageObjects.RestAssuredPageObjects;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RestAssuredStepDefinations {
	RestAssuredPageObjects restAssuredPageObjects;

	public RestAssuredStepDefinations(RestAssuredPageObjects restAssuredPageObjects) {
		this.restAssuredPageObjects = restAssuredPageObjects;
	}
	
	@Given("User calls {string} Request")
	public void callPayload(String payloadAction) {
		System.out.println(payloadAction + ": payload is called");
		restAssuredPageObjects.requestSpecBuildingAPI(payloadAction);
	}
	
	@When("User request call for {string}")
	public void callAPI(String resourcePathName) {
		restAssuredPageObjects.httpRequest(resourcePathName);
    }
	@When("User request call for {string} with {string} value {string}")
	public void callAPI(String resourcePathName,String pathParamKey,String pathParamValue) {
		restAssuredPageObjects.httpRequest(resourcePathName,pathParamKey,pathParamValue);
    }
    @Then("{string} must be added with response code for {string}")
	public void validateResponseCode(String keyVal,String statusCode) {
    	restAssuredPageObjects.statusCodeValidation(keyVal,statusCode);    	
	}
   
}

package PageObjects;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import StepDefinations.TestContext;
import Utilities.BuildJsonPayLoad.ResponseValidationFile;
import Utilities.BuildJsonPayLoad.RestAssuredEnums;
import Utilities.BuildJsonPayLoad.RestAssuredRequestSpecs;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredPageObjects {
    private RequestSpecification req;
    private String resp; 
    RestAssuredRequestSpecs reqSpec=new RestAssuredRequestSpecs();
    ResponseValidationFile responseValidation=new ResponseValidationFile();

    public RestAssuredPageObjects() {
		System.out.println("RestAssuredPageObjects constructor called");
		reqSpec = new RestAssuredRequestSpecs();
	    }

    public void requestSpecBuildingAPI(String  payloadAction) {
    	 System.out.println("call payloadAction: " + payloadAction);    	
    	switch(payloadAction)
    	{
		case "ADD_PLACE":				
			req=given().spec(reqSpec.returnRequestforPlaceAPI() ).log().all();
	        TestContext.setRequestString(req);
			break;
		case "ADD_BOOK":
			req=given().spec(reqSpec.returnRequestforBookAPI(payloadAction)).log().all();
			TestContext.setRequestString(req);
			break;	
		case "GET_BOOKS":
			req=given().spec(reqSpec.returnRequestforBookAPI(payloadAction)).log().all();
			TestContext.setRequestString(req);
			break;
		case "GET_BOOK":
			req=given().spec(reqSpec.returnRequestforBookAPI(payloadAction)).log().all();
			TestContext.setRequestString(req);
			break;
		case "UPDATE_BOOK":
			req=given().spec(reqSpec.returnRequestforBookAPI(payloadAction)).log().all();
			TestContext.setRequestString(req);
			break;
		case "DELETE_BOOK":
			req=given().spec(reqSpec.returnRequestforBookAPI(payloadAction)).log().all();
			TestContext.setRequestString(req);
			break;
    	}
 
    }
    public void httpRequest(String actionResource,String pathParamKey,String pathParamValue) {
        System.out.println("Action api call");
        if (TestContext.getRequestSpec() == null) {
            throw new IllegalStateException(
                "RequestSpecification is null. " +
                "Ensure addPlacePayload() step runs before postAPI()."
            );
        }
        RestAssuredEnums apiEndpoint = RestAssuredEnums.valueOf(actionResource);
        
        System.out.println(actionResource+" end point is requested");//  Assuming you want to use ADD_PLACE endpoint
		Response res = null;
		switch(actionResource) {		
		case "GET_BOOK":
			res = TestContext.getRequestSpec().when().get(apiEndpoint.getResourcePath(pathParamKey,pathParamValue)).then().log().all().extract().response();
			break;
		case "UPDATE_BOOK":
			res = TestContext.getRequestSpec().when().put(apiEndpoint.getResourcePath(pathParamKey,pathParamValue)).then().log().all().extract().response();
			break;
		case "DELETE_BOOK":
			res = TestContext.getRequestSpec().when().delete(apiEndpoint.getResourcePath(pathParamKey,pathParamValue)).then().log().all().extract().response();
			break;
		}
		TestContext.setResponseString(res);
    }

    public void httpRequest(String actionResource) {
        System.out.println("Action api call");
        if (TestContext.getRequestSpec() == null) {
            throw new IllegalStateException(
                "RequestSpecification is null. " +
                "Ensure addPlacePayload() step runs before postAPI()."
            );
        }
        RestAssuredEnums apiEndpoint = RestAssuredEnums.valueOf(actionResource);
        
        System.out.println(actionResource+" end point is requested");//  Assuming you want to use ADD_PLACE endpoint
		Response res = null;
		switch(actionResource) {
		case "ADD_PLACE":
			res = TestContext.getRequestSpec().when().post(apiEndpoint.getResourcePath()).then().log().all().extract().response();
			break;
		case "ADD_BOOK":
			res = TestContext.getRequestSpec().when().post(apiEndpoint.getResourcePath()).then().log().all().extract().response();
			break;
		case "GET_BOOKS":
			res = TestContext.getRequestSpec().when().get(apiEndpoint.getResourcePath()).then().log().all().extract().response();
			break;
		
		}
		TestContext.setResponseString(res);
    }

    public void statusCodeValidation(String keyVal, String actionResource) {
        System.out.println("statusCodeValidation() called");
        
        if (TestContext.getResponseString() == null) {
            throw new IllegalStateException(
                "Response string is null. " +
                "Ensure postAPI() step runs before statusCodeValidation()."
            );
        }
        
        System.out.println(TestContext.getResponseString().asString());
        RestAssuredEnums apiEndpoint =RestAssuredEnums.valueOf(actionResource);
        responseValidation.responseCodeAnyValidation(TestContext.getResponseString(), apiEndpoint.getStatusCode());
    }
 
}

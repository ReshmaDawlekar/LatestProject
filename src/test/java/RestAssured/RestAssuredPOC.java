package RestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import RestAssured.PayLoad;
import Utilities.BuildJsonPayLoad.BuildJsonPayload;
import Utilities.BuildJsonPayLoad.Location;
import Utilities.BuildJsonPayLoad.ResponseValidationFile;

public class RestAssuredPOC {

	@Test(dataProvider="data")
	public void getPlaceTest(String lat,String lng) {
		ResponseValidationFile resVal=new ResponseValidationFile();
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("http://localhost:8080/mydb/getbook")
				.addPathParam("id","123")
				.addHeader("Content-Type","application/json")
				.setBody(PayLoad.getPayLoad(lat,lng)).build();
		ResponseSpecification res= new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
				
		RestAssured.baseURI = "http://localhost:8080";
		String response=given().spec(req).log().all()
				.when() .post("/maps/api/place/add/json")
				.then().spec(res).extract().response().asString();	     
		System.out.println(response);
		
		JsonPath js=new JsonPath(response);
		String place_id=js.getString("place_id");
		System.out.println(place_id);

		Response res1=given().log().all()
		.queryParam("key", "qaclick123")
		.header("Content-Type", "application/json")
		.body("{\r\n" + 
				"\"place_id\":\""+place_id+"\",\r\n" + 
				"\"address\":\"101 Mountain View, USA\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}")
		.when()
		.put("/maps/api/place/update/json")
		.then().extract().response();
		
		resVal.responseCodeAnyValidation(res1, 200);
		resVal.bodyValidation(res1,"msg", "Address successfully updated","equalTo");
		
		//get place request 
		Response resr=given().spec(req)
		.queryParam("place_id", place_id)
		.header("Content-Type", "application/json")
		.when()
		.get("/maps/api/place/get/json")
		.then().spec(res).extract().response();
		 resVal.bodyValidation(resr,"address", "101 Mountain View, USA","equalTo");
	}
	
	@DataProvider(name="data")
	public Object[][] getData()
	{
		return new Object[][] {{"-3.2","3.4"},{"-3.6","3.8"}};
	}
}


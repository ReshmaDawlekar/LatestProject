package Utilities.BuildJsonPayLoad;


import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.testng.Assert;

import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;

public class ResponseValidationFile {
	
	public void responseCodeValidation(ResponseSpecification res, int expectedCode) {
		
		res.statusCode(expectedCode);
		
	}
public void responseCodeAnyValidation(Response res, int expectedCode) {
		int actualStatusCode=res.getStatusCode();
		System.out.println("Actual Status Code is "+actualStatusCode);
		Assert.assertEquals(actualStatusCode, expectedCode);
		
	}
 
 public void headersValidation(ResponseSpecification res,String key,String value)
 {
	 res.then().header(key, value).log().all();
	 
 }

 public void bodyValidation(Response res,String key,String value,String operation)
 {
	 switch(operation) {
	 case"equalTo":
	 System.out.println("body : "+ key +" value is : "+value);
	 res.then().body(key, equalTo(value));
	 break;	 
 }
 }
	public Map<String, String> extractHeaders(Response res,List<String> keys) {
		
		Map<String, String> map=new HashMap<String, String>();
		map = keys.stream().filter(key -> res.getHeader(key) != null)				
				.collect(Collectors.toMap(key->key, key->res.getHeader(key)));
		return map;		
	}
	
	
	
}

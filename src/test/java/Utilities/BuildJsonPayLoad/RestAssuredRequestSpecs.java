package Utilities.BuildJsonPayLoad;

import java.io.FileOutputStream;
import java.io.PrintStream;

import org.testng.annotations.DataProvider;

import StepDefinations.TestContext;
import Utilities.GetConfigData;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RestAssuredRequestSpecs {
	
	dynamiPayloadClass dynamicPayLoads=new dynamiPayloadClass();
	BuildJsonPayload returnPayLoadData=dynamicPayLoads.returnPayLoadData();
	Library LibPayLoad=dynamicPayLoads.returnLibraryPayLoadData();
	Library updateLibPayLoad=dynamicPayLoads.returnUpdateLibPayLoad();
	
	
	public RequestSpecification returnRequestforBookAPI(String actions)
	{
		RequestSpecification req=null;
		try {
		PrintStream file=new PrintStream(new FileOutputStream("logging.txt"));
				req=new RequestSpecBuilder().
				setBaseUri(GetConfigData.getBaseURI("book")).
				addHeader("Content-Type","application/json").				
				addFilter(RequestLoggingFilter.logRequestTo(file)).
				addFilter(ResponseLoggingFilter.logResponseTo(file)).
				setContentType(ContentType.JSON).
				build();
				
				switch(actions)
				{
				case "ADD_BOOK":
					req = req.body(LibPayLoad);
					break;
				case "UPDATE_BOOK":
					req = req.body(updateLibPayLoad);
					break;
				}
		}
		catch (Exception e) {
			System.out.println("Exception while creating request specification " + e.getMessage());
		}
		TestContext.setRequestString(req);
		return  req;	
	}

	public RequestSpecification returnRequestforPlaceAPI() {
		RequestSpecification req=null;
		try {
		PrintStream file=new PrintStream(new FileOutputStream("logging.txt"));
				req=new RequestSpecBuilder().
				setBaseUri(GetConfigData.getBaseURI()).
				addQueryParam("key","qaclick123").
				addHeader("Content-Type","application/json").
				setBody(returnPayLoadData).
				addFilter(RequestLoggingFilter.logRequestTo(file)).
				addFilter(ResponseLoggingFilter.logResponseTo(file)).
				setContentType(ContentType.JSON).
				build();
		}
		catch (Exception e) {
			System.out.println("Exception while creating request specification " + e.getMessage());
		}
		TestContext.setRequestString(req);
		return  req;	
	}

	
}

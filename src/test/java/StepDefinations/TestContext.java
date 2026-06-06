package StepDefinations;

import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.WebDriver;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestContext {
	
	private WebDriver driver;
	private static HashMap<List<String>, List<List<String>>> dataFromExcel=new HashMap<>();
	private static Response responseSpec;
	private static RequestSpecification requestSpec;
	public void setDataFromExcel(HashMap<List<String>, List<List<String>>> dataFromExcel) {
		// TODO Auto-generated method stub
		this.dataFromExcel=dataFromExcel;
	}
	public static HashMap<List<String>, List<List<String>>> getDataFromExcel() {
		// TODO Auto-generated method stub
		return dataFromExcel;
	}
	public static void setResponseString(Response resp) {
		responseSpec=resp;
	}
			
	public static Response getResponseString() {
		return responseSpec;
	}
	
	public static void setRequestString(RequestSpecification req) {
		requestSpec=req;
	}
	
	public static RequestSpecification getRequestSpec() {
		return requestSpec;
	}
}

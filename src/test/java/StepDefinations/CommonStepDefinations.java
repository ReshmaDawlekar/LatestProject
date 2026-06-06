package StepDefinations;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v138.page.Page;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import PageObjects.CommonPageObjects;
import Utilities.ExcelUtils;
import Utilities.ExtentManager;
import Utilities.ExtentTestManager;
import Utilities.GetConfigData;
import WebDriverManager.Drivermanager;
import WebDriverManager.WebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;


public class CommonStepDefinations {
	private static final String OUTPUTTYPE = null;
	WebDriver driver;
	ExtentReports extent=ExtentManager.getInstance();
	ExcelUtils utils=new ExcelUtils();
	
	@Before("@RestAssured")
	public  void startExcel(Scenario scenario)
	{
		
		String featureUri = scenario.getUri().toString();
        System.out.println("Feature URI: " + featureUri);
        String featureFileName = featureUri
                .substring(featureUri.lastIndexOf("/") + 1);
        System.out.println("Feature File Name: " + featureFileName);
        HashMap<List<String>,List<List<String>>>  dataFromExcel=utils.readExcelData(featureFileName.split("\\.")[0]);
        TestContext dataContext=new TestContext();
        dataContext.setDataFromExcel(dataFromExcel);
        System.out.println("Feature File Name: " + dataFromExcel);
	}
	
	@Before("@RestAssured")
	public void loadScenarioData(Scenario scenario)
	{
		HashMap<String,String> data=null;
		try {
			HashMap<List<String>, List<List<String>>> dataFromExcel = TestContext.getDataFromExcel();
			List<String> columnHeaders = dataFromExcel.keySet().stream().map(key -> key).findFirst().orElse(null);
			List<List<String>> rowData = dataFromExcel.values().stream().map(value -> value).findFirst().orElse(null);
			List<List<String>>currentData=rowData.stream().filter(rowList -> rowList.contains(scenario.getName())).collect(Collectors.toList());
			columnHeaders.forEach(header -> System.out.println("Column Header: " + header));
			currentData.forEach(data1 -> System.out.println("Current Data: " + data1));			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	@Before("@Web")
	public  void startDriver(Scenario scenario)
	{
		
		Drivermanager.intialiseBrowser();
		ExtentTest test=extent.createTest(scenario.getName());
		ExtentTestManager.setExtent(test);
		
	}
	
	@After("@Web")
	public void quitBrowser()
	{
		WebDriverFactory.getDriver().quit();
	}
	@After("@Web")
	public static void flushReport() {
		ExtentManager.getInstance().flush();
	}
	@BeforeStep("@Web")
	public void tearUp() {
		
		
	} 
	
	public void takeScreenshot() throws WebDriverException, IOException {
	TakesScreenshot sc=(TakesScreenshot)WebDriverFactory.getDriver();
	FileUtils.copyFile(sc.getScreenshotAs(OutputType.FILE),new File("target/screenshot.png"));
	
	}
	@AfterStep("@Web")
	public void takeScreenshotExtent(Scenario scenario) throws WebDriverException, IOException {
	TakesScreenshot sc=(TakesScreenshot)WebDriverFactory.getDriver();
	String base64Screenshot=sc.getScreenshotAs(OutputType.BASE64);
	  ExtentTestManager.getExtent().
      addScreenCaptureFromBase64String(base64Screenshot,scenario.getName());
	}
	@AfterStep("@Web")
	public void takeScreenFullPage(Scenario scenario) throws WebDriverException, IOException
	{
//		String base64fullScreen=WebDriverFactory.returnDevTools().send(Page.captureScreenshot(Optional.empty(), Optional.empty(), Optional.empty(), Optional.of(true), java.util.Optional.empty(), java.util.Optional.empty()));
//		ExtentTestManager.getExtent().addScreenCaptureFromBase64String(base64fullScreen, "Full Page Screenshot " + scenario.getName());
	}
	   @AfterStep("@Web")
	    public void takeScreenshot(Scenario sc) throws IOException {
//	        if (sc.isFailed()) {
	            WebDriver driver = WebDriverFactory.getDriver();
	            if (driver != null) {
	                try {
	                    byte[] source = ((TakesScreenshot) driver)
	                        .getScreenshotAs(OutputType.BYTES);
	                    sc.attach(source, "image/png", sc.getName());
//	                    String base64Screenshot = Base64.getEncoder().encodeToString(source);
//
//	                    // Step 3: Add to Extent Report
//	                    ExtentTestManager.getExtent()
//	                        .addScreenCaptureFromBase64String(base64Screenshot, sc.getName());
//						
	                } catch (Exception e) {
	                    System.out.println("Screenshot failed for thread "
	                        + Thread.currentThread().getId()
	                        + ": " + e.getMessage());
	                }
	            }
//	        }
	    }
@Given("User launches URL")
public void launchURL() {
    String url = GetConfigData.getURL();
    System.out.println("Launching : " + url);
    WebDriverFactory.getDriver().get(url);
}
@When("User enter search value {string}")
public void searchData(String data) {
    CommonPageObjects commonPageObjects = new CommonPageObjects();
    commonPageObjects.enterDataForSearch(WebDriverFactory.getDriver(), data);
}
}
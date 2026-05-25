package StepDefinations;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import PageObjects.CommonPageObjects;
import Utilities.ExtentManager;
import Utilities.ExtentTestManager;
import WebDriverManager.Drivermanager;
import WebDriverManager.GetConfigData;
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
	
	   @AfterStep("@Web")
	    public void takeScreenshot(Scenario sc) throws IOException {
//	        if (sc.isFailed()) {
	            WebDriver driver = WebDriverFactory.getDriver();
	            if (driver != null) {
	                try {
	                    byte[] source = ((TakesScreenshot) driver)
	                        .getScreenshotAs(OutputType.BYTES);
	                    sc.attach(source, "image/png", sc.getName());
	                    String base64Screenshot = Base64.getEncoder().encodeToString(source);

	                    // Step 3: Add to Extent Report
	                    ExtentTestManager.getExtent()
	                        .addScreenCaptureFromBase64String(base64Screenshot, sc.getName());
						
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
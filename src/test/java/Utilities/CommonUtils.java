package Utilities;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import StepDefinations.TestContext;
import WebDriverManager.WebDriverFactory;

public class CommonUtils extends TestContext {

	private WebDriver driver=WebDriverFactory.getDriver();

	
	public void launchUrl(String Url)
	{
		WebDriverFactory.getDriver() .get(Url);
	}
	public void navigate(String Url)
	{
		WebDriverFactory.getDriver() .navigate().to(Url);
	}
	public String  getTitleOfPage(String Url)
	{
		return WebDriverFactory.getDriver() .getTitle().isBlank()?WebDriverFactory.getDriver() .getTitle() :null;
	}
	public String getSourceOfPage(String Url)
	{
		return WebDriverFactory.getDriver() .getPageSource().isBlank() ? driver.getPageSource() :null;
	}

	//window handles
	public void switchToWindowByTitle(String title)
	{
		Set<String> windowHandles=driver.getWindowHandles();
		for (String window:windowHandles)
		{
			if(window.contains(title))
			{
				WebDriverFactory.getDriver() .switchTo().window(window);
				break;
			}
		}
	}
	public void openNewTab()
	{
		WebDriverFactory.getDriver() .switchTo().newWindow(WindowType.TAB);
	}

	public void openNewWindow()
	{
		WebDriverFactory.getDriver() .switchTo().newWindow(WindowType.WINDOW);
	}


	public void maximizeWindow() {
		WebDriverFactory.getDriver() .manage().window().maximize();
	}
	public Dimension getWindowSize() {
		return WebDriverFactory.getDriver() .manage().window().getSize();
	}

	//handling alerts
	public void actionsAlert(String actionsType,long duration) {
		
		Alert alert=explicitWaitForAlertToBePresent(duration);
		switch(actionsType)
		{
		case "accept":
		alert.accept();
		break;
		case "dismiss":
            alert.dismiss();
            break;
		case "enterText":
            alert.sendKeys("text to enter");
            break;
		}
	}
	
	public void pageloadwait(Long duration) {
		WebDriverFactory.getDriver() .manage().timeouts().pageLoadTimeout(Duration.ofSeconds(duration));
	}
	//Handling Frames
	public void switchFrameByIndex(int index)
	{
		WebDriverFactory.getDriver() .switchTo().frame(index);
	}

	public void switchFrameByName(String name)
	{
		WebDriverFactory.getDriver() .switchTo().frame(name);
	}

	public void switchFrameByLocator(String locator)
	{
		WebDriverFactory.getDriver() .switchTo().frame(WebDriverFactory.getDriver() .findElement(By.xpath(locator)));
	}
	
	public void switchToDefaultContent() {
		WebDriverFactory.getDriver() .switchTo().defaultContent();
	}
	
	public void switchToFrameImmediataeParent() {
		WebDriverFactory.getDriver() .switchTo().parentFrame();
	}

	//Cookies

	public Set<Cookie> getCookie() {
		Set<Cookie> cookie=	WebDriverFactory.getDriver() .manage().getCookies();
		return cookie.size()>0?cookie:null;
	}

	//waits
	public  WebElement explicitWaitForVisibilityOfElement(String locator,int duration)
	{
		WebDriverWait wait=new WebDriverWait(WebDriverFactory.getDriver() ,Duration.ofSeconds(duration));
		WebElement ele=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
		return ele.isDisplayed()?ele:null;
	}
	public  Alert explicitWaitForAlertToBePresent(long duration)
	{
		try {
		WebDriverWait wait=new WebDriverWait(WebDriverFactory.getDriver() ,Duration.ofSeconds(duration));
		Alert alert=wait.until(ExpectedConditions.alertIsPresent());
		return alert!=null?alert:null;
		}
		catch (Exception e) {
			System.out.println("Alert is not present");
			return null;
		}
	}
	public WebElement shadowElement(String rootlocator,String elementToFind)
	{
		WebElement root=WebDriverFactory.getDriver() .findElement(By.cssSelector(rootlocator));
		SearchContext shadow=root.getShadowRoot();
		return shadow.findElement(By.cssSelector(elementToFind));

	}

	public void jScriptExecutorActions(String actionType,String locator)
	{
		JavascriptExecutor js=(JavascriptExecutor)WebDriverFactory.getDriver() ;
		switch(actionType) {
		case "click":
			js.executeScript("arguments[0].click", driver.findElement(By.xpath(locator)));
			break;
		case "Top":
			js.executeScript("window.scrollBy(0,0);");
			break;
		case "viewElement":
			js.executeScript("argument[0].scrollIntoView(true);",driver.findElement(By.xpath(locator)));
			break;
		case "bottom":
			js.executeScript("window.scrollTo(0,document.body.scrollHeight);");
			break;
		}
	}
	
	public static String getUniqueDateTime()
	{
		String newDateTime="";
		Date d=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		newDateTime = sdf.format(d);
		
		return newDateTime;
	}
}

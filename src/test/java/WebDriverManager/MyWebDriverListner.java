package WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

public class MyWebDriverListner implements WebDriverListener {
	
	public void beforeNavigateTo(String url, WebDriver driver) {
		System.out.println("Before navigating to: " + url);
	}

	public void afterNavigateTo(String url, WebDriver driver) {
		System.out.println("After navigating to: " + url);
	}

	@Override
	public void beforeClick(WebElement element) {
		System.out.println("Before clicking on: " + element);
	}

	@Override
	public void afterClick(WebElement element) {
		System.out.println("After clicking on: " + element);
	}
	
	@Override
	public void beforeGet(WebDriver driver, String url) {
		
		System.out.println("Before get url to: " + url);
	}
	@Override
	public void afterGet(WebDriver driver, String url) {
		
		System.out.println("After get url to: " + url);
	}
	
	 public void beforeSendKeys(WebElement element, CharSequence... keysToSend) {
		 
		 System.out.println("Before send keys to: " + element.toString());
	 }

	  public void afterSendKeys(WebElement element, CharSequence... keysToSend) {
		  
		  System.out.println("After send keys to " +element.toString());
	  }

	// Implement other methods as needed, for example:
	// beforeFindBy, afterFindBy, beforeChangeValueOf, afterChangeValueOf, etc.

}

package WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;

public class WebDriverFactory {
	
	 private static final ThreadLocal<WebDriver> driverThread
     = new ThreadLocal<>();

	 public static WebDriver getDriver() {
     WebDriver driver = driverThread.get();
     if (driver == null) {
         throw new RuntimeException(
             "Driver not initialised for thread: "
             + Thread.currentThread().getId()
         );
     }
     return driver;
 }

	    public static void setWebDriver(WebDriver dr) {
	    	MyWebDriverListner listener=new MyWebDriverListner();	    	
	    	EventFiringDecorator decorator = new EventFiringDecorator<>(listener);
	    	WebDriver decoratedDriver = decorator.decorate(dr);
	        // Set the thread-safe driver
	        driverThread.set(decoratedDriver);
	    }

	    // ✅ This is what cleans up after each scenario
	    public static void removeWebDriver() {
	    	driverThread.remove();
	    }
	}

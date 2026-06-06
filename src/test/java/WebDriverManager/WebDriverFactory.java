package WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;

public class WebDriverFactory {
	
	 private static final ThreadLocal<WebDriver> driverThread
     = new ThreadLocal<>();
	 private static final ThreadLocal<WebDriver> rawDriverThread
	 = new ThreadLocal<>();
	 public static WebDriver getRawDriver() {
		    WebDriver driver = rawDriverThread.get();
		    if (driver == null) {
		        throw new RuntimeException(
		            "Raw driver not initialised for thread: "
		            + Thread.currentThread().getId()
		        );
		    }
		    return driver;
		}
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
	    	rawDriverThread.set(dr);  // ← store BEFORE decorating
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
	    
//		public static DevTools returnDevTools() {
//			DevTools devtools=null;
//			switch(GetConfigData.getBrowser().toLowerCase())
//			{
//			case "chrome":
//			devtools=((ChromeDriver)getRawDriver()).getDevTools();
//			break;
//			case "edge":
//			devtools=((EdgeDriver)getRawDriver()).getDevTools();
//			break;
//			
//			}
//			devtools.createSession();
//			return devtools;
//		}
	}

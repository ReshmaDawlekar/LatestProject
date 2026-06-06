//package Appium;
//
//import org.testng.Assert;
//import org.testng.annotations.*;
//
//import java.net.MalformedURLException;
//public class AppiumSetup {
//	AndroidDriver driver;
//
//    @BeforeClass
//    public void setUp() throws MalformedURLException {
//
//        UiAutomator2Options options = new UiAutomator2Options();
//        options.setPlatformName("Android");
//        options.setDeviceName("emulator-5554");
//        options.setAppPackage("com.google.android.calculator");
//        options.setAppActivity("com.android.calculator2.Calculator");
//        options.setNoReset(true);
//        options.setAutomationName("UiAutomator2");
//
//        driver = new AndroidDriver(
//            new URL("http://127.0.0.1:4723/wd/hub"), options
//        );
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//    }
//
//}

package Utilities;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentTestManager{
	
	static ThreadLocal< ExtentTest> extent=new ThreadLocal<>();
	
	public static ExtentTest getExtent() {
		return extent.get();
	}
	public static void setExtent(ExtentTest test) {
		extent.set(test);
		}
	
}
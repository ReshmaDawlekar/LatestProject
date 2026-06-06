package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
	
	private  static ExtentReports extent=new ExtentReports();
	
	public static ExtentReports getInstance()
    {
		String uniqueDateTime=CommonUtils.getUniqueDateTime();
		ExtentSparkReporter spark=new ExtentSparkReporter(System.getProperty("user.dir")+"//test-output//CustomReports//ExtentReport_"+uniqueDateTime+"//ExtentReport.html");
		spark.config().setReportName("Automation Test Report");
		spark.config().setDocumentTitle("Test Results");
		extent.attachReporter(spark);
        return extent;
    }
	
	
}

package Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;

public class GetConfigData {
	static Properties prop=readConfig();
	static String URL;
	static String session;
	static Scenario sc;
	public static String getSession() {
		setSession();
		return session;
	}
	public static void setSession() {
		session = prop.getProperty("session");
		System.out.print("Working on session :" + session);
	}
	public static String getURL() {
		setURL();
		return URL;
	}
	public static void setURL() {
		
		URL =prop.getProperty("url");
		System.out.print("Launching :" + URL);
	}
	public static String getGridURL() {
		String path=prop.getProperty("seleniumGridURL");
		return path;
	}
	
	public static String getIsSeleniumGrid() {
		String path=prop.getProperty("isSeleniumGrid");
		return path;
	}
	static String Browser;
	public static String getBrowser() {
		setBrowser();
		return Browser;
	}
	public static void setBrowser() {
		Browser =prop.getProperty("browser");
		
	}
	public static String getTestDataPath() {
		// TODO Auto-generated method stub
		String path=prop.getProperty("TestDataLocation");
		return path;
	}
	public static String getScenarioName() {
		// TODO Auto-generated method stub
		return sc.getName();
	}
	public static Properties readConfig()
	{
		FileInputStream fis = null;
		Properties prop = new Properties();
		try {
			fis = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//configurations.properties");
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	public static String getBaseURI() {
		String path=prop.getProperty("baseURI");
		return path;
	}
	public static String getBaseURI(String type) {
		String path=prop.getProperty(type+"baseURI");
		return path;
	}
}

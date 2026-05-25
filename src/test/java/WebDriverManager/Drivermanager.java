package WebDriverManager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Drivermanager {

	private static WebDriver driver;


	public static void intialiseBrowser()
	{	

		if (GetConfigData.getIsSeleniumGrid().toLowerCase().equals("false")) {
			if (GetConfigData.getSession().toLowerCase().equals("new")) {
				switch(GetConfigData.getBrowser().toLowerCase())
				{
				case "chrome":
					Map<String,Object> map=new HashMap<String, Object>();
					map.put("download.default_directory", System.getProperty("user.dir")+"//src//test//resources//downloads");					
					ChromeOptions option=new ChromeOptions();
					option.setPageLoadStrategy(PageLoadStrategy.NORMAL);
					option.addArguments("--start-maximized");
					option.addArguments("--headless");
					option.setExperimentalOption("pref",map);
					driver =new ChromeDriver(option);
					WebDriverFactory.setWebDriver(driver);
//					storeSession();
					break;
				case "firefox":
					FirefoxOptions fireoption=new FirefoxOptions();
					fireoption.setPageLoadStrategy(PageLoadStrategy.NORMAL);
					fireoption.addArguments("--start-maximized");
					fireoption.addArguments("--headless");
					driver =new FirefoxDriver(fireoption) ;	
					WebDriverFactory.setWebDriver(driver);
					break;
				}
				
			} 
			
			else
			{
			//applicable for chrome only
				ChromeOptions option = getSession();
				driver = new ChromeDriver(option);
			}
			
		}

		else if(GetConfigData.getIsSeleniumGrid().toLowerCase().equals("true"))
		{
			ChromeOptions option=new ChromeOptions();
			option.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			option.addArguments("--start-maximized");
			option.addArguments("--headless");
			try {
				driver =new RemoteWebDriver(new URL(GetConfigData.getGridURL()),option);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			WebDriverFactory.setWebDriver(driver);
		}

	}	
	
	
public static ChromeOptions getSession() {
	ChromeOptions option=new ChromeOptions();
	try{
		String filename=System.getProperty("user.dir")+"//src//test//resources//debugger_address.txt";
		FileInputStream fileIn = new FileInputStream(filename);
		ObjectInputStream in = new ObjectInputStream(fileIn);			
		String hostaddress=in.readObject().toString().split("=")[1].replace("}","");
		System.out.println(hostaddress);
		
		option.setExperimentalOption("debuggerAddress", hostaddress);
		return option;
	}
	catch(ClassNotFoundException | IOException e)
	{
		e.printStackTrace();
	}
	return option;
}
	public static void storeSession() {
		Map<String,Object> map = null;
		String filename=System.getProperty("user.dir")+"//src//test//resources//debugger_address.txt";
		Capabilities capabilities =((ChromeDriver) WebDriverFactory. getDriver()).getCapabilities();
		//		map=capabilities.asMap();	
		System.out.println("Existing browser capabilities : "+map);
		for (Map.Entry<String, Object> entry : capabilities.asMap().entrySet()) {
			System.out.println("  " + entry.getKey() + ": " + entry.getValue());
			if(entry.getKey().contains("edgeOptions"))
			{
				System.out.println(entry.getValue());
				try {

					FileOutputStream fileOut = new FileOutputStream(filename);
					ObjectOutputStream out = new ObjectOutputStream(fileOut);
					out.writeObject(entry.getValue());
					FileInputStream fileIn = new FileInputStream(filename);
					ObjectInputStream in = new ObjectInputStream(fileIn);
					System.out.println(in.readObject().toString());
				}
				catch(IOException | ClassNotFoundException e)
				{
					e.printStackTrace();
				}
				System.out.println("Stored newly created session as : "+entry.getValue());
				break;
			}
		}
	}

	public WebDriver getdriver()
	{
		try {
			return  WebDriverFactory.getDriver();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return  WebDriverFactory.getDriver();
	}


}

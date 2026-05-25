package RestAssured;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;

import Utilities.BuildJsonPayLoad.BuildJsonPayload;
import Utilities.BuildJsonPayLoad.Location;

public class PayLoad  {
	
	@DataProvider(name="data")
	public static String getPayLoad(String lat,String lng)
	{
		{
			return "{\r\n" + 
					"  \"location\": {\r\n" + 
					"    \"lat\": "+lat+",\r\n" + 
					"    \"lng\": "+lng+"\r\n" + 
					"  },\r\n" + 
					"  \"accuracy\": 50,\r\n" + 
					"  \"name\": \"Rahul Shetty Academy\",\r\n" + 
					"  \"phone_number\": \"(+91) 983 893 3937\",\r\n" + 
					"  \"address\": \"29, side layout, cohen 09\",\r\n" + 
					"  \"types\": [\r\n" + 
					"    \"shoe park\",\r\n" + 
					"    \"shop\"\r\n" + 
					"  ],\r\n" + 
					"  \"website\": \"http://rahulshettyacademy.com\",\r\n" + 
					"  \"language\": \"French-IN\"\r\n" + 
					"}\r\n" + 
					"";
			
			
		}
		

	}
	
	public static String CoursePrice()
	{
		
		return "{\r\n" + 
				"  \"dashboard\": {\r\n" + 
				"    \"purchaseAmount\": 1162,\r\n" + 
				"    \"website\": \"rahulshettyacademy.com\"\r\n" + 
				"  },\r\n" + 
				"  \"courses\": [\r\n" + 
				"    {\r\n" + 
				"      \"title\": \"Selenium Python\",\r\n" + 
				"      \"price\": 50,\r\n" + 
				"      \"copies\": 6\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"title\": \"Cypress\",\r\n" + 
				"      \"price\": 40,\r\n" + 
				"      \"copies\": 4\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"title\": \"RPA\",\r\n" + 
				"      \"price\": 45,\r\n" + 
				"      \"copies\": 10\r\n" + 
				"    },\r\n" + 
				"     {\r\n" + 
				"      \"title\": \"Appium\",\r\n" + 
				"      \"price\": 36,\r\n" + 
				"      \"copies\": 7\r\n" + 
				"    }\r\n" + 
				"    \r\n" + 
				"    \r\n" + 
				"    \r\n" + 
				"  ]\r\n" + 
				"}\r\n" + 
				"";
}
	
	public static String jiraPayloadBug()
	{
		return "{\r\n"
				+ "    \"fields\": {\r\n"
				+ "        \"project\": {\r\n"
				+ "            \"key\": \"SCRUM\"\r\n"
				+ "        },\r\n"
				+ "        \"summary\": \"Test1 issue from Postman\",\r\n"
				+ "        \"description\": {\r\n"
				+ "            \"type\": \"doc\",\r\n"
				+ "            \"version\": 1,\r\n"
				+ "            \"content\": [\r\n"
				+ "                {\r\n"
				+ "                    \"type\": \"paragraph\",\r\n"
				+ "                    \"content\": [\r\n"
				+ "                        {\r\n"
				+ "                            \"type\": \"text\",\r\n"
				+ "                            \"text\": \"Created via Postman\"\r\n"
				+ "                        }\r\n"
				+ "                    ]\r\n"
				+ "                }\r\n"
				+ "            ]\r\n"
				+ "        },\r\n"
				+ "        \"issuetype\": {\r\n"
				+ "            \"name\": \"Bug\"\r\n"
				+ "        }\r\n"
				+ "    }\r\n"
				+ "}";
	}
	

	public String baseURI(String appType) {
		String baseURI="";
		switch(appType) {
		case "Jira":		
			baseURI= "https://reshmadawlekar41.atlassian.net/rest/api/3/issue";
			break;
		case "app":
			baseURI= "";
			break;
		}
	    return baseURI;

	}

	public void buildPayload() {

		BuildJsonPayload p=new BuildJsonPayload();
		
		p.setAccuracy(50);
		p.setAddress("29, side layout, cohen 09");
		p.setLanguage("French-IN");
		p.setName("Rahul Shetty Academy");
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("http://rahulshettyacademy.com");
		List<String> typesval=new ArrayList<String>();
		typesval.add("shoe park");
		typesval.add("shop");
		p.setTypes(typesval);		
		Location l=new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
	}

}

package Utilities.BuildJsonPayLoad;

import java.util.ArrayList;
import java.util.List;

public class dynamiPayloadClass {
	
	public BuildJsonPayload returnPayLoadData()
	{
		BuildJsonPayload p = new BuildJsonPayload();
		p.setAccuracy(50);
		p.setAddress("29, side layout, cohen 09");
		p.setLanguage("French-IN");
		p.setName("Rahul Shetty Academy");
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("http://rahulshettyacademy.com");

		List<String> typesVal = new ArrayList<>();
		typesVal.add("shoe park");
		typesVal.add("shop");
		p.setTypes(typesVal);

		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		return p;
	}
	
	public Library returnLibraryPayLoadData()
	{
		Library p = new Library();
		p.setId(902);
		p.setName("Learn Appium Automation with C++");
		p.setIsbn("bcd");
		p.setAisle("227");
		p.setAuthor("John foe");
		return p;
	}
	public Library returnUpdateLibPayLoad()
	{
		Library p = new Library();
		p.setName("Learn Appium Automation with new");
		p.setIsbn("bcd");
		p.setAisle("227");
		p.setAuthor("Reshma");
		p.setId(786);
		return p;
	}

}

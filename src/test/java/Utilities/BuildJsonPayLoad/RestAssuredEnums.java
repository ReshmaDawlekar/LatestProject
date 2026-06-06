package Utilities.BuildJsonPayLoad;

public enum RestAssuredEnums {
		ADD_BOOK  ("/mydb/addbook",    200),	   
	    GET_BOOKS ("/mydb/getbooks", 	200),
	    GET_BOOK  ( "/mydb/getbook/{id}",     200),
	    UPDATE_BOOK ("/mydb/updatebook/{id}",  200),
	    DELETE_BOOK ("/mydb/deletBook/{id}",  200),
	    ADD_PLACE   ("/maps/api/place/add/json", 200);
	
	String resourcePath;
	int statusCode;
	
	RestAssuredEnums(String resourcePath,int statusCode)
	{
		this.resourcePath=resourcePath;
		this.statusCode=statusCode;
	}

	public String getResourcePath() {
		return resourcePath;
	}
	public String getResourcePath(String pathParam,String value) {
		return resourcePath.replace("{"+pathParam+"}", value);
	}
	public int getStatusCode() {
		return statusCode;
	}
}

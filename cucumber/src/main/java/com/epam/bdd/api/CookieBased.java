package com.epam.bdd.api;

import java.util.HashMap;
import java.util.Map;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;


public class CookieBased {

	@Test
	public void TestCookie()
	{
		Map<String, String> data =new HashMap<>();
		data.put("username", "pm1");
		data.put("password", "project123");
		
		Response post=given().header("Content-Type","application/json").body(data).post("https://jira-rmsis.optimizory.com/rest/auth/1/session");	
	    System.out.println(post.getStatusCode());
	    System.out.println(post.getBody().prettyPrint());
	    
	    String JsessionID=post.getCookie("JSESSIONID");
	    System.out.println(JsessionID);
	    
	    Response res=given().contentType("application/json")
	    		.cookie("JSESSIONID", JsessionID)
	    		.body("{\"fields\":{\"project\":{\"key\":\"TEST\"},\"summary\":\"REST ye merry gentlemen.\",\"description\":\"Creating of an issue using project keys and issue type names using the REST API\",\"issuetype\":{\"name\":\"Bug\"}}}")
	    		.post("https://jira-rmsis.optimizory.com/rest/api/2/issue/");
	
	System.out.println(res.getStatusCode());
	}
}

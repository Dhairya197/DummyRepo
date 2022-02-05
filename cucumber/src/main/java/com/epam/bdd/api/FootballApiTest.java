package com.epam.bdd.api;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;

import java.util.List;
import java.util.Map;

public class FootballApiTest {

	@BeforeClass
	public void setup()
	{
		RestAssured.baseURI="https://api.football-data.org";
	RestAssured.basePath="/v2";
	
	RestAssured.requestSpecification=new RequestSpecBuilder()
			 .setContentType("application/json").addHeader("X-Auth-Token", "9903e5f4ae6e4ce7a11e97490aa5fbd5") .build();
		
	}
	@Test
	public void getRequest()
	{
		get("/teams")
		.then().assertThat().statusCode(200);
		
	}
	
	
	@Test
	public void getRequest2()
	{
		get("/teams/66")
		.then().assertThat().statusCode(200).body("name", equalTo("Manchester United FC"));
		
	}
	
	@Test
	public void getRequest3()
	{
		Response res=get("/teams/66");
		Assert.assertEquals(res.path("name"), "Manchester United FC");
	}
	
	@Test
	public void getRequest4()
	{
		Response res=get("/teams/66");
		JsonPath js=res.jsonPath();
		Assert.assertEquals(js.get("name"), "Manchester United FC");
		
	}
	
	@Test
	public void getRequest5()
	{
		String responeAsString=get("/teams/66").asString();
		String name=JsonPath.from(responeAsString).get("name");
		Assert.assertEquals(name, "Manchester United FC");
		
	}
	
	@Test
	public void getRequest6()
	{
		Assert.assertEquals(get("/teams/66").path("name"), "Manchester United FC");
		
	}
	
	@Test
	public void getRequest7()
	{
		Response res=get("/teams");
		String name=res.path("teams[0].name");
		//Assert.assertEquals(name, "Manchester United FC");
		System.out.println(name);
		String Lastname=res.path("teams[-1].name");   //name of last array
		System.out.println(Lastname);
		List<Map<String, ?>> allTeams=res.path("teams");
		Map<String, ?> teamObj=res.path("teams.find{it.name=='Aston Villa FC'}");
	}
	

	@Test
	public void TestDataComplex()
	{
		Response res=get("/teams/66");
		String s=res.path("squad.find{it.shirtNumber==10}.name");
		System.out.println(s);
	}
}

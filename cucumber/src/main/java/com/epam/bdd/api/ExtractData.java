package com.epam.bdd.api;

import io.restassured.response.*;
import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.lessThan;

import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ExtractData {

	private RequestSpecification req=new RequestSpecBuilder()
			.setBaseUri("https://api.zippopotam.us")
			.setContentType("application/json").build();
	private ResponseSpecification res=new ResponseSpecBuilder()
			.expectStatusCode(200)
			.expectResponseTime(lessThan(2000L)).build();
	
	@Test
	public void verifyState1()
	{
		
		Response response= given().spec(req)
		.when().get("/US/00210")
		.then().extract().response();
	    
		int statusCode= response.statusCode();
		String country=response.path("country");
		String state=response.path("places[0].state");
		System.out.println("Status code is:"+ statusCode );
		System.out.println(country+" - " + state);
	}
	
	@Test
	public void verifyData()
	{
		
	}
}

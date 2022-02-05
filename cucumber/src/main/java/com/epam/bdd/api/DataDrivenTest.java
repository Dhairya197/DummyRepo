package com.epam.bdd.api;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


public class DataDrivenTest {


	private RequestSpecification req=new RequestSpecBuilder()
			.setBaseUri("https://api.zippopotam.us")
			.setContentType("application/json").build();
	private ResponseSpecification res=new ResponseSpecBuilder()
			.expectStatusCode(200)
			.expectResponseTime(lessThan(2000L)).build();
	
	@Test
	public void verifyState1()
	{
		given().spec(req)
		.when().get("/US/00210")
		.then().assertThat().spec(res)
		.body("places[0].state", equalTo("New Hampshire"));
	}
	
	@Test(dataProvider = "getZipCode")
	public void verifyState2(String countryCode,String zipCode,String state)
	{
		given().spec(req)
		.when().get("/"+countryCode+"/"+zipCode)
		.then().assertThat().spec(res)
		.body("places[0].state", equalTo(state));
	}
			

	@Test(dataProvider = "getZipCode")
	public void verifyState3(String countryCode,String zipCode,String state)
	{
		given().spec(req)
		.pathParam("countryCode", countryCode).pathParam("zipCode", zipCode)
		.when().get("/{countryCode}/{zipCode}")
		.then().assertThat().spec(res)
		.body("places[0].state", equalTo(state));
	}

	@Test()
	public void verifyStates()
	{
		given().spec(req)
		.when().get("/US/99950")
		.then().assertThat().spec(res)
		.body("places[0].state", equalTo("Alaska"));
	}
	
	@DataProvider(name="getZipCode")
    public Object[][] getZipCode()
    {
    	return new Object[][] {
    		{"US","00210","New Hampshire"},
    		{"US","99950","Alaska"}
    	};
    }
}


package com.epam.bdd.api;

import org.testng.annotations.Test;


import static org.hamcrest.Matchers.*;



import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

public class BasicTestForBody {

	private RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://api.zippopotam.us").setContentType("application/json").build();
	private ResponseSpecification res=new ResponseSpecBuilder().expectStatusCode(200).expectResponseTime(lessThan(2000L)).build();
	
	@Test
	public void verifyCountryDetails()
	{
		given().spec(req)/*.contentType("application/json")*/
		.when().get("/US/00210")
		.then().assertThat().spec(res)
		.body("country", equalTo("United States"));
	}
	
	
	@Test
	public void verifyNegativeCase()
	{
		given().spec(req)
		.when().get("/US/002100")
		.then().assertThat().statusCode(404);
	}
}

package com.epam.bdd.api;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecificatonsClass {



	/*
	 * private RequestSpecification req=new
	 * RequestSpecBuilder().setBaseUri("https://api.zippopotam.us").setContentType(
	 * "application/json").build(); private ResponseSpecification res=new
	 * ResponseSpecBuilder().expectStatusCode(200).expectResponseTime(lessThan(2000L
	 * )).build();
	 */
		@BeforeClass
		public void setup()
		{
			RestAssured.requestSpecification=new RequestSpecBuilder()
					.setBaseUri("https://api.zippopotam.us")
					.setContentType("application/json").build();
		
			RestAssured.responseSpecification=new ResponseSpecBuilder()
					.expectStatusCode(200)
					.expectResponseTime(lessThan(2000L)).build();
			
		}
		
		@Test
		public void verifyCountryDetails()
		{
			get("/US/00210")
			.then().assertThat()
			.body("country", equalTo("United States"));
		}
		
		
		/*
		 * @Test public void verifyNegativeCase() { get("/US/002100")
		 * .then().assertThat().statusCode(404); }
		 */
}

package com.epam.bdd.api;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

public class BasicAuth {

	@Test
	public void basicAuth()
	{
		given().auth().preemptive().basic("postman", "password")
		.when().get("https://postman-echo.com/basic-auth").
		then().statusCode(200);
	}
}

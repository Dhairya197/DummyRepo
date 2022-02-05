package com.epam.bdd.api;


import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.*;

public class App 
{
	///US/00210
	@Test
    public void verifyStatus()
    {
		given().baseUri("https://api.zippopotam.us")
        .when().get("/US/00210").then().statusCode(200)
        .body("country", equalTo("United States"))
        .body("'country abbreviation'", equalTo("US"))
        .body("places[0].state", equalTo("New Hampshire"));
    }
	
	
}

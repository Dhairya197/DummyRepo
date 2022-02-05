package com.epam.bdd.api;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.testng.annotations.Test;

public class PetSchemaTest {

	@Test
	public void testSchema()
    {
		

		   given().
		       baseUri("https://petstore.swagger.io").basePath("/v2").accept("application/json")
		       .when().get("/pet/9223372000001093518")
		       .then().statusCode(200).
		       body(matchesJsonSchemaInClasspath("PetSchema.json"));
		
	}
}

package com.epam.bdd.api;

import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.*;

public class XmlTest {

	@Test
	public void testXMLData()
	{
	   given().
	       baseUri("https://petstore.swagger.io").basePath("/v2").accept("application/xml")
	       .when().get("/pet/1234")
	       .then().statusCode(200).body("Pet.status",equalTo("pending"));
	}
}

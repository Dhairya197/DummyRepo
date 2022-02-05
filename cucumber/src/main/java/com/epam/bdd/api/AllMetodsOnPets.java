package com.epam.bdd.api;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.epam.bdd.model.Category;
import com.epam.bdd.model.Pet;

import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;


public class AllMetodsOnPets {

	@BeforeMethod
	public void setup() {
		
		RestAssured.baseURI="https://petstore.swagger.io";
		RestAssured.basePath="/v2";
		
		RestAssured.requestSpecification=new RequestSpecBuilder()
				 .setContentType("application/json").build();
	}
	
	@Test
	public void testGet()
	{
		get("/pet/12345").then().statusCode(200);
	}
	
	@Test
	public void testDelete()
	{
		delete("/pet/12345").then().statusCode(200);
	}
	
	@Test
	public void testPost()
	{
		Response res= given()
		       .body("{\r\n"
		       		+ "    \"id\": 12345,\r\n"
		       		+ "    \"category\": {\r\n"
		       		+ "        \"id\": 123545,\r\n"
		       		+ "        \"name\": \"string\"\r\n"
		       		+ "    },\r\n"
		       		+ "    \"name\": \"doggie-new2\",\r\n"
		       		+ "    \"photoUrls\": [\r\n"
		       		+ "        \"string\"\r\n"
		       		+ "    ],\r\n"
		       		+ "    \"tags\": [\r\n"
		       		+ "        {\r\n"
		       		+ "            \"id\": 123545,\r\n"
		       		+ "            \"name\": \"string\"\r\n"
		       		+ "        }\r\n"
		       		+ "    ],\r\n"
		       		+ "    \"status\": \"pending\"\r\n"
		       		+ "}").
		when()
		     .post("/pet");
		
		String id=res.path("id").toString();
		System.out.println(id);
     
	}
	
	@Test
	public void testPost2()
	{
		String BodyData="{\r\n"+"\"id\":123545,\r\n"+"\"category\":{\r\n"+"\"id\":123545,\r\n"+"\"name\":\"string\"\r\n"+"},\r\n"+"\"name\":\"doggie-new2\",\r\n"+"\"photoUrls\":[\r\n"+"\"string\"\r\n"+"],\r\n"+"\"tags\":[\r\n"+"{\r\n"+"\"id\":123545,\r\n"+"\"name\":\"string\"\r\n"+"}\r\n"+"],\r\n"+"\"status\":\"pending\"\r\n"+"}";
		Response res= given()
		       .body(BodyData).
		when()
		     .post("/pet");
		
		String id=res.path("id").toString();
		System.out.println(id);
     
	}
	
	@Test
	public void testPost3()
	{
		//Deserialization --converting byte of stream into java object
		
		Category category=new Category(1, "dog");
		Pet pet=new Pet(12321, "snoopie",category,"available");
		
		Response res= given()
		       .body(pet).
		when()
		     .post("/pet");
		
		String id=res.path("id").toString();
		System.out.println(id);
     
	}
	
	@Test
	public void testGet3()
	{
		
		Pet pet=get("/pet/12321").as(Pet.class);
		System.out.println(pet.getName());
	}
	
	@Test
	public void testPut()
	{
		//Deserialization --converting byte of stream into java object
		
		Category category=new Category(1, "dog");
		Pet pet=new Pet(12321, "snoopie",category,"pending");
		
		Response res= given()
		       .body(pet).
		when()
		     .put("/pet");
		
		String id=res.path("status").toString();
		System.out.println("Updated Value for Field"+ id);
     
	}
	
	@Test
	public void allTest()
	{
		Map<String,Object> categoryMap=new HashMap<String,Object>();
		categoryMap.put("id", 234);
		categoryMap.put("name", "dog");
		
		Map<String,Object> petmap=new HashMap<>();
		petmap.put("name", "doggie-epam");
		petmap.put("status", "pending");
		petmap.put("category", categoryMap);
		Response res=given().body(petmap).when().post("/pet").then().extract().response();
		System.out.println(res.asString());
		String newID= res.path("id").toString();
		
		String body =get("/pet/"+newID).then().extract().response().asString();
		
				
		//String status=given().body(petmap).when().post("/pet").path("status").toString();
		get("/pet/"+newID).then().statusCode(200).and().body("status", equalTo("pending"));
	
		petmap.put("status", "available");
		petmap.put("id", newID);
		
		given().body(petmap).when().put("/pet").then().statusCode(200);
		
		get("/pet/"+newID).then().statusCode(200).and().body("status", equalTo("available"));
		
		delete("/pet/"+newID).then().statusCode(200);
		
		get("/pet/"+newID).then().statusCode(404);
	}
}

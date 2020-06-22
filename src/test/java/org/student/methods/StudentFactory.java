package org.student.methods;

import static io.restassured.RestAssured.given;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class StudentFactory 
{

	@Step("Student:Get Information with end point {0}")
	public static void getInformation(String endpoint)
	{
		Response resp = given().contentType("application/json").when().get(endpoint);

		resp.then().log().all();
	}
	
}

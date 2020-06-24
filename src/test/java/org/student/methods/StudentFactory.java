package org.student.methods;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import com.github.javafaker.Faker;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class StudentFactory 
{

	@Step("Student:Get Information with end point {0}")
	public static Response getInformation(String endpoint)
	{
		Response resp = given().contentType("application/json").when().get(endpoint);
		return resp;
	
	}
	
	@Step("Student:Post Information with {0} {1} {2} {3} {4} with end point {5}")
	public static Response postInformation(String firtname,String lastname,String emailID, String prog,List<String> courses, String endpoint)
	{
		StudentClass student = new StudentClass();
		student.setFirstName(firtname);
		student.setLastName(lastname);
		student.setEmail(emailID);
		student.setProgramme(prog);
		student.setCourses(courses);
		
		Response resp =given().contentType("application/json").body(student).when().post(endpoint);
		return resp;
	}

	@SuppressWarnings("unchecked")
	@Step("Student:Put Information with {0} {1} {2} {3} {4} with end point {5}")
	public static Response updateInformation(String firstName, String lastName, String emailAddress, String title,
			List<String> courses, String endpoint) {
		
		JSONObject student= new JSONObject();
		student.put("firstName", firstName);
		student.put("lastName", lastName);
		student.put("email", emailAddress);
		student.put("programme", title);
		student.put("courses", courses);

		Response resp =given().contentType("application/json").body(student).when().put(endpoint);
		return resp;
	}
	
	@Step("Student:Patch Information with {0} with end point {1}")
	public static Response patchInformation( String emailAddress,String endpoint)
	{
		StudentClass stu = new StudentClass();
		stu.setEmail(emailAddress);
		
		Response resp = given().contentType("application/json").body(stu).when().patch(endpoint);
		return resp;
	
	}
	
	@Step("Student:Delete Information with end point {0}")
	public static Response deteleInformation(String endpoint)
	{
		Response resp = given().contentType("application/json").when().delete(endpoint);
		return resp;
	
	}
}

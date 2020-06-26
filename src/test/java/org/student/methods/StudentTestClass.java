package org.student.methods;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.com.APITestFramework.BaseClass;
import org.com.APITestFramework.Specifications;
import org.com.APITestFramework.StudentFactory;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import io.restassured.response.Response;

@Epic("Epic1 : Testing Allure Report")
@Feature("Feature 1: Testing Allure Report")
public class StudentTestClass extends BaseClass {
	Integer studentId;
	
	@TmsLink("https://hangouts.google.com")
	@Issue("https://www.seleniumeasy.com/")
	@Link("https://docs.qameta.io/allure/#_testng")
	@Description("Description 1 : Testing Allure Report")
	@Story("Story 1 : Testing Allure Report")
	@Step("Create a student")
	@Test(priority=1)
	public void postMethod() {
		Faker faker = new Faker();

		List<String> courses = new ArrayList<String>();
		courses.add("Accounting");
		courses.add("Statistics");

		Response response = StudentFactory.postInformation(faker.name().firstName(), faker.name().lastName(),
				faker.internet().emailAddress(), faker.book().title(), courses, "/student");
		response.then().spec(Specifications.responseSpec(201)).log().all();
	
	}

	@Step("Get all student information")
	@Test(priority=2)
	public void getStudentMethod() {

		Response response = StudentFactory.getInformation("/student/list");
		List<Integer> allID=response.then().spec(Specifications.responseSpec(200)).extract().path("id");
		studentId= Collections.max(allID);

	}
	
	@SuppressWarnings("unchecked")
	@Step("Update a student")
	@Test(priority=3)
	public void putMethod() {
		Faker faker = new Faker();

		List<String> courses = new ArrayList<String>();
		courses.add("Accounting");
		courses.add("Statistics");

		Response response = StudentFactory.updateInformation(faker.name().firstName(), faker.name().lastName(),
				faker.internet().emailAddress(), faker.book().title(), courses, "/student/"+studentId);
		response.then().spec(Specifications.responseSpec(200)).log().all();

	}

	@Step("Get info of one student")
	@Test(priority=4)
	public void getIndividualStudentMethod() {

		Response response = StudentFactory.getInformation("/student/"+studentId);
		response.then().spec(Specifications.responseSpec(200)).log().all();


	}
	
	@Step("Update email of a student")
	@Test(priority=5)
	public void patchMethod() {
		Faker faker = new Faker();

		Response response = StudentFactory.patchInformation(faker.internet().emailAddress(), "/student/"+studentId);
		response.then().spec(Specifications.responseSpec(200)).log().all();

	}

	@Step("Delete a student")
	@Test(priority=6)
	public void deleteMethod() {
		Response response = StudentFactory.deteleInformation("/student/"+studentId);
		response.then().spec(Specifications.responseSpec(204)).log().all();

	}
}

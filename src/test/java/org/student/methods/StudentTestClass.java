package org.student.methods;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.com.APITestFramework.BaseClass;
import org.json.simple.JSONObject;
import org.testng.Assert;
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
	@TmsLink("https://hangouts.google.com")
	@Issue("https://www.seleniumeasy.com/")
	@Link("https://docs.qameta.io/allure/#_testng")
	@Description("Description 1 : Testing Allure Report")
	@Story("Story 1 : Testing Allure Report")

	@Step("Get all student information")
	@Test(priority=1)
	public void getStudentMethod() {

		Response response = StudentFactory.getInformation("/student/list");
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);

	}

	@Step("Get info of one student")
	@Test(priority=2)
	public void getIndividualStudentMethod() {

		Response response = StudentFactory.getInformation("/student/100");
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);

	}

	@Step("Create a student")
	@Test(priority=3)
	public void postMethod() {
		Faker faker = new Faker();

		List<String> courses = new ArrayList<String>();
		courses.add("Accounting");
		courses.add("Statistics");

		Response response = StudentFactory.postInformation(faker.name().firstName(), faker.name().lastName(),
				faker.internet().emailAddress(), faker.book().title(), courses, "/student");
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 201);
	}

	@SuppressWarnings("unchecked")
	@Step("Update a student")
	@Test(priority=4)
	public void putMethod() {
		Faker faker = new Faker();

		List<String> courses = new ArrayList<String>();
		courses.add("Accounting");
		courses.add("Statistics");

		Response response = StudentFactory.updateInformation(faker.name().firstName(), faker.name().lastName(),
				faker.internet().emailAddress(), faker.book().title(), courses, "/student/101");
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Step("Update email of a student")
	@Test(priority=5)
	public void patchMethod() {
		Faker faker = new Faker();

		Response response = StudentFactory.patchInformation(faker.internet().emailAddress(), "/student/101");
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Step("Delete a student")
	@Test(priority=6)
	public void deleteMethod() {
		Response response = StudentFactory.deteleInformation("/student/101");
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 204);
	}
}

package org.com.APITestFramework;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Story;
import io.restassured.response.Response;

@Epic("Epic1 : Testing Allure Report")
@Feature("Feature 1: Testing Allure Report")
public class FirstTestClass {

	@Issue("https://www.seleniumeasy.com/")
	@Description("Description 1 : Testing Allure Report")
	@Story("Story 1 : Testing Allure Report")
	@Test
	public void test1() {
		System.out.println("::::::No Auth:::::::::::::");
		baseURI = "https://reqres.in/api/users/2";

		Response resp = given().contentType("application/json").when().get();

		resp.then().log().all();
		System.out.println("::::::No Auth:::::::::::::");
	}


}

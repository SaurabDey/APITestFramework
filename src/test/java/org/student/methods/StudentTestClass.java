package org.student.methods;

import org.com.APITestFramework.BaseClass;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;

@Epic("Epic1 : Testing Allure Report")
@Feature("Feature 1: Testing Allure Report")
public class StudentTestClass extends BaseClass{
	@TmsLink("https://hangouts.google.com")
	@Issue("https://www.seleniumeasy.com/")
	@Link("https://docs.qameta.io/allure/#_testng")
	@Description("Description 1 : Testing Allure Report")
	@Story("Story 1 : Testing Allure Report")
	
	@Step("Get all student information")
	@Test
	public void getStudentMethod()
	{

		StudentFactory.getInformation("/list");
		
	}
	
	@Step("Get info of one student")
	@Test
	public void getIndividualStudentMethod()
	{

		StudentFactory.getInformation("/100");
		
	}
	
	@Step("Create a student")
	@Test
	public void postMethod()
	{
		
	}
	
	@Step("Update a student")
	@Test
	public void putMethod()
	{
		
	}
	@Step("update email of a student")
	@Test
	public void patchMethod()
	{
		
	}
	@Step("Delete a student")
	@Test
	public void deleteMethod()
	{
		
	}
}

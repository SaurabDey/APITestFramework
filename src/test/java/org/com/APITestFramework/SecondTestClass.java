package org.com.APITestFramework;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Story;

@Epic("Epic 2: Testing Allure Report")
@Feature("Feature 2: Testing Allure Report")
public class SecondTestClass {

	@Link("https://docs.qameta.io/allure/#_testng")
	@Description("Description 2 : Testing Allure Report")
	@Story("Story 2 : Testing Allure Report")
	@Test
	public void test2() {
		System.out.println("::::::Assertion ::::::::::::");
		Assert.assertEquals("Saurab", "Saurabh");
		System.out.println("::::::Assertion :::::::::::::");
	}

}

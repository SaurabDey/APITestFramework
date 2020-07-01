package org.student.methods;

import static io.restassured.RestAssured.given;

import java.util.List;
import java.util.Map;

import org.com.APITestFramework.CommonUtil;
import org.com.APITestFramework.Specifications;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DatabaseConnect {
	String geratedId;
	
	@BeforeTest
	public void bt() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8080;
	}

	@SuppressWarnings("unchecked")
	@Step("Database: Post Information")
	@Test(priority = 1)
	public void postInformation() {
		try {

			Faker faker = new Faker();
			JSONObject book = new JSONObject();
			book.put("name", faker.book().title());
			book.put("description", faker.book().author());
			book.put("price", faker.number().digit());

			Response resp = given().spec(Specifications.requestSpec()).body(book).when().post("/api/v1/items");
			resp.then().spec(Specifications.responseSpec(200));
			geratedId= resp.then().extract().path("id").toString();
			
			List<Object> dataFromDB = CommonUtil.JDBCConnectionIndividaul("select * from item where id='"+geratedId+"'");

			Assert.assertEquals(resp.then().extract().path("name"), dataFromDB.get(0));
			Assert.assertEquals(resp.then().extract().path("description"), dataFromDB.get(1));
			Assert.assertEquals(resp.then().extract().path("price"), dataFromDB.get(2));
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Step("Database: Get Information")
	@Test(priority=2)
	public void getInformation() {
		Response resp = given().spec(Specifications.requestSpec()).when().get("/api/v1/items");
		resp.then().spec(Specifications.responseSpec(200));

	}

	

	@Step("Database: Get individual Information")
	@Test(priority=3)
	public void getIndividualInformation() {
		Response resp = given().spec(Specifications.requestSpec()).when().get("/api/v1/items/" + geratedId);
		resp.then().spec(Specifications.responseSpec(200));
		
		List<Object> dataFromDB = CommonUtil.JDBCConnectionIndividaul("select * from item where id='"+geratedId+"'");

		Assert.assertEquals(resp.then().extract().path("name"), dataFromDB.get(0));
		Assert.assertEquals(resp.then().extract().path("description"), dataFromDB.get(1));
		Assert.assertEquals(resp.then().extract().path("price"), dataFromDB.get(2));

	}

	@SuppressWarnings("unchecked")
	@Step("Database: Put Information")
	@Test(priority=4)
	public void updateInformation() {

		Faker faker = new Faker();
		JSONObject book = new JSONObject();
		book.put("id", geratedId);
		book.put("name", faker.book().title());
		book.put("description", faker.book().author());
		book.put("price", faker.number().digit());

		Response resp = given().spec(Specifications.requestSpec()).body(book).when().put("/api/v1/items/" + geratedId);
		resp.then().spec(Specifications.responseSpec(200));
		
		List<Object> dataFromDB = CommonUtil.JDBCConnectionIndividaul("select * from item where id='"+geratedId+"'");

		Assert.assertEquals(resp.then().extract().path("name"), dataFromDB.get(0));
		Assert.assertEquals(resp.then().extract().path("description"), dataFromDB.get(1));
		Assert.assertEquals(resp.then().extract().path("price"), dataFromDB.get(2));

	}

	@Step("Database: Delete Information")
	@Test(priority=5)
	public void deteleInformation() {
		Response resp = given().spec(Specifications.requestSpec()).when().delete("/api/v1/items/" + geratedId);
		resp.then().spec(Specifications.responseSpec(200));

	}
}

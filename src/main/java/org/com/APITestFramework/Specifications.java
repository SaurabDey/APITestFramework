package org.com.APITestFramework;

import static org.hamcrest.Matchers.equalTo;

import java.util.concurrent.TimeUnit;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.Matchers.*;

public class Specifications {
	
	
	public static ResponseSpecification responseSpec(int expectedStatusCode)
	{
		ResponseSpecification responseSpe;
		ResponseSpecBuilder responsebuilder= new ResponseSpecBuilder() ;
		responsebuilder.expectStatusCode(expectedStatusCode);
		responsebuilder.expectContentType("application/json");
		responsebuilder.expectResponseTime(lessThanOrEqualTo(15L),TimeUnit.SECONDS);
		responseSpe=responsebuilder.build();
		
		return responseSpe;
	}
	
	public static RequestSpecification requestSpec()
	{
		RequestSpecification requestSpe;
		RequestSpecBuilder requestbuilder= new RequestSpecBuilder() ;
		requestbuilder.setContentType("application/json");
		requestbuilder.addFilter(new AllureRestAssured());
		requestSpe=requestbuilder.build();
		return requestSpe;
	}
	
}

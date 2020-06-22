package org.com.APITestFramework;
import java.util.Properties;

import org.com.APITestFramework.CommonUtil;
import org.testng.annotations.BeforeTest;

import io.restassured.RestAssured;

public class BaseClass 
{

	@BeforeTest
	public void bt()
	{
		Properties proVaraible=CommonUtil.readpropertyFile();
		RestAssured.baseURI=proVaraible.getProperty("base.URI");
		RestAssured.port=Integer.parseInt(proVaraible.getProperty("base.port"));
	}
}

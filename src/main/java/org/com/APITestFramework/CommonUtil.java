package org.com.APITestFramework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class CommonUtil {

	public static Properties readpropertyFile() {
		File f = new File("Resource/environment.properties");
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(f);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Properties pro = new Properties();
		try {
			pro.load(fis);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return pro;
	}

	public static Map<Integer, List<Object>> JDBCConnectionFetchAll(String query) {
		Connection con = null;
		Map<Integer, List<Object>> items = new HashMap<Integer, List<Object>>();
		try {

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/selenium_database", "root", "selenium");
			Statement stm = con.createStatement();

			ResultSet rs = stm.executeQuery(query);

			while (rs.next()) {

				List<Object> info = new ArrayList<Object>();
				info.add(rs.getString("name"));
				info.add(rs.getString("description"));
				info.add(rs.getInt("price"));

				items.put(rs.getInt("id"), info);
			}

			System.out.println(items);
		} catch (Exception e) {
			System.out.println("Connection was not successful");
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return items;
	}

	public static List<Object> JDBCConnectionIndividaul(String query) {
		Connection con = null;
		List<Object> info = new ArrayList<Object>();
		try {

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/selenium_database", "root", "selenium");
			Statement stm = con.createStatement();

			ResultSet rs = stm.executeQuery(query);

			while (rs.next()) {
				info.add(rs.getString("name"));//0
				info.add(rs.getString("description"));//1
				info.add(rs.getInt("price"));//2
			}
			System.out.println(info);
		} catch (Exception e) {
			System.out.println("Connection was not successful");
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return info;
	}

}

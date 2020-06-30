package org.com.APITestFramework;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCConnection {

	public static void main(String[] args) throws SQLException {
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/selenium_database", "root", "selenium");
		Statement stm=con.createStatement();
		
		ResultSet rs= stm.executeQuery("select * from item");
		
		Map<Integer, Object> items= new HashMap<Integer, Object>();
		while (rs.next()) {
			
			List<Object> info= new ArrayList<Object>();
			info.add(rs.getString("name"));
			info.add(rs.getString("description"));
			info.add(rs.getInt("price"));
			
			items.put(rs.getInt("id"), info);
		}
		con.close();
		
		System.out.println(items);
	}

}

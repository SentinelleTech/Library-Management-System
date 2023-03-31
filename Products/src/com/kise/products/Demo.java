package com.kise.products;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Demo {
	
	Connection con;
	String db_url = "jdbc:mysql://localhost:3306/Products_DB";

	public Demo() {
		
		try {
			
			System.out.println("Connecting...");
			
			con = DriverManager.getConnection(db_url, "root", "");
			
			System.out.println("connected");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		new Demo();

	}

}

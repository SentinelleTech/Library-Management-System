package com.kise.products;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Pool {
	
	String schema;
	String db_username;
	String db_password;
	Connection con;

	public Pool() {
		
	}
	
	public Pool(String u_name, String p_word) {
		
		this.db_username = u_name;
		this.db_password = p_word;
		
	}
	
	public Pool(String u_name, String p_word, String db) {
		this.db_username = u_name;
		this.db_password = p_word;
		this.schema = db;
	}
	
	public Connection createConnection() {
		
		String db_url = "jdbc:mysql://localhost:3306/" + schema;
		
		try {
			
			con = DriverManager.getConnection(db_url, db_username, db_password);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return con;
		
	}

}

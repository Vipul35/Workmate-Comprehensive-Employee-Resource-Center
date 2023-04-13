package com.example.SpringAssignment3;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionClass {

	public static Connection connection() {
		Connection conn=null;
		try {
			//Class.forName("com.postgresql.Driver");
			conn=DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","123456");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		return conn;
	}

}

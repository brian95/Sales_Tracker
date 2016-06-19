package com.rhcloud.rdisalestracker.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;

public class UserController {
	private static final String DB_HOST = "jdbc:mysql://localhost/";
	private static final String DB_NAME = "salesdb?";
	private static final String DB_USER = "user=admin&";
	private static final String DB_PASS = "pass=admin&useSSL=false";
	private Connection conn;
	private Statement stmt;
	private ResultSet result;
	private User user;
	
	public UserController() {
		
	}
	
	public Observable<User> login(String username, String pass) {
		if (conn == null) {
			conn = open();
		}
		User mUser = null;
		String sql = "SELECT * FROM users where username = " 
				+ "'" + username + "'";
		try {
			stmt = conn.createStatement();
		result = stmt.executeQuery(sql);
		
		while (result.next()) {
		long id = result.getLong("userId");
		String name = result.getString("username");
		String password = result.getString("password");
		mUser = new User(id, name, password);
		}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return Observable.just(mUser);
	}
	
	private Connection open() {
		Connection conn = null;
		if (conn == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager
						.getConnection("jdbc:mysql://localhost/salesdb?" + "user=admin&password=admin&useSSL=false");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return conn;
	}

	

}

package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class OracleConnection {

	public static Connection getConnection(final String user, final String password, final String host, final String instance) throws ClassNotFoundException, SQLException {
		final Properties props = new Properties();
		props.put("user", user);
		props.put("password", password);
		Class.forName("oracle.jdbc.driver.OracleDriver");
		final Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@" + host + ":1521:" + instance, props);
		return conn;
	}

	

}

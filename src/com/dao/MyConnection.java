package com.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class MyConnection {

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		return OracleConnection.getConnection("cherrera2018", "Pass123$", "oraclelinux.eng.fau.edu", "r11g");
	}

	public static void close(final Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException se) {
				// nothing
			}
		}
	}

}

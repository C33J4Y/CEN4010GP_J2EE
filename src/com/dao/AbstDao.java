package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AbstDao {

	protected Connection conn;

	public AbstDao(final Connection conn) {
		this.conn = conn;
	}

	protected String getJ2EEFilePath(final String sub) {
		return '/' + this.getClass().getPackage().getName().replace('.', '/') + "/" + sub + "/" + this.getClass().getSimpleName() + ".query";
	}

	protected void close(final Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException se) {
				// nothing
			}
		}
	}

	protected void close(final PreparedStatement pstmt) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException se) {
				// nothing
			}
		}
	}

	protected void close(final ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException se) {
				// nothing
			}
		}
	}
}

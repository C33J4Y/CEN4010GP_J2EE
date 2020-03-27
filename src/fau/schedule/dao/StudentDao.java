package fau.schedule.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dao.AbstDao;
import fau.schedule.vo.StudentVo;


public class StudentDao extends AbstDao {

	public StudentDao(Connection conn) {
		super(conn);
	}

	public void createUser(final StudentVo studentVo) throws IOException {
		// Question 2.1
		final String query = "INSERT INTO student (zno, fname, lname, password) VALUES (?,?,?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, studentVo.getZno());
			pstmt.setString(2, studentVo.getFName());
			pstmt.setString(3, studentVo.getLName());
			pstmt.setString(4, studentVo.getPassword());
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(getClass().getName() + ".createUser()", e);
		} finally {
			close(pstmt);
		}
	}

	public String retrievePassword(final int zno) throws IOException {
		// Question 2.2
		final String query = "SELECT password FROM student where zno = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, zno);
			rs = pstmt.executeQuery();
			return rs.next() ? (rs.getString(1)) : null; // It returns the password associated with zno.
		} catch (Exception e) {
			throw new RuntimeException(getClass().getName() + ".retrievePassword()", e);
		} finally {
			close(rs);
			close(pstmt);
		}
		 
	}

	public void updatePassword(final int zno, final String password) throws IOException {
		// Question 2.3
		final String sql = "UPDATE student SET password = ? WHERE zno = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, password);
			pstmt.setInt(2, zno);
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(getClass().getName() + ".updatePassword()", e);
		} finally {
			close(pstmt);
		}
	}

	public void deleteUser(final int zno) throws IOException {
		// Question 2.4
		final String sql = "DELETE FROM student WHERE zno = ?";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, zno);
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(getClass().getName() + ".deleteUser()", e);
		} finally {
			close(pstmt);
		}
	}

	public StudentVo retrieveStudent(final int zno) throws IOException {
		// Question 2.5
		final String query = "SELECT fname, lname, password FROM student where zno = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, zno);
			rs = pstmt.executeQuery();
			return rs.next() ? new StudentVo(zno, rs.getString(1), rs.getString(2), rs.getString(3)) : null; // It returns a StudentVo.
		} catch (Exception e) {
			throw new RuntimeException(getClass().getName() + ".retrieveStudent()", e);
		} finally {
			close(rs);
			close(pstmt);
		}
		
	}

}

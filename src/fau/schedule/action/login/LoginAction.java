package fau.schedule.action.login;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.MyConnection;

import fau.schedule.dao.StudentDao;
import fau.schedule.servlet.ActionIF;
import fau.schedule.vo.StudentVo;

public class LoginAction implements ActionIF {

	public String process(final HttpServletRequest req, final HttpServletResponse res) throws Exception {
		final int zno = Integer.parseInt(req.getParameter("zno").trim());
		final String password = req.getParameter("password".trim());
		final Connection conn = MyConnection.getConnection();
		final StudentDao studentDao = new StudentDao(conn);
		final StudentVo studentVo = studentDao.retrieveStudent(zno);
		if (studentVo != null && password.equals(studentVo.getPassword())) {
			createSession(req, studentVo);
			req.setAttribute("message", " Login Successful");
			return "/message.jsp";
		} else {
			req.setAttribute("message", "Login Fail");
			return "/error.jsp";
		}
	}

	public void createSession(final HttpServletRequest req, final StudentVo studentVo) {
		HttpSession session = req.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		session = req.getSession(true);
		session.setAttribute("studentVo", studentVo);
	}
}

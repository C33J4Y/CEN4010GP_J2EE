package fau.schedule.action.login;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.MyConnection;

import fau.schedule.dao.StudentDao;
import fau.schedule.servlet.ActionIF;
import fau.schedule.vo.StudentVo;

public class CreateUserAction implements ActionIF {

	public String process(final HttpServletRequest req, final HttpServletResponse res) throws Exception {
		final int zno = Integer.parseInt(req.getParameter("zno").trim());
		final String fname = req.getParameter("fname").trim();
		final String lname = req.getParameter("lname").trim();
		final String password = req.getParameter("password").trim();
		final Connection conn = MyConnection.getConnection();
		final StudentDao studentDao = new StudentDao(conn);
		if (studentDao.retrievePassword(zno) == null) {
			studentDao.createUser(new StudentVo(zno, fname, lname, password));
			req.setAttribute("message", "The user was successfully created.");
			return "/message.jsp";
		} else {
			req.setAttribute("message", "Duplicated zno.");
			return "/error.jsp";
		}
	}
}

package fau.schedule.action.login;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.MyConnection;

import fau.schedule.dao.StudentDao;
import fau.schedule.servlet.ActionIF;

public class DeleteUserAction implements ActionIF {

	public String process(final HttpServletRequest req, final HttpServletResponse res) throws Exception {
		final int zno = Integer.parseInt(req.getParameter("zno").trim());
		final String password1 = req.getParameter("password").trim();
		final Connection conn = MyConnection.getConnection();
		final StudentDao studentDao = new StudentDao(conn);
		final String password2 = studentDao.retrievePassword(zno);
		if (password1.equals(password2)) {
			studentDao.deleteUser(zno);
			req.setAttribute("message", "The user was successfully deleted.");
			return "/message.jsp";
		} else {
			req.setAttribute("message", "Incorrect Password.");
			return "/error.jsp";
		}

	}

}

package fau.schedule.action.login;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.MyConnection;

import fau.schedule.dao.StudentDao;
import fau.schedule.servlet.ActionIF;

public class ChangePasswordAction implements ActionIF {

	public String process(final HttpServletRequest req, final HttpServletResponse res) throws Exception {
		final int zno = Integer.parseInt(req.getParameter("zno"));
		final String oldPassword1 = req.getParameter("oldPassword");
		final String newPassword = req.getParameter("newPassword");
		final Connection conn = MyConnection.getConnection();
		final StudentDao studentDao = new StudentDao(conn);
		final String oldPassword2 = studentDao.retrievePassword(zno);
		if (oldPassword1.equals(oldPassword2)) {
			studentDao.updatePassword(zno, newPassword);
			req.setAttribute("message", "The password was successfully updated");
			return "/message.jsp";
		} else {
			req.setAttribute("message", "Incorrect Password");
			return "/error.jsp";
		}
	}

}

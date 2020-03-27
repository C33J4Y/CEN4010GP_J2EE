package fau.schedule.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "/COP4703Schedule", urlPatterns = { "/myAction" })

public class MyServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
		super.doGet(req, res);
		service(req, res);
	}

	protected void doPost(final HttpServletRequest req, final HttpServletResponse res) throws ServletException, IOException {
		super.doPost(req, res);
		service(req, res);
	}

	// Service
	// 1) Retrieve the value of "action" from Request.
	// 2) Create Instance of Action
	// 3) Action return the location of "JSP" file.

	protected void service(final HttpServletRequest req, final HttpServletResponse res) throws IOException {
		final String actionName = req.getParameter("action");
		if (actionName == null) {
			printErr(req, res, "No action name", null);
			return;
		}
		try {
			System.out.println("Call " + actionName);
			final ActionIF action = (ActionIF) Class.forName(actionName).newInstance();
			final String clientIPAddr = getClientIpAddr(req);
			final String returnJsp = action.process(req, res);
			if (returnJsp != null) {
				printJsp(req, res, returnJsp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			printErr(req, res, "Action Error: " + actionName, e);
			return;
		}
	}

	private void printJsp(final HttpServletRequest req, HttpServletResponse res, String jspfile) {
		try {
			final RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(jspfile);
			dispatcher.forward(req, res);
		} catch (Exception e) {
			this.printErr(req, res, "printJsp Failed", e);
		}
	}

	private void printErr(HttpServletRequest req, HttpServletResponse res, String message, Exception e) {
		try {
			final StringBuilder strbuf = new StringBuilder();
			strbuf.append("JSP/Servlet Error (Catched by MyServlet) :[");
			strbuf.append(message);
			strbuf.append("] Request URI: " + req.getRequestURI());
			String user = req.getRemoteUser();
			if (user != null) {
				strbuf.append(", USER: " + user);
			}
			strbuf.append(", USER LOCATION: " + req.getRemoteHost() + "(" + req.getRemoteAddr() + ")");
			java.io.PrintWriter out = res.getWriter();
			res.setContentType("text/html;charset=UTF-8");
			out.println("<html><head><title>Error Message</title></head><body bgcolor=white><xmp>");
			out.println(strbuf.toString());
			if (e != null) {
				e.printStackTrace(out);
			}
			out.println("</xmp></body></html>");
			out.flush();
			out.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private String getClientIpAddr(final HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

}
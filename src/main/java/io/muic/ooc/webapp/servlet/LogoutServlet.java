package io.muic.ooc.webapp.servlet;

import io.muic.ooc.webapp.Routable;
import io.muic.ooc.webapp.service.SecurityService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutServlet extends HttpServlet implements Routable {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getSession().invalidate();
		String message = "Successfully logged out.";
		req.setAttribute("message", message);
		req.setAttribute("messagestatus", "success");
		RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/login.jsp");
		rd.include(req, resp);
	}

	@Override
	public String getMapping() {
		return "/logout";
	}

}

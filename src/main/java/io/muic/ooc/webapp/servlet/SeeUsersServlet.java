package io.muic.ooc.webapp.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SeeUsersServlet extends HttpServlet implements Routable {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (securityService.isAuthorized(req)) {
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/users.jsp");
			rd.include(req, resp);
		} else{
			resp.sendRedirect("/login");
		}
	}

	@Override
	public String getMapping() {
		return "/users";
	}

}

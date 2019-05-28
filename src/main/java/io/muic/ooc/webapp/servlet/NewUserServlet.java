package io.muic.ooc.webapp.servlet;

import io.muic.ooc.webapp.Routable;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NewUserServlet extends HttpServlet implements Routable {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		boolean authorised = securityService.isAuthorized(req);
		if (authorised) {
			String username = (String) req.getSession().getAttribute("username");
			String name = securityService.getName(username);
			req.setAttribute("name", name);
			RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/newuser.jsp");
			rd.include(req, resp);
		} else{
			resp.sendRedirect("/");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String name = req.getParameter("name");
		boolean success = securityService.addNewUser(username, password, name);
		if (success){
			String message = "Successfully created a new user.";
			req.setAttribute("message", message);
			req.setAttribute("messagestatus", "success");
		} else{
			String message = "An error occurred in creating a new user.";
			req.setAttribute("message", message);
			req.setAttribute("messagestatus", "danger");
		}
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/home.jsp");
		rd.include(req, resp);
	}

	@Override
	public String getMapping() {
		return "/newuser";
	}

}

package io.muic.ooc.webapp.servlet;

import io.muic.ooc.webapp.service.SecurityService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditUserServlet extends HttpServlet implements Routable {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id-edit"));
		String username = req.getParameter("username-edit");
		String name = req.getParameter("name-edit");
		String password = req.getParameter("password-edit");
		boolean success = SecurityService.getInstance().editUser(id, username, password, name);
		if (success){
			String message = "Successfully edited data of " + name + ".";
			req.setAttribute("message", message);
			req.setAttribute("messagestatus", "success");
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/users.jsp");
			rd.include(req, resp);
		} else{
			String message = "An error occurred in editing user " + name + ".";
			req.setAttribute("message", message);
			req.setAttribute("messagestatus", "danger");
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/users.jsp");
			rd.include(req, resp);
		}
	}

	@Override
	public String getMapping() {
		return "/edituser";
	}

}

package io.muic.ooc.webapp.servlet;

import io.muic.ooc.webapp.service.SecurityService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RemoveUserServlet extends HttpServlet implements Routable {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id-to-remove"));
		int sessionId = Integer.parseInt(req.getParameter("sessionId"));
		if(id != sessionId) {
			boolean success = SecurityService.getInstance().removeUser(id);
			if (success) {
				String message = "Successfully removed a user.";
				req.setAttribute("message", message);
				req.setAttribute("messagestatus", "success");
			} else {
				String message = "An error occurred in removing user.";
				req.setAttribute("message", message);
				req.setAttribute("messagestatus", "danger");
			}
		} else{
			String message = "Why you trying to erase yourself?.";
			req.setAttribute("message", message);
			req.setAttribute("messagestatus", "danger");
		}
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/users.jsp");
		rd.include(req, resp);
	}

	@Override
	public String getMapping() {
		return "/removeuser";
	}

}

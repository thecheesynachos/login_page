/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.muic.ooc.webapp.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gigadot
 */
public class HomeServlet extends HttpServlet implements Routable {

    @Override
    public String getMapping() {
        return "/index.jsp";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (securityService.isAuthorized(request)) {
            // do MVC in here
            String username = (String) request.getSession().getAttribute("username");
            int id = securityService.getId(username);
            request.getSession().setAttribute("sessionId", id);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/users.jsp");
            rd.include(request, response);
        } else {
            response.sendRedirect("/login");
        }
    }

}

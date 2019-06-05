package io.muic.ooc.webapp.service;

import io.muic.ooc.webapp.servlet.HomeServlet;
import io.muic.ooc.webapp.servlet.Routable;
import io.muic.ooc.webapp.servlet.ServletFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WebFilter implements Filter {

	HttpServlet home = new HomeServlet();

	@Override
	public void init(FilterConfig filterConfig) {

	}

	@Override
	public void doFilter(
			ServletRequest request,
			ServletResponse response,
			FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String path = httpRequest.getServletPath();
		boolean found = false;
		for (Routable r : ServletFactory.generateServlets()){
			if(path.equals(r.getMapping())){
				HttpServlet hs = (HttpServlet) r;
				hs.service(httpRequest, httpResponse);
				found = true;
			}
		}
		if (!found) {
			home.service(httpRequest, httpResponse);
		}
	}

	@Override
	public void destroy() {

	}
}

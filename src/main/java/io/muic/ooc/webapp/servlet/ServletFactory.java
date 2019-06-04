package io.muic.ooc.webapp.servlet;

import java.util.ArrayList;
import java.util.List;

public class ServletFactory {

	private static final List<Routable> routables = new ArrayList<>();

	static {
		routables.add(new HomeServlet());
		routables.add(new LoginServlet());
		routables.add(new LogoutServlet());
		routables.add(new NewUserServlet());
		routables.add(new SeeUsersServlet());
		routables.add(new RemoveUserServlet());
		routables.add(new EditUserServlet());
	}

	public static List<Routable> generateServlets(){
		return routables;
	}

}

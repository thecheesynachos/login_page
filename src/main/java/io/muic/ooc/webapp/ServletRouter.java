/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.muic.ooc.webapp;

import io.muic.ooc.webapp.servlet.*;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.http.HttpServlet;

/**
 *
 * @author gigadot
 */
public class ServletRouter {


    public void init(Context ctx) {
        for (Routable routable : ServletFactory.generateServlets()) {
            try {
                String name = routable.getClass().getSimpleName();
                Tomcat.addServlet(ctx, name, (HttpServlet) routable);
                ctx.addServletMapping(routable.getMapping(), name);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}

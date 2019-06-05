package io.muic.ooc.webapp;

import java.io.File;
import javax.servlet.ServletException;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.FilterDef;
import org.apache.tomcat.util.descriptor.web.FilterMap;

public class Main {


    public static void main(String[] args) {

        File docBase = new File("src/main/webapp/");
        docBase.mkdirs();
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(7742);

        ServletRouter servletRouter = new ServletRouter();

        Context ctx;
        try {

            ctx = tomcat.addWebapp("", docBase.getAbsolutePath());
            servletRouter.init(ctx);

            Class filterClass = WebFilter.class;
            FilterDef myFilterDef = new FilterDef();
            myFilterDef.setFilterClass(filterClass.getName());
            myFilterDef.setFilterName(filterClass.getSimpleName());
            ctx.addFilterDef(myFilterDef);

            FilterMap myFilterMap = new FilterMap();
            myFilterMap.setFilterName(filterClass.getSimpleName());
            myFilterMap.addURLPattern("/*");
            ctx.addFilterMap(myFilterMap);

            tomcat.start();
            tomcat.getServer().await();
        } catch (ServletException | LifecycleException ex) {
            ex.printStackTrace();
        }

    }

}

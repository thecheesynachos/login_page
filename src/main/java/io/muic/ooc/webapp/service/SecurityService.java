/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.muic.ooc.webapp.service;

import javax.servlet.http.HttpServletRequest;

import io.muic.ooc.webapp.MySQLJava;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author gigadot
 */
public class SecurityService {

    private MySQLJava database = MySQLJava.getInstance();

    private static SecurityService securityService = new SecurityService();

    public static SecurityService getInstance() {
        return securityService;
    }
    
    public boolean isAuthorized(HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("username");
        // do checking
       return (username != null && database.hasUser(username));
    }
    
    public boolean authenticate(String username, String password, HttpServletRequest request) {
        boolean isMatched = database.loginSuccess(username, password);
        if (isMatched) {
            request.getSession().setAttribute("username", username);
            return true;
        } else {
            return false;
        }
    }

    public String getName(String username){
        return database.getName(username);
    }

    public String getName(int id){
        return database.getName(id);
    }

    public int getId(String username){
        return database.getId(username);
    }

    public boolean addNewUser(String username, String password, String name){
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        return database.addNewUser(username, hashedPassword, name);
    }

    public boolean removeUser(int id){
        return database.removeUser(id);
    }

    public boolean editUser(int id, String username, String password, String name){
        return database.editUser(id, username, password, name);
    }
    
    public void logout(HttpServletRequest request) {
        request.getSession().invalidate();
    }
    
}

package io.muic.ooc.webapp.servlet;

import io.muic.ooc.webapp.service.SecurityService;

public interface Routable {

    SecurityService securityService = SecurityService.getInstance();

    String getMapping();

}

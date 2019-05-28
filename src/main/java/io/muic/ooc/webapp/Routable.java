package io.muic.ooc.webapp;

import io.muic.ooc.webapp.service.SecurityService;

public interface Routable {

    SecurityService securityService = SecurityService.getInstance();

    String getMapping();

}

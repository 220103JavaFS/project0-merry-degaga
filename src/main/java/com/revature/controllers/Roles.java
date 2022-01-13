package com.revature.controllers;
import io.javalin.core.security.RouteRole;


public interface Roles {
    enum Role implements RouteRole {
        MANAGER,
        EMPLOYEE,
        CUSTOMER,
        ALL;
    }
}

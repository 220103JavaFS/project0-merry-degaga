package com.revature.controllers;
import io.javalin.core.security.RouteRole;


interface Roles {
    enum Role implements RouteRole {
        MANAGER,
        EMPLOYEE,
        CUSTOMER,
        ALL;
    }
}

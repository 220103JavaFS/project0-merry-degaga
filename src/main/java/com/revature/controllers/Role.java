package com.revature.controllers;
import io.javalin.core.security.RouteRole;

    enum Role implements RouteRole {
        MANAGER,
        EMPLOYEE,
        CUSTOMER,
    }

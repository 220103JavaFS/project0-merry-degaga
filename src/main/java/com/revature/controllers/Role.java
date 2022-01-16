package com.revature.controllers;
import io.javalin.core.security.RouteRole;

public enum Role implements RouteRole {
        MANAGER,
        EMPLOYEE,
        CUSTOMER,
    }

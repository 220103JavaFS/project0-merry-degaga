package com.revature.controllers;

import io.javalin.core.security.AccessManager;
import io.javalin.core.security.RouteRole;
import io.javalin.http.Context;

public interface Roles {
    enum Role implements RouteRole {
        ADMIN,
        EMPLOYEE,
        CUSTOMER,
        ALL;
    }
    Role getUserRole(Context ctx);


}

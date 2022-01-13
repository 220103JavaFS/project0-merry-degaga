package com.revature.controllers;

import io.javalin.Javalin;
import io.javalin.core.security.RouteRole;

public abstract class Controller {
    public abstract void addRoutes(Javalin app);
}

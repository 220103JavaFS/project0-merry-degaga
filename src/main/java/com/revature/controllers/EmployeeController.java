package com.revature.controllers;

import io.javalin.Javalin;
import io.javalin.http.Handler;


public class EmployeeController extends Controller {

    private Handler getMenu = (ctx) -> {
        ctx.html("<h1>Employee's menu view</h1>");
        ctx.status(200);
    };

    public void addRoutes(Javalin app) {
        app.get("/menu123", getMenu);
    }
}

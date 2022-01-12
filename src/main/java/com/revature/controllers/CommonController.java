package com.revature.controllers;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class CommonController extends Controller{

    //call declare and init a service layer object

    private Handler getMenu = (ctx) -> {
        ctx.html("<h1>no menu at this time</h1>");
        ctx.status(200);
    };

    @Override
    public void addRoutes(Javalin app) {
        app.get("/menu", getMenu);
    }
}

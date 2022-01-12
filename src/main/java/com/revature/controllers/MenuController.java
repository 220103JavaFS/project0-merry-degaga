package com.revature.controllers;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class MenuController extends Controller {

    //call declare and init a service layer object
    private Handler getMenu = (ctx) -> {
        ctx.html("<h1>no menu at this time</h1>");
        ctx.status(200);
    };

    private Handler addItem = (ctx) -> {

    };
    private Handler removeItem = (ctx) -> {

    };
    private Handler getTotalOrder = (ctx) -> {

    };

    @Override
    public void addRoutes(Javalin app) {
        app.get("/menu", getMenu);
        app.get("/cart", getTotalOrder);
        app.put("/menu{:item}", addItem);
        app.put("/menu{:item}", removeItem);

    }


}




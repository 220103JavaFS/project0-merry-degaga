package com.revature.controllers;
import io.javalin.Javalin;
import io.javalin.http.Handler;



public class MenuController extends Controller implements Roles {


    //call declare and init a service layer object
    private Handler getMenu = (ctx) -> {
        System.out.println("role?????");
        ctx.html("<h1>no menu at this time</h1>");
        ctx.status(200);
    };

    private Handler addItemToCart = (ctx) -> {

    };
    private Handler removeItemFromCart = (ctx) -> {

    };
    private Handler getTotalOrder = (ctx) -> {

    };

    @Override
    public void addRoutes(Javalin app) {
        app.get("/menu", getMenu, Role.ALL);
        app.get("/cart", getTotalOrder, Role.ALL);
        app.put("/menu/add", addItemToCart, Role.ALL);
        app.put("/menus/remove", removeItemFromCart, Role.ALL);

    }



}




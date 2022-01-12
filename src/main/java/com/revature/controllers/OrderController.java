package com.revature.controllers;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class OrderController extends Controller{

    private Handler getAllOrders = (ctx) -> {

    };
    private Handler completeOrder = (ctx) -> {

    };

    @Override
    public void addRoutes(Javalin app) {
        app.get("/orders",getAllOrders);
        app.put("/complete{}", completeOrder);
    }
}

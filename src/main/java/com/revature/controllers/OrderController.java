package com.revature.controllers;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.Handler;


public class OrderController extends Controller implements Roles{
    @Override
    public Role getUserRole(Context ctx) {
        return null;
    }

    private Handler getAllOrders = (ctx) -> {

    };
    private Handler completeOrder = (ctx) -> {

    };
    
    @Override
    public void addRoutes(Javalin app) {
        app.get("/orders",getAllOrders, Role.EMPLOYEE);
        app.put("/complete{}", completeOrder, Role.EMPLOYEE, Role.ADMIN);
    }


}

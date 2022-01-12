package com.revature.controllers;

import io.javalin.Javalin;
import io.javalin.core.security.AccessManager;
import io.javalin.core.security.RouteRole;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

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

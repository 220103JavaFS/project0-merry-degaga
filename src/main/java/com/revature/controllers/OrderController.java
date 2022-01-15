package com.revature.controllers;
import com.revature.service.OrderService;
import io.javalin.Javalin;
import io.javalin.http.Handler;


public class OrderController extends Controller {
    private OrderService service = new OrderService();
    public OrderController(){}

    private Handler getAllOrders = (ctx) -> {

    };
    private Handler completeOrder = (ctx) -> {

    };

    @Override
    public void addRoutes(Javalin app) {
        app.get("/orders",getAllOrders, Role.MANAGER);
        app.put("/complete", completeOrder, Role.MANAGER);
    }


}

package com.revature.controllers;
import com.revature.service.OrderService;
import com.revature.users.OrderDTO;
import io.javalin.Javalin;
import io.javalin.http.Handler;

import java.util.ArrayList;

public class OrderController extends Controller {
    private OrderService service = new OrderService();
    private ArrayList<OrderDTO> orders = null;
    public OrderController(){}

    private Handler getAllOrders = (ctx) -> {
        if(ctx.req.getSession(false)!=null){
            orders = service.getAllOrders();
            ctx.json(orders);
            ctx.status(200);
        }
        else {
            ctx.status(401);
        }

    };
    private Handler completeOrder = (ctx) -> {
        if(ctx.req.getSession(false)!=null){
            if(orders == null) {
                ctx.status(400); //please view the orders first
            }
            service.completeOrder(orders.get(0).orderID); //automatically completes first order in table.
            ctx.status(200);
        }
        else {
            ctx.status(401);
        }
    };

    //if there is time
    private Handler viewProfits = (ctx) -> {
      //service.viewProfits();
    };

    @Override
    public void addRoutes(Javalin app) {
        app.get("/orders",getAllOrders, Role.MANAGER, Role.EMPLOYEE);
        app.delete("/complete", completeOrder, Role.MANAGER, Role.EMPLOYEE);
    }


}

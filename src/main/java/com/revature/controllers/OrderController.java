package com.revature.controllers;
import com.revature.service.OrderService;
import io.javalin.Javalin;
import io.javalin.http.Handler;

public class OrderController extends Controller {
    private OrderService service = new OrderService();
    public OrderController(){}

    private Handler getAllOrders = (ctx) -> {
        if(ctx.req.getSession(false)!=null){
            service.getAllOrders();
            ctx.status(200);
        }
        else {
            ctx.status(401);
        }

    };
    private Handler completeOrder = (ctx) -> {
        if(ctx.req.getSession(false)!=null){
            service.completeOrder(); //automatically completes first order in table.
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

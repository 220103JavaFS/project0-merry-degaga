package com.revature.controllers;
import com.revature.service.OrderService;
import com.revature.users.OrderDTO;
import io.javalin.Javalin;
import io.javalin.http.Handler;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderController extends Controller {
    private OrderService service = new OrderService();
    private ArrayList<OrderDTO> orders = null;
    private static Logger log = LoggerFactory.getLogger(Controller.class);

    public OrderController(){}

    private Handler getAllOrders = (ctx) -> {
        log.info("Attempting to get all submitted orders");
        if(ctx.req.getSession(false)!=null){
            orders = service.getAllOrders();
            ctx.json(orders);
            log.info("Successfully got all submitted orders");
            ctx.status(200);
        }
        else {
            log.info("User not authorized to access submitted orders");
            ctx.status(401);
        }

    };
    private Handler completeOrder = (ctx) -> {
        if(ctx.req.getSession(false)!=null){
            log.info("Attempting to complete an order from orders submitted list");
            if(orders == null) {
                log.info("Unable to complete an order, please view orders first");
                ctx.status(400); //please view the orders first
            }
            else if(orders.size() == 0) { //nothing in orders
                log.info("No pending orders...");
                ctx.html("<p>No pending orders</p>");
                ctx.status(200);
            }
            else {
                log.info("Attempting to complete order ... " + orders.get(0).orderID);
                service.completeOrder(orders.get(0).orderID); //automatically completes first order in table.
                log.info("Order id " + orders.get(0).orderID + " completed sucessfully");
                ctx.status(200);
            }
        }
        else {
            log.info("User not authorized to access this page");
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

package com.revature.controllers;

import com.revature.service.CartService;
import com.revature.users.Customer;
import com.revature.users.FoodDTO;
import io.javalin.Javalin;
import io.javalin.http.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CartController extends Controller {
    private CartService service = new CartService();
    private Customer customer = new Customer();
    private static Logger log = LoggerFactory.getLogger(Controller.class);
    public CartController(){}

    private Handler addItemToCart = (ctx) -> {
        FoodDTO food;
        //if(ctx.req.getSession(false)!=null || ctx.pathParam("Role").equalsIgnoreCase("Guest") ){
            //for a customer that is logged in or a guest
            food = ctx.bodyAsClass(FoodDTO.class);
            //customer = new Customer();
            if(service.addItemToCart(customer, food)) {
                log.info("Successfully added " + food.foodName + " to cart.");
                ctx.status(200);
            }
            else {
                log.info("Unable to add " + food.foodName + " to cart.");
                ctx.status(400);
            }
        //}
        //else {
         //   ctx.status(401);
        //}
    };


    private Handler removeItemFromCart = (ctx) -> {
        FoodDTO food;
        //if(ctx.req.getSession(false)!=null || ctx.pathParam("Role").equalsIgnoreCase("Guest") ){
            //for a customer that is logged in or a guest
            food = ctx.bodyAsClass(FoodDTO.class);
           // if(customer == null) {
            if(customer.getCart().getCart().size() == 0) {
                log.info("Cart is empty, unable to remove from cart");
                ctx.status(400);
            }
            if(service.removeItemFromCart(customer, food)) {
                log.info("Successfully removed " +food.quantity+" of item " + food.foodName + " from cart");
                ctx.status(200);
            } else {
                ctx.status(400);
            }
        //}
        //else {
         //   ctx.status(401);
        //}
    };
    private Handler getTotalOrder = (ctx) -> {
       // if(ctx.req.getSession(false)!=null || ctx.pathParam("Role").equalsIgnoreCase("Guest") ){

//            if(customer == null) {
//                ctx.status(400);
//            }
            ctx.json(service.getTotalOrder(customer));
            ctx.status(200);
        //}
        //else {
          //  ctx.status(401);
        //}
    };

    private Handler submitOrder = (ctx) -> {
       // if(ctx.req.getSession(false)!=null || ctx.pathParam("Role").equalsIgnoreCase("Guest") ){

//            if(customer == null) {
//                ctx.status(400);
//            }
        if(customer.getCart().getCart().size() == 0 ) {
            log.info("Nothing in cart to submit");
            ctx.status(400);
        } else {
            log.info("Customer "+ customer.getOrderID()+" successfully submitted order");
            service.submitOrder(customer);
            //customer = null;
            customer = new Customer();
            ctx.status(200);
        }
        //}
        //else {
         //   ctx.status(401);
        //}
    };
    private Handler editItemInCart = (ctx) -> {
        if(ctx.req.getSession(false)!=null){
            //String name = ctx.queryParam("name");
            //service.editItemInCart(name);
            ctx.status(200);
        }
        else {
            ctx.status(401);
        }
    };

    @Override
    public void addRoutes(Javalin app) {
        //app.get("/cart", getTotalOrder, Role.CUSTOMER);
        app.get("/cart", getTotalOrder);

        //app.post("/menu/add/cart", addItemToCart, Role.CUSTOMER);
        app.post("/cart/add", addItemToCart);

        //app.delete("/menu/remove/cart", removeItemFromCart, Role.CUSTOMER);
        app.delete("/cart/remove", removeItemFromCart);

        //app.patch("/menus/edit/cart", editItemInCart, Role.CUSTOMER);
        //app.post("/submit", submitOrder, Role.CUSTOMER);
        app.post("/cart/submit", submitOrder);

        //create a post handler for submitted the cart to the DB since DTO will be used for manipulating the cart
    }
}



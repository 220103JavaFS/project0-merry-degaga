package com.revature.controllers;

import com.revature.service.CartService;
import com.revature.users.Customer;
import com.revature.users.FoodDTO;
import com.revature.users.cart.food.Food;
import io.javalin.Javalin;
import io.javalin.http.Handler;

public class CartController extends Controller {
    private CartService service = new CartService();
    private Customer customer = null;
    public CartController(){}

    private Handler addItemToCart = (ctx) -> {
        FoodDTO food;
        if(ctx.req.getSession(false)!=null || ctx.pathParam("Role").equalsIgnoreCase("Guest") ){
            //for a customer that is logged in or a guest
            food = ctx.bodyAsClass(FoodDTO.class);
            customer = new Customer();
            service.addItemToCart(customer, food);
            ctx.status(200);
        }
        else {
            ctx.status(401);
        }
    };


    private Handler removeItemFromCart = (ctx) -> {
        FoodDTO food;
        if(ctx.req.getSession(false)!=null || ctx.pathParam("Role").equalsIgnoreCase("Guest") ){
            //for a customer that is logged in or a guest
            food = ctx.bodyAsClass(FoodDTO.class);
            if(customer == null) {
                ctx.status(400);
            }
            service.removeItemFromCart(customer, food);
            ctx.status(200);
        }
        else {
            ctx.status(401);
        }
    };
    private Handler getTotalOrder = (ctx) -> {
        if(ctx.req.getSession(false)!=null || ctx.pathParam("Role").equalsIgnoreCase("Guest") ){

            if(customer == null) {
                ctx.status(400);
            }
            service.getTotalOrder(customer);
            ctx.status(200);
        }
        else {
            ctx.status(401);
        }
    };

    private Handler submitOrder = (ctx) -> {
        if(ctx.req.getSession(false)!=null || ctx.pathParam("Role").equalsIgnoreCase("Guest") ){

            if(customer == null) {
                ctx.status(400);
            }
            service.submitOrder(customer);
            customer = null;
            ctx.status(200);
        }
        else {
            ctx.status(401);
        }
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
        app.get("/cart", getTotalOrder, Role.CUSTOMER);

        app.put("/menu/add/cart", addItemToCart, Role.CUSTOMER);
        app.delete("/menu/remove/cart", removeItemFromCart, Role.CUSTOMER);
        app.patch("/menus/edit/cart", editItemInCart, Role.CUSTOMER);
        app.put("/submit", submitOrder, Role.CUSTOMER);

        //create a post handler for submitted the cart to the DB since DTO will be used for manipulating the cart
    }
}



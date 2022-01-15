package com.revature.controllers;

import com.revature.service.CartService;
import com.revature.users.cart.food.Food;
import io.javalin.Javalin;
import io.javalin.http.Handler;

public class CartController extends Controller {
    private CartService service = new CartService();
    public CartController(){}

    private Handler addItemToCart = (ctx) -> {
        if(ctx.req.getSession(false)!=null){
            String name = ctx.queryParam("name");
            Food food = ctx.bodyAsClass(Food.class);
            service.addItemToCart(food);
            ctx.status(200);
        }
        else {
            ctx.status(401);
        }
    };
    private Handler removeItemFromCart = (ctx) -> {
        if(ctx.req.getSession(false)!=null){
            String name = ctx.queryParam("name");
            service.removeItemFromCart(name);
            ctx.status(200);
        }
        else {
            ctx.status(401);
        }
    };
    private Handler getTotalOrder = (ctx) -> {
        if(ctx.req.getSession(false)!=null){
            service.getTotalOrder();
            ctx.status(200);
        }
        else {
            ctx.status(401);
        }
    };
    private Handler editItemInCart = (ctx) -> {
        if(ctx.req.getSession(false)!=null){
            String name = ctx.queryParam("name");
            service.editItemInCart(name);
            ctx.status(200);
        }
        else {
            ctx.status(401);
        }
    };

    @Override
    public void addRoutes(Javalin app) {
        app.get("/cart", getTotalOrder, Role.CUSTOMER, Role.EMPLOYEE, Role.MANAGER);

        app.put("/menu/add/cart", addItemToCart, Role.CUSTOMER, Role.EMPLOYEE, Role.MANAGER);
        app.delete("/menu/remove/cart", removeItemFromCart, Role.CUSTOMER, Role.EMPLOYEE, Role.MANAGER);
        app.patch("/menus/edit/cart", editItemInCart, Role.CUSTOMER, Role.EMPLOYEE, Role.MANAGER);
    }
}



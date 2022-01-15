package com.revature.controllers;

import com.revature.service.CartService;
import com.revature.users.cart.food.Food;
import io.javalin.Javalin;
import io.javalin.http.Handler;

public class CartController extends Controller {
    private CartService service = new CartService();
    public CartController(){}

    private Handler addItemToCart = (ctx) -> {
        Food food = ctx.bodyAsClass(Food.class);
        //service.addItemToCart(food);
    };
    private Handler removeItemFromCart = (ctx) -> {
        String name = ctx.queryParam("name");
        //service.removeItemFromCart(name);
    };
    private Handler getTotalOrder = (ctx) -> {
        //service.getTotalOrder();
    };
    private Handler editItemInCart = (ctx) -> {
        String name = ctx.queryParam("name");
        //service.editItemInCart(name)
    };

    @Override
    public void addRoutes(Javalin app) {
        app.get("/cart", getTotalOrder, Role.CUSTOMER, Role.EMPLOYEE, Role.MANAGER);

        app.put("/menu/add/cart", addItemToCart, Role.CUSTOMER, Role.EMPLOYEE, Role.MANAGER);
        app.delete("/menu/remove/cart", removeItemFromCart, Role.CUSTOMER, Role.EMPLOYEE, Role.MANAGER);
        app.patch("/menus/edit/cart", editItemInCart, Role.CUSTOMER, Role.EMPLOYEE, Role.MANAGER);
    }
}

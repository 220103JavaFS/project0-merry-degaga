package com.revature.controllers;

import com.revature.service.CartService;
import io.javalin.Javalin;
import io.javalin.http.Handler;

public class CartController extends Controller {
    private CartService service = new CartService();
    public CartController(){}

    private Handler addItemToCart = (ctx) -> {

    };
    private Handler removeItemFromCart = (ctx) -> {

    };
    private Handler getTotalOrder = (ctx) -> {

    };

    @Override
    public void addRoutes(Javalin app) {
        app.get("/cart", getTotalOrder, Role.CUSTOMER, Role.EMPLOYEE, Role.MANAGER);

        app.put("/menu/add/cart", addItemToCart, Role.CUSTOMER, Role.EMPLOYEE, Role.MANAGER);
        app.put("/menus/remove", removeItemFromCart, Role.CUSTOMER, Role.EMPLOYEE, Role.MANAGER);
    }
}

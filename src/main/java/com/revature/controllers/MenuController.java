package com.revature.controllers;
import com.revature.service.MenuService;
import io.javalin.Javalin;
import io.javalin.http.Handler;



public class MenuController extends Controller {

    private MenuService service = new MenuService();
    public MenuController(){}

    private Handler getMenu = (ctx) -> {
        ctx.status(200);
    };

    private Handler addMenuItem = (ctx) -> {

    };
    private Handler removeMenuItem = (ctx) -> {

    };



    @Override
    public void addRoutes(Javalin app) {
        app.get("/menu", getMenu, Role.MANAGER, Role.EMPLOYEE, Role.CUSTOMER);

        app.put("/menu/add", addMenuItem, Role.MANAGER);
        app.put("/menu/remove", removeMenuItem, Role.MANAGER);
    }



}




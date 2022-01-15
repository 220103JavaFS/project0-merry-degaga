package com.revature.controllers;
import com.revature.service.MenuService;
import com.revature.users.cart.food.Food;
import io.javalin.Javalin;
import io.javalin.http.Handler;

public class MenuController extends Controller {

    private MenuService service = new MenuService();
    public MenuController(){}

    private Handler getMenu = (ctx) -> {
        if(ctx.req.getSession(false)!=null){
            service.getMenu();
            ctx.status(200);
        }
        else {
            ctx.status(401);
        }
    };

    private Handler addMenuItem = (ctx) -> {
        if(ctx.req.getSession(false)!=null){
            Food food = ctx.bodyAsClass(Food.class);
            service.addMenuItem(food);
            ctx.status(200);
        }
        else {
            ctx.status(401);
        }
    };
    private Handler removeMenuItem = (ctx) -> {
        if(ctx.req.getSession(false)!=null){
            String name = ctx.queryParam("name");
            service.removeMenuItem(name);
            ctx.status(200);
        }
        else {
            ctx.status(401);
        }
    };

    private Handler editMenuItem = (ctx) -> {
        if(ctx.req.getSession(false)!=null){
            String name = ctx.queryParam("name");
            service.editMenuItem(name);
            ctx.status(200);
        }
        else {
            ctx.status(401);
        }
    };



    @Override
    public void addRoutes(Javalin app) {
        app.get("/menu", getMenu, Role.MANAGER, Role.EMPLOYEE, Role.CUSTOMER);

        app.post("/menu/add", addMenuItem, Role.MANAGER);
        app.patch("/menu/edit", editMenuItem, Role.MANAGER);
        app.delete("/menu/remove", removeMenuItem, Role.MANAGER);

    }



}




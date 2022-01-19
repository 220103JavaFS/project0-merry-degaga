package com.revature.controllers;
import com.revature.service.MenuService;
import com.revature.users.Inventory;
import com.revature.users.cart.food.Food;
import io.javalin.Javalin;
import io.javalin.http.Handler;

public class MenuController extends Controller {

    private MenuService service = new MenuService();
    public MenuController(){}

    private Handler getMenu = (ctx) -> {

            ctx.json(service.getMenu());
            ctx.status(200);

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

    private Handler addInventory = (ctx) -> {
        if(ctx.req.getSession(false)!=null){
            Inventory item = ctx.bodyAsClass(Inventory.class);
            if(service.addInventory(item)) {
                ctx.status(200);
            }else {
                ctx.status(400);
            }
        }
        else {
            ctx.status(401);
        }
    };




    @Override
    public void addRoutes(Javalin app) {
        app.get("/menu", getMenu);
        app.post("/menu/add", addMenuItem, Role.MANAGER);
        app.post("/inventory/add", addInventory, Role.MANAGER);

       //app.patch("/menu/edit", editMenuItem, Role.MANAGER);

        //TRY
        //app.delete("/menu/remove", removeMenuItem, Role.MANAGER);

    }



}




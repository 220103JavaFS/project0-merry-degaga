package com.revature.controllers;
import com.revature.service.MenuService;
import com.revature.users.Inventory;
import com.revature.users.cart.food.Food;
import io.javalin.Javalin;
import io.javalin.http.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MenuController extends Controller {
    private static Logger log = LoggerFactory.getLogger(Controller.class);

    private MenuService service = new MenuService();
    public MenuController(){}

    private Handler getMenu = (ctx) -> {
            log.info("Getting the menu...");
            ctx.json(service.getMenu());
            ctx.status(200);

    };

    private Handler addMenuItem = (ctx) -> {
        if(ctx.req.getSession(false)!=null){
            log.info("Customer is attempting to add food to cart...");
            Food food = ctx.bodyAsClass(Food.class);
            if(service.addMenuItem(food)) {
                log.info("Added " + food.getFoodName() + " successfully");
                ctx.status(200);
            } else {
                log.info("Unable to add " + food.getFoodName() + " at this time");
                ctx.status(400);
            }
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
            log.info("Attempting to add " + item.ingredientName + " to inventory...");
            if(service.addInventory(item)) {
                log.info("Success adding to the inventory");
                ctx.status(200);
            }else {
                log.info("Unable to add " + item.ingredientName + " to inventory...");
                ctx.status(400);
            }
        }
        else {
            ctx.status(401);
        }
    };

    private Handler getInventory = (ctx) -> {
        log.info("Attempting to get inventory...");
        if(ctx.req.getSession(false)!=null) {
            log.info("Getting the inventory...");
            ctx.json(service.getInventory());
            ctx.status(200);
        } else {
            log.info("Not authorized to access inventory");
            ctx.status(401);
        }
    };




    @Override
    public void addRoutes(Javalin app) {
        app.get("/menu", getMenu);
        app.post("/menu/add", addMenuItem, Role.MANAGER);
        app.post("/inventory/add", addInventory, Role.MANAGER);
        app.get("/inventory", getInventory, Role.MANAGER);

       //app.patch("/menu/edit", editMenuItem, Role.MANAGER);

        //TRY
        //app.delete("/menu/remove", removeMenuItem, Role.MANAGER);

    }



}




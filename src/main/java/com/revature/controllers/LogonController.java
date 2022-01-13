package com.revature.controllers;

import com.revature.service.LogonService;
import io.javalin.Javalin;
import io.javalin.http.Handler;

public class LogonController extends Controller implements Roles {

    private LogonService service = new LogonService();


    private Handler login = (ctx) -> {
        //call getUserRole, if Role is in Role then do request
        //System.out.println(service.login("Merry","password", false));
        ctx.html("success, login");
    };

    private Handler logout = (ctx) -> {
        ctx.html("success logout");
    };

    @Override
    public void addRoutes(Javalin app) {
        app.post("/login", login, Role.MANAGER, Role.CUSTOMER, Role.EMPLOYEE, Role.ALL);
        app.post("/logout", logout, Role.MANAGER, Role.CUSTOMER, Role.EMPLOYEE, Role.ALL);
    }
}

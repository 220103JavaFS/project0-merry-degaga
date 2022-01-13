package com.revature.controllers;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class LogonController extends Controller {

    private Handler login = (ctx) -> {
        //call getUserRole, if Role is in Role then do request
        ctx.html("success, login");
    };

    private Handler logout = (ctx) -> {
        ctx.html("success logout");
    };

    @Override
    public void addRoutes(Javalin app) {
        app.post("/login", login);
        app.post("/logout", logout);
    }
}

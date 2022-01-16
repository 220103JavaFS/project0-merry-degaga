package com.revature.controllers;

import com.revature.service.RegisterService;
import com.revature.users.User;
import io.javalin.Javalin;
import io.javalin.http.Handler;

public class RegisterController extends Controller{

    private RegisterService service = new RegisterService();

    public RegisterController(){};

    private Handler register = (ctx) -> {
        //manager or employee that is logged in or not logged in can register.
            User user = ctx.bodyAsClass(User.class);
            if(service.register(user)) {
                ctx.status(200);
            }else {
                ctx.status(400);
            }
    };

    @Override
    public void addRoutes(Javalin app) {
        app.post("/register", register, Role.MANAGER, Role.EMPLOYEE);
    }
}

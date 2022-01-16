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
        if(ctx.req.getSession(false)!=null){ //user is logged in

        }else { //if user not logged in
            User user = ctx.bodyAsClass(User.class);
            System.out.println(user.toString());
            //service.register(user);
            ctx.status(200);
        }
    };

    @Override
    public void addRoutes(Javalin app) {
        app.post("/register", register);
    }
}

package com.revature.controllers;

import com.revature.service.RegisterService;
import com.revature.users.User;
import io.javalin.Javalin;
import io.javalin.http.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//handles user registration
public class RegisterController extends Controller{
    private static Logger log = LoggerFactory.getLogger(Controller.class);

    private RegisterService service = new RegisterService();

    public RegisterController(){};

    private Handler register = (ctx) -> {

        //manager or employee that is logged in or not logged in can register.
            User user = ctx.bodyAsClass(User.class);
            log.info(user.getFirstname() + " is try to register...");
            if(service.register(user)) {
                log.info("Registration is successful!");
                ctx.status(200);
            }else {
                log.info("Registration failed.");
                ctx.status(400);
            }
    };

    @Override
    public void addRoutes(Javalin app) {
        app.post("/register", register, Role.MANAGER, Role.EMPLOYEE);
    }
}

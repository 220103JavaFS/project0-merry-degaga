package com.revature.controllers;

import com.revature.exceptions.MyException;
import com.revature.service.LogonService;
import com.revature.users.UserDTO;
import io.javalin.Javalin;
import io.javalin.http.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogonController extends Controller {

    private LogonService service = new LogonService();
    private static Logger log = LoggerFactory.getLogger(Controller.class);
    public LogonController(){}


//    private Handler login = (ctx) -> {
//        UserDTO user = ctx.bodyAsClass(UserDTO.class);
//        String role = service.login(user.username, user.password);
//        if(role != null){
//            ctx.req.getSession().setAttribute("Role", role);
//            ctx.status(200);
//        }
//        else {
//            ctx.req.getSession().invalidate();
//            ctx.status(401);
//        }
//    };

    private Handler login = (ctx) -> {

        UserDTO user = ctx.bodyAsClass(UserDTO.class);
        log.info(user.username + " is trying to log in...");
        String role = service.login(user.username, user.password);
        if(role != null){
            log.info("Login is successful!");
            ctx.req.getSession().setAttribute("Role", role);
            ctx.status(200);
        }
        else {
            log.info("Invalid credentials, unable to login.");
            ctx.req.getSession().invalidate();
            ctx.status(401);
        }
    };


    private Handler logout = (ctx) -> {
        if(ctx.req.getSession(false) == null) {
            ctx.status(400); //cannot log out if you never logged in the first place
        } else {
            ctx.req.getSession().invalidate();
            ctx.status(200);
        }
    };

    @Override
    public void addRoutes(Javalin app) {
        app.post("/login", login);
        app.post("/logout", logout);
    }
}

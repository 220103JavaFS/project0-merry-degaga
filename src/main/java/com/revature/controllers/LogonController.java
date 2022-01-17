package com.revature.controllers;

import com.revature.exceptions.MyException;
import com.revature.service.LogonService;
import com.revature.users.UserDTO;
import io.javalin.Javalin;
import io.javalin.http.Handler;

public class LogonController extends Controller {

    private LogonService service = new LogonService();
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
        String role = service.login(user.username, user.password);
        if(role != null){
            ctx.req.getSession().setAttribute("Role", role);
            ctx.status(200);
        }
        else {
            ctx.req.getSession().invalidate();
            ctx.status(401);
        }
    };


    private Handler logout = (ctx) -> {
            ctx.req.getSession().invalidate();
            ctx.status(200);
    };

    @Override
    public void addRoutes(Javalin app) {
        app.post("/login", login);
        app.post("/logout", logout);
    }
}

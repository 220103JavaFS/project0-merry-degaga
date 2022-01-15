package com.revature.controllers;

import com.revature.exceptions.MyException;
import com.revature.service.LogonService;
import com.revature.users.UserDTO;
import io.javalin.Javalin;
import io.javalin.http.Handler;

public class LogonController extends Controller {

    private LogonService service = new LogonService();
    public LogonController(){}


    private Handler login = (ctx) -> {
        UserDTO user = ctx.bodyAsClass(UserDTO.class);
        try {
            if(service.login(user.username, user.password)){
                ctx.req.getSession().setAttribute("Role", queryRole(user.username));
                System.out.println("session: " + (String) ctx.req.getSession().getAttribute("Role"));
                ctx.status(200);
            }
            else {
                ctx.req.getSession().invalidate();
                ctx.status(401);
            }
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    };

    private String queryRole(String username) {
        return service.getUserRole(username);
    }

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

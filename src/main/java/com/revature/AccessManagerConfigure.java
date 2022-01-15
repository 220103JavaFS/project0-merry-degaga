package com.revature;
import io.javalin.core.security.AccessManager;
import io.javalin.core.security.RouteRole;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;
import java.util.Set;

public class AccessManagerConfigure implements AccessManager {

    public AccessManagerConfigure(){}

    @Override
    public void manage(@NotNull Handler handler, @NotNull Context ctx, @NotNull Set<RouteRole> routeRoles) throws Exception {

        if(routeRoles.size()==0) { //javalin documentation states if no routeRoles provided in addpath for a verb,
            // then no routeRoles would be required - the path would not go through AccessManager. Documenation must be
            // out of date because this did not work as stated so had to put this condition in.
            handler.handle(ctx);
            return;
        }
        String role = getUserRole(ctx);
        //can seperate out role == null for when user not logged in and so cannot access session
        if( role != null && isInRoles(role, routeRoles)) {
            handler.handle(ctx);
        } else {
            ctx.status(401).result("Unauthorized Access");
        }
    }

    private boolean isInRoles(String role, Set<RouteRole> routeRoles) {
        for(RouteRole r: routeRoles) {
            if(r.toString().equalsIgnoreCase(role)) return true;
        }
        return false;
    }

    private String getUserRole(Context ctx) {
        return (String) ctx.req.getSession().getAttribute("Role");
    }
}

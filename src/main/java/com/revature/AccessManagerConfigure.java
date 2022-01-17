package com.revature;
import com.revature.users.User;
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

        if(routeRoles.size()==0) {
            handler.handle(ctx);
            return;
        }
        String role = getUserRole(ctx);
        //System.out.println("role is : " + role);
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
        if(ctx.req.getSession(false)==null) { //need to have this when table not filled with any info - a manager /
            // employee should be able to register themselves
           try {
               User user = ctx.bodyAsClass(User.class);
               return user.getRolez();
           } catch(Exception e) {
               //needed when a user is trying to access other endpoints and not logged in.
               return null;
           }
        }
        return (String) ctx.req.getSession().getAttribute("Role");
    }
}

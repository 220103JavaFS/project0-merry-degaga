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
        //grab ctx ROLE header
        String role = getUserRole(ctx);
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
        return ctx.header("Role");
    }
}

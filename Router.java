package NetworkIP;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Router {
    private final List<Route> routingTable;

    public Router() {
        routingTable = new ArrayList<>();
    }

    public void initializeDefaultRoutes() {
        try {
            addRoute(new Route("192.168.1.0/24", "直接连接"));
            addRoute(new Route("192.168.1.128/25", "接口1"));
            addRoute(new Route("192.168.0.0/16", "路由器2"));
            addRoute(new Route("10.0.0.0/8", "路由器3"));
            addRoute(new Route("0.0.0.0/0", "默认网关"));
        } catch (IllegalArgumentException e) {
            System.err.println("初始化路由表错误: " + e.getMessage());
        }
    }

    public void addRoute(Route route) {
        routingTable.add(route);
        routingTable.sort(Comparator.comparingInt(Route::getPrefixLength).reversed());
    }

    public Route findBestRoute(IPAddress destIP) {
        for (Route route : routingTable) {
            if (route.matches(destIP)) {
                return route;
            }
        }
        return null;
    }

    public List<Route> getRoutingTable() {
        return routingTable;
    }
}
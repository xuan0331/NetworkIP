package NetworkIP;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class RouterController {
    private final Router router = new Router();
    private ResultPanel resultPanel;

    public void setResultPanel(ResultPanel resultPanel) {
        this.resultPanel = resultPanel;
    }

    public void initializeRoutes() {
        router.initializeDefaultRoutes();
        updateRouteTable();
    }

    public void handleSearch(String ipAddress) {
        if (ipAddress.isEmpty()) {
            showError("请输入IP地址");
            return;
        }

        try {
            IPAddress destIP = new IPAddress(ipAddress);
            Route bestRoute = router.findBestRoute(destIP);

            if (resultPanel != null) {
                resultPanel.displayRouteResult(bestRoute);
            } else {
                showError("ResultPanel 未初始化");
            }
        } catch (IllegalArgumentException ex) {
            showError("错误: " + ex.getMessage());
        }
    }

   public void updateRouteTable() {
        if (resultPanel != null) {
            resultPanel.updateRouteTable(router.getRoutingTable());
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(
                null,
                message,
                "输入错误",
                JOptionPane.ERROR_MESSAGE
        );
    }
}

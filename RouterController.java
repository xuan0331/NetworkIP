package NetworkIP;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class RouterController {
    private final Router router = new Router();
    private ResultPanel resultPanel;

    // 设置ResultPanel
    public void setResultPanel(ResultPanel resultPanel) {
        this.resultPanel = resultPanel;
    }

    // 初始化路由表
    public void initializeRoutes() {
        router.initializeDefaultRoutes();
        updateRouteTable();
    }

    // 处理搜索功能
    public void handleSearch(String ipAddress) {
        if (ipAddress.isEmpty()) {
            showError("请输入IP地址");
            return;
        }

        try {
            // 尝试将输入的IP地址转换为IPAddress对象
            IPAddress destIP = new IPAddress(ipAddress);
            // 查找最佳路由
            Route bestRoute = router.findBestRoute(destIP);

            // 如果resultPanel已经初始化，显示路由结果
            if (resultPanel != null) {
                resultPanel.displayRouteResult(bestRoute);
            } else {
                // 如果resultPanel为空，显示错误信息
                showError("ResultPanel 未初始化");
            }
        } catch (IllegalArgumentException ex) {
            showError("错误: " + ex.getMessage());
        }
    }

    // 更新路由表
    public void updateRouteTable() {
        if (resultPanel != null) {
            resultPanel.updateRouteTable(router.getRoutingTable());
        }
    }

    // 显示错误信息
    private void showError(String message) {
        JOptionPane.showMessageDialog(
                null,
                message,
                "输入错误",
                JOptionPane.ERROR_MESSAGE
        );
    }
}

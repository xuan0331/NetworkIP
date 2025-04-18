package NetworkIP;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.nio.charset.StandardCharsets;
import javax.swing.JTextArea;

public class ResultPanel extends JPanel {
    private final JTextArea resultArea;
    private final JTable routeTable;

    public ResultPanel(RouterController controller) {
        super(new BorderLayout(10, 10));
        controller.setResultPanel(this);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Consolas", Font.PLAIN, 14));
        JScrollPane textScroll = new JScrollPane(resultArea);

        String[] columnNames = {"网络地址", "子网掩码", "下一跳", "前缀长度"};
        routeTable = new JTable(new Object[0][4], columnNames);
        routeTable.setRowHeight(30);
        routeTable.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        JScrollPane tableScroll = new JScrollPane(routeTable);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, textScroll, tableScroll);
        splitPane.setResizeWeight(0.3);
        add(splitPane, BorderLayout.CENTER);
    }

    public void displayRouteResult(Route bestRoute) {
        resultArea.setFont(new Font("Microsoft YaHei", Font.PLAIN, 14));
        if (bestRoute != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("--------------- 匹配结果 ---------------\n");
            sb.append(String.format("| %-8s : %-30s |\n", "目标网络", bestRoute.getNetwork()));
            sb.append(String.format("| %-8s : %-30s |\n", "下一跳", bestRoute.getNextHop()));
            sb.append(String.format("| %-8s : %-30s |\n", "子网掩码", bestRoute.getSubnetMask()));
            sb.append(String.format("| %-8s : %-30d |\n", "前缀长度", bestRoute.getPrefixLength()));
            sb.append("-----------------------------------------");
            resultArea.setText(sb.toString());
        } else {
            resultArea.setText("没有匹配的路由，分组将被丢弃");
        }
    }

    public void updateRouteTable(List<Route> routes) {
        Object[][] data = new Object[routes.size()][4];
        for (int i = 0; i < routes.size(); i++) {
            Route route = routes.get(i);
            data[i][0] = route.getNetwork();
            data[i][1] = route.getSubnetMask();
            data[i][2] = route.getNextHop();
            data[i][3] = route.getPrefixLength();
        }
        routeTable.setModel(new DefaultTableModel(data, new String[]{"网络地址", "子网掩码", "下一跳", "前缀长度"}));
    }
}
package NetworkIP;

import javax.swing.*;
import java.awt.*;

public class RouterFrame extends JFrame {
    private final RouterController controller;

    public RouterFrame() {
        super("IP路由模拟器");
        setIconImage(new ImageIcon("router_icon.png").getImage());
        setBounds(100, 100, 800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout(10, 10));

        controller = new RouterController();

        InputPanel inputPanel = new InputPanel(controller);
        ResultPanel resultPanel = new ResultPanel(controller);

        getContentPane().add(inputPanel, BorderLayout.NORTH);
        getContentPane().add(resultPanel, BorderLayout.CENTER);

        JLabel statusBar = new JLabel(" 就绪");
        getContentPane().add(statusBar, BorderLayout.SOUTH);

        controller.initializeRoutes();
    }
}
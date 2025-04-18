package NetworkIP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class InputPanel extends JPanel {
    private final JTextField ipField;
    private final RouterController controller;

    public InputPanel(RouterController controller) {
        super(new FlowLayout(FlowLayout.LEFT, 10, 10));
        this.controller = controller;

        ipField = new JTextField(20);
        ipField.setFont(new Font("微软雅黑", Font.BOLD, 14));

        JButton btnSearch = createStyledButton("路由查询", "#4CAF50");
        JButton btnClear = createStyledButton("清空结果", "#C62828");

        btnSearch.addActionListener(this::handleSearch);
        btnClear.addActionListener(e -> {
            ipField.setText("");
            controller.setResultPanel(null);
        });
        add(new JLabel("目标IP地址:"));
        add(ipField);
        add(btnSearch);
        add(btnClear);
    }

    private void handleSearch(ActionEvent e) {
        controller.handleSearch(ipField.getText().trim());
    }

    private JButton createStyledButton(String text, String colorHex) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                if (!isOpaque()) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(getBackground());
                    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                    g2.dispose();
                }
                super.paintComponent(g);
            }
        };

        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setBackground(Color.decode(colorHex));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("微软雅黑", Font.BOLD, 14));
        button.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(button.getBackground().darker());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.decode(colorHex));
            }
        });

        return button;
    }
}
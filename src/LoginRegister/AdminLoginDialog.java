package LoginRegister;



import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Admin.AdminUI;
import Database.Connect;

public class AdminLoginDialog extends JDialog {

    private JTextField adminIdField;
    private JPasswordField passwordField;

    public AdminLoginDialog(final JFrame parent) {
        super(parent, "管理员登录", true);
        setSize(400, 200);
        setLocationRelativeTo(parent);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel adminIdLabel = new JLabel("ID:");
        adminIdField = new JTextField(20);
        JLabel passwordLabel = new JLabel("密码:");
        passwordField = new JPasswordField(20);

        JButton loginButton = new JButton("登录");
        JButton cancelButton = new JButton("取消");

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(adminIdLabel, gbc);
        gbc.gridx = 1;
        add(adminIdField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(passwordLabel, gbc);
        gbc.gridx = 1;
        add(passwordField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(loginButton, gbc);
        gbc.gridx = 1;
        add(cancelButton, gbc);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String adminId = adminIdField.getText();
                String password = new String(passwordField.getPassword());

                boolean loginSuccess = false;
                try (Connection conn = new Connect().getConnection()) {
                    String sql = "SELECT * FROM Admin WHERE AdminID = ? AND Password = ?";
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, adminId);
                    pstmt.setString(2, password);
                    ResultSet rs = pstmt.executeQuery();
                    if (rs.next()) {
                        loginSuccess = true;
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                if (loginSuccess) {
                    JOptionPane.showMessageDialog(AdminLoginDialog.this, "Admin Login Successful!");
                    dispose();
                    new AdminUI().setVisible(true);
                    parent.dispose();
                } else {
                    JOptionPane.showMessageDialog(AdminLoginDialog.this, "Invalid Admin ID or Password");
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}

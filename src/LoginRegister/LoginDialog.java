package LoginRegister;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;

import User.UserUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginDialog extends JDialog {

    private JTextField idField;
    private JPasswordField passwordField;

    public LoginDialog(final JFrame parent) {
        super(parent, "Login", true);
        setSize(400, 200);
        setLocationRelativeTo(parent);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel idLabel = new JLabel("身份证:");
        idField = new JTextField(20);
        JLabel passwordLabel = new JLabel("密码:");
        passwordField = new JPasswordField(20);

        JButton loginButton = new JButton("登录");
        JButton cancelButton = new JButton("取消");

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(idLabel, gbc);
        gbc.gridx = 1;
        add(idField, gbc);
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
                String idCard = idField.getText();
                String password = new String(passwordField.getPassword());

                boolean loginSuccess = false;
                try (BufferedReader reader = new BufferedReader(new FileReader("password.txt"))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(":");
                        if (parts[0].equals(idCard) && parts[1].equals(password)) {
                            loginSuccess = true;
                            LoginRegisterUI.userID = idCard;
                            break;
                        }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                if (loginSuccess) {
                    JOptionPane.showMessageDialog(LoginDialog.this, "Login Successful!");
                    dispose();
                    new UserUI().setVisible(true);
                    parent.dispose(); 
                } else {
                    JOptionPane.showMessageDialog(LoginDialog.this, "Invalid ID Card or Password");
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
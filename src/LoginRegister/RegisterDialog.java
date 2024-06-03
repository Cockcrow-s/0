package LoginRegister;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Database.Connect;

public class RegisterDialog extends JDialog {

    private JTextField idField;
    private JTextField nameField;
    private JTextField contactField;
    private JPasswordField passwordField;

    public RegisterDialog(JFrame parent) {
        super(parent, "注册", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel idLabel = new JLabel("身份证:");
        idField = new JTextField(20);
        JLabel nameLabel = new JLabel("姓名:");
        nameField = new JTextField(20);
        JLabel contactLabel = new JLabel("联系方式:");
        contactField = new JTextField(20);
        JLabel passwordLabel = new JLabel("密码:");
        passwordField = new JPasswordField(20);

        JButton registerButton = new JButton("注册");
        JButton cancelButton = new JButton("取消");

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(idLabel, gbc);
        gbc.gridx = 1;
        add(idField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(nameLabel, gbc);
        gbc.gridx = 1;
        add(nameField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(contactLabel, gbc);
        gbc.gridx = 1;
        add(contactField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(passwordLabel, gbc);
        gbc.gridx = 1;
        add(passwordField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(registerButton, gbc);
        gbc.gridx = 1;
        add(cancelButton, gbc);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idCard = idField.getText();
                String name = nameField.getText();
                String contactInfo = contactField.getText();
                String password = new String(passwordField.getPassword());

 
                try (Connection conn = new Connect().getConnection()) {
                    String sql = "INSERT INTO Visitor (IDCard, Name, ContactInfo) VALUES (?, ?, ?)";
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, idCard);
                    pstmt.setString(2, name);
                    pstmt.setString(3, contactInfo);
                    pstmt.executeUpdate();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }


                try (BufferedWriter writer = new BufferedWriter(new FileWriter("password.txt", true))) {
                    writer.write(idCard + ":" + password);
                    writer.newLine();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                JOptionPane.showMessageDialog(RegisterDialog.this, "Registration Successful!");
                dispose();
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

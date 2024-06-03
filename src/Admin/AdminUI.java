package Admin;

import javax.swing.*;

import LoginRegister.AdminLoginDialog;
import LoginRegister.LoginRegisterUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminUI extends JFrame {

    public AdminUI() {
        setTitle("管理员中心");
        setSize(1800, 1200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        ImageIcon backgroundImageIcon = new ImageIcon("./image/1.jpg");
        Image img = backgroundImageIcon.getImage();
        Image scaledImg = img.getScaledInstance(1800, 1200, Image.SCALE_SMOOTH);
        backgroundImageIcon = new ImageIcon(scaledImg);


        JLabel backgroundLabel = new JLabel(backgroundImageIcon);
        backgroundLabel.setLayout(new BorderLayout());


        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false);


        JButton manageAttractionsButton = new JButton("管理景点信息");
        JButton manageCompaniesButton = new JButton("管理公司信息");
        JButton manageEmployeesButton = new JButton("管理职员信息");
        JButton manageTicketsButton = new JButton("管理票务信息");
        JButton manageCityButton = new JButton("管理城市信息");



        Font buttonFont = new Font("SimSun", Font.BOLD, 20);
        manageAttractionsButton.setFont(buttonFont);
        manageCompaniesButton.setFont(buttonFont);
        manageEmployeesButton.setFont(buttonFont);
        manageTicketsButton.setFont(buttonFont);
        manageCityButton.setFont(buttonFont);
        manageAttractionsButton.setPreferredSize(new Dimension(250,130));
        manageCompaniesButton.setPreferredSize(new Dimension(250,130));
        manageEmployeesButton.setPreferredSize(new Dimension(250,130));
        manageTicketsButton.setPreferredSize(new Dimension(250,130));
        manageCityButton.setPreferredSize(new Dimension(250,130));


        manageAttractionsButton.setForeground(Color.RED);
        manageCompaniesButton.setForeground(Color.RED);
        manageEmployeesButton.setForeground(Color.RED);
        manageTicketsButton.setForeground(Color.RED);
        manageCityButton.setForeground(Color.RED);


        manageAttractionsButton.setContentAreaFilled(false);
        manageCompaniesButton.setContentAreaFilled(false);
        manageEmployeesButton.setContentAreaFilled(false);
        manageTicketsButton.setContentAreaFilled(false);
        manageCityButton.setContentAreaFilled(false);



        buttonPanel.add(manageAttractionsButton);
        buttonPanel.add(manageCompaniesButton);
        buttonPanel.add(manageEmployeesButton);
        buttonPanel.add(manageTicketsButton);
        buttonPanel.add(manageCityButton);



        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));


        backgroundLabel.add(buttonPanel, BorderLayout.WEST);


        setContentPane(backgroundLabel);


        manageAttractionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManageAttractionsUI().setVisible(true);
            }
        });

        manageCompaniesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManageCompaniesUI().setVisible(true);
            }
        });

        manageEmployeesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManageEmployeesUI().setVisible(true);
            }
        });

        manageTicketsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManageTicketsUI().setVisible(true);
            }
        });
        manageCityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManageCitiesUI().setVisible(true);
            }
        });


    }
}
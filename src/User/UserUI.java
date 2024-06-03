package User;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserUI extends JFrame {

    public UserUI() {
        setTitle("�û�����");
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


        JButton queryCityButton = new JButton("�鿴����");
        JButton queryAttractionsButton = new JButton("��ѯ����");
        JButton viewCompaniesButton = new JButton("�鿴��˾");
        JButton viewEmployeesButton = new JButton("�鿴ְԱ");
        JButton bookAttractionButton = new JButton("ԤԼ");
        JButton purchaseTicketButton = new JButton("��Ʊ");
        JButton userProfileButton = new JButton("�û�����");


        Font buttonFont = new Font("SimSun", Font.BOLD, 20);
        queryCityButton.setFont(buttonFont);
        queryAttractionsButton.setFont(buttonFont);
        viewCompaniesButton.setFont(buttonFont);
        viewEmployeesButton.setFont(buttonFont);
        bookAttractionButton.setFont(buttonFont);
        purchaseTicketButton.setFont(buttonFont);
        userProfileButton.setFont(buttonFont);
        
        queryCityButton.setPreferredSize(new Dimension(250,130));
        queryAttractionsButton.setPreferredSize(new Dimension(250,130));
        viewCompaniesButton.setPreferredSize(new Dimension(250,130));
        viewEmployeesButton.setPreferredSize(new Dimension(250,130));
        bookAttractionButton.setPreferredSize(new Dimension(250,130));
        purchaseTicketButton.setPreferredSize(new Dimension(250,130));
        userProfileButton.setPreferredSize(new Dimension(250,130));

        queryCityButton.setForeground(Color.RED);
        queryAttractionsButton.setForeground(Color.RED);
        viewCompaniesButton.setForeground(Color.RED);
        viewEmployeesButton.setForeground(Color.RED);
        bookAttractionButton.setForeground(Color.RED);
        purchaseTicketButton.setForeground(Color.RED);
        userProfileButton.setForeground(Color.RED);
        
        queryCityButton.setContentAreaFilled(false);
        queryAttractionsButton.setContentAreaFilled(false);
        viewCompaniesButton.setContentAreaFilled(false);
        viewEmployeesButton.setContentAreaFilled(false);
        bookAttractionButton.setContentAreaFilled(false);
        purchaseTicketButton.setContentAreaFilled(false);
        userProfileButton.setContentAreaFilled(false);
        

        buttonPanel.add(queryCityButton);
        buttonPanel.add(queryAttractionsButton);
        buttonPanel.add(viewCompaniesButton);
        buttonPanel.add(viewEmployeesButton);
        buttonPanel.add(bookAttractionButton);
        buttonPanel.add(purchaseTicketButton);
        buttonPanel.add(userProfileButton);


        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));


        backgroundLabel.add(buttonPanel, BorderLayout.WEST);


        setContentPane(backgroundLabel);
        queryCityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new QueryCityUI().setVisible(true);
            }
        });

        queryAttractionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new QueryAttractionsUI().setVisible(true);
            }
        });
        viewCompaniesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewCompaniesUI().setVisible(true);
            }
        });

        viewEmployeesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewEmployeesUI().setVisible(true);
            }
        });
        purchaseTicketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PurchaseAttractionUI().setVisible(true);
            }
        });
        bookAttractionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BookAttractionUI().setVisible(true);
            }
        });
        userProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UserProfileUI().setVisible(true);
            }
        });
        
    }
}

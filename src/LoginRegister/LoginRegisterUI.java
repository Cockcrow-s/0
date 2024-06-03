package LoginRegister;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginRegisterUI extends JFrame {

    private ImageIcon backgroundImageIcon;
    public static String userID;  
    public LoginRegisterUI() {
        setTitle("黔程无忧");
        setSize(1800, 1200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        backgroundImageIcon = new ImageIcon("./image/1.jpg");
        Image img = backgroundImageIcon.getImage();
        Image scaledImg = img.getScaledInstance(1600, 1500, Image.SCALE_SMOOTH);
        backgroundImageIcon = new ImageIcon(scaledImg);


        BackgroundPanel backgroundPanel = new BackgroundPanel(backgroundImageIcon.getImage());
        backgroundPanel.setLayout(new GridBagLayout());
        backgroundPanel.setOpaque(true);

        setContentPane(backgroundPanel);


        JLabel label = new JLabel("走遍神州大地，醉美多彩贵州");
        label.setFont(new Font("Serif", Font.BOLD, 36));
        label.setForeground(Color.RED);
        GridBagConstraints gbcLabel = new GridBagConstraints();
        gbcLabel.gridx = 0;
        gbcLabel.gridy = 0;
        gbcLabel.insets = new Insets(10, 10, 50, 10);
        gbcLabel.anchor = GridBagConstraints.CENTER;
        backgroundPanel.add(label, gbcLabel);


        JPanel panel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.1f)); // Set transparency
                g2d.setColor(getBackground());
                g2d.fillRect(0, 0, getWidth(), getHeight());
                g2d.dispose();
            }
        };
        panel.setOpaque(false);


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JButton loginButton = new JButton("登录");
        loginButton.setPreferredSize(new Dimension(150, 50));
        JButton registerButton = new JButton("注册");
        registerButton.setPreferredSize(new Dimension(150, 50));
        JButton adminLoginButton = new JButton("管理员登录");
        adminLoginButton.setPreferredSize(new Dimension(150, 50));

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(loginButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(registerButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(adminLoginButton, gbc);

        GridBagConstraints gbcPanel = new GridBagConstraints();
        gbcPanel.gridx = 0;
        gbcPanel.gridy = 1;
        gbcPanel.insets = new Insets(50, 10, 10, 10);
        gbcPanel.anchor = GridBagConstraints.CENTER;
        backgroundPanel.add(panel, gbcPanel);


        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginDialog(LoginRegisterUI.this).setVisible(true);
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterDialog(LoginRegisterUI.this).setVisible(true);
            }
        });

        adminLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminLoginDialog(LoginRegisterUI.this).setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginRegisterUI().setVisible(true);
            }
        });
    }


    class BackgroundPanel extends JPanel {
        private Image image;

        public BackgroundPanel(Image image) {
            this.image = image;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            g2d.dispose();
        }
    }
}

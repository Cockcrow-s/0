package User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import Database.Connect;

public class QueryCityUI extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;

    public QueryCityUI() {
        setTitle("Query City");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);


        tableModel = new DefaultTableModel();
        tableModel.addColumn("城市");


        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);


        loadData();


        add(scrollPane, BorderLayout.CENTER);


        JPanel buttonPanel = new JPanel();
        JButton viewImageButton = new JButton("查看详情");
        buttonPanel.add(viewImageButton);
        add(buttonPanel, BorderLayout.SOUTH);


        viewImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(QueryCityUI.this, "Please select a city to view its description and image.");
                } else {
                    String cityName = (String) table.getValueAt(selectedRow, 0);
                    showCityDescriptionAndImage(cityName);
                }
            }
        });
    }

    private void loadData() {
        try (Connection conn = new Connect().getConnection()) {
            String sql = "SELECT Name FROM City";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(rs.getString("Name"));
                tableModel.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showCityDescriptionAndImage(String cityName) {
        try (Connection conn = new Connect().getConnection()) {
            String sql = "SELECT Description FROM City WHERE Name = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cityName);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String description = rs.getString("Description");

  
                JDialog dialog = new JDialog(this, "Description and Image of " + cityName, true);
                dialog.setSize(1200, 900);
                dialog.setLayout(new BorderLayout());

                JTextArea textArea = new JTextArea(description);
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                textArea.setEditable(false);
                textArea.setPreferredSize(new Dimension(1200,80));
                JScrollPane textScrollPane = new JScrollPane(textArea);
                dialog.add(textScrollPane, BorderLayout.NORTH);

                String imagePath = "./image/" + cityName + ".jpg";
                ImageIcon cityImageIcon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(1200, 800, Image.SCALE_SMOOTH));

                if (cityImageIcon.getIconWidth() == -1) {
                    JOptionPane.showMessageDialog(this, "Image not found for city: " + cityName);
                } else {
                    JLabel imageLabel = new JLabel(cityImageIcon);
                    JScrollPane imageScrollPane = new JScrollPane(imageLabel);
                    dialog.add(imageScrollPane, BorderLayout.CENTER);
                }

                dialog.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "No description found for city: " + cityName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new QueryCityUI().setVisible(true);
            }
        });
    }
}

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

public class QueryAttractionsUI extends JFrame {

	private JTable table;
    private DefaultTableModel tableModel;

    public QueryAttractionsUI() {
        setTitle("Query Attractions");
        setSize(1500, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);


        tableModel = new DefaultTableModel();
        tableModel.addColumn("景点编号");
        tableModel.addColumn("名称");
        tableModel.addColumn("详细地址");
        tableModel.addColumn("开放时间");
        tableModel.addColumn("所在城市");


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
                    JOptionPane.showMessageDialog(QueryAttractionsUI.this, "Please select an attraction to view its description and image.");
                } else {
                    String attractionName = (String) table.getValueAt(selectedRow, 1);
                    showAttractionDescriptionAndImage(attractionName);
                }
            }
        });
    }

    private void loadData() {
        try (Connection conn = new Connect().getConnection()) {
            String sql = "SELECT AttractionID, Name, DetailedAddress, OpeningHours, CityName FROM Attraction";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(String.valueOf(rs.getInt("AttractionID")));
                row.add(rs.getString("Name"));
                row.add(rs.getString("DetailedAddress"));
                row.add(rs.getString("OpeningHours"));
                row.add(rs.getString("CityName"));
                tableModel.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAttractionDescriptionAndImage(String attractionName) {
        try (Connection conn = new Connect().getConnection()) {
            String sql = "SELECT Description FROM Attraction WHERE Name = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, attractionName);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String description = rs.getString("Description");


                JDialog dialog = new JDialog(this, "景点详情 " + attractionName, true);
                dialog.setSize(1200, 900);
                dialog.setLayout(new BorderLayout());

                JTextArea textArea = new JTextArea(description);
                textArea.setLineWrap(true);
                textArea.setWrapStyleWord(true);
                textArea.setEditable(false);
                textArea.setPreferredSize(new Dimension(1200,50));
                JScrollPane textScrollPane = new JScrollPane(textArea);
                dialog.add(textScrollPane, BorderLayout.NORTH);

                String imagePath = "./image/Attractions" + attractionName + ".jpg";
                ImageIcon attractionImageIcon = new ImageIcon(imagePath);
                Image img = attractionImageIcon.getImage();
                Image scaledImg = img.getScaledInstance(1200, 800, Image.SCALE_SMOOTH);
                attractionImageIcon = new ImageIcon(scaledImg);


                if (attractionImageIcon.getIconWidth() == -1) {
                    JOptionPane.showMessageDialog(this, "Image not found for attraction: " + attractionName);
                } else {
                    JLabel imageLabel = new JLabel(attractionImageIcon);
                    JScrollPane imageScrollPane = new JScrollPane(imageLabel);
                    dialog.add(imageScrollPane, BorderLayout.CENTER);
                }

                dialog.setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

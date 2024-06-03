package User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import Database.Connect;
import LoginRegister.LoginRegisterUI;

public class BookAttractionUI extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;

    public BookAttractionUI() {
        setTitle("Book Free Attraction");
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
        JButton bookAttractionButton = new JButton("购票");

        buttonPanel.add(viewImageButton);
        buttonPanel.add(bookAttractionButton);
        add(buttonPanel, BorderLayout.SOUTH);


        viewImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(BookAttractionUI.this, "Please select an attraction to view its description and image.");
                } else {
                    String attractionName = (String) table.getValueAt(selectedRow, 1);
                    showAttractionDescriptionAndImage(attractionName);
                }
            }
        });


        bookAttractionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(BookAttractionUI.this, "Please select an attraction to book.");
                } else {
                    String attractionID = (String) table.getValueAt(selectedRow, 0);
                    showVisitTimeDialog(attractionID);
                }
            }
        });
    }

    private void loadData() {
        try (Connection conn = new Connect().getConnection()) {
            String sql = "SELECT a.AttractionID, a.Name, a.DetailedAddress, a.OpeningHours, a.CityName " +
                         "FROM FreeAttraction fa " +
                         "JOIN Attraction a ON fa.AttractionID = a.AttractionID " +
                         "WHERE fa.ReservationRequired = 1";
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


                JDialog dialog = new JDialog(this, "景点 " + attractionName, true);
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

                dialog.setLocationRelativeTo(null); 
                dialog.setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showVisitTimeDialog(final String attractionID) {
        final JDialog visitTimeDialog = new JDialog(this, "Select Visit Time", true);
        visitTimeDialog.setSize(300, 200);
        visitTimeDialog.setLayout(new BorderLayout());

        JLabel label = new JLabel("选择时间（7日以内）:");
        visitTimeDialog.add(label, BorderLayout.NORTH);


        final JComboBox<Date> visitTimeComboBox = new JComboBox<>();
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < 7; i++) {
            visitTimeComboBox.addItem(calendar.getTime());
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }

        visitTimeDialog.add(visitTimeComboBox, BorderLayout.CENTER);

        JButton confirmButton = new JButton("确认");
        visitTimeDialog.add(confirmButton, BorderLayout.SOUTH);

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date selectedVisitTime = (Date) visitTimeComboBox.getSelectedItem();
                bookAttraction(attractionID, selectedVisitTime);
                visitTimeDialog.dispose();
            }
        });

        visitTimeDialog.setLocationRelativeTo(null); 
        visitTimeDialog.setVisible(true);
    }

    private void bookAttraction(String attractionID, Date visitTime) {
        try (Connection conn = new Connect().getConnection()) {
            String sql = "INSERT INTO TicketPurchaseRecord (AttractionID, VisitorID, PurchaseTime, VisitTime) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(attractionID));
            pstmt.setString(2, LoginRegisterUI.userID);  
            pstmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            pstmt.setTimestamp(4, new Timestamp(visitTime.getTime()));
            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "Booking successful!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

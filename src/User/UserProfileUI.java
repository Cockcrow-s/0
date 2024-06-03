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

public class UserProfileUI extends JFrame {

    private JTextField nameField;
    private JTextField contactInfoField;
    private JTable ticketTable;
    private DefaultTableModel ticketTableModel;

    public UserProfileUI() {
        setTitle("用户中心");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);


        JPanel userInfoPanel = new JPanel(new GridLayout(3, 2));
        userInfoPanel.add(new JLabel("名字:"));
        nameField = new JTextField();
        userInfoPanel.add(nameField);
        userInfoPanel.add(new JLabel("联系方式:"));
        contactInfoField = new JTextField();
        userInfoPanel.add(contactInfoField);
        JButton updateButton = new JButton("更新个人信息");
        userInfoPanel.add(updateButton);


        ticketTableModel = new DefaultTableModel();
        ticketTableModel.addColumn("景点编号");
        ticketTableModel.addColumn("景点名称");
        ticketTableModel.addColumn("支付时间");
        ticketTableModel.addColumn("游览时间");
        ticketTable = new JTable(ticketTableModel);
        JScrollPane ticketScrollPane = new JScrollPane(ticketTable);


        loadUserInfo();
        loadTicketData();


        setLayout(new BorderLayout());
        add(userInfoPanel, BorderLayout.NORTH);
        add(ticketScrollPane, BorderLayout.CENTER);


        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateUserInfo();
            }
        });


        JPanel buttonPanel = new JPanel();
        JButton deleteButton = new JButton("退票");
        JButton updateVisitTimeButton = new JButton("更新游览时间");
        buttonPanel.add(deleteButton);
        buttonPanel.add(updateVisitTimeButton);
        add(buttonPanel, BorderLayout.SOUTH);


        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteTicket();
            }
        });


        updateVisitTimeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateVisitTime();
            }
        });
    }

    private void loadUserInfo() {
        try (Connection conn = new Connect().getConnection()) {
            String sql = "SELECT Name, ContactInfo FROM Visitor WHERE IDCard = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, LoginRegisterUI.userID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                nameField.setText(rs.getString("Name"));
                contactInfoField.setText(rs.getString("ContactInfo"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadTicketData() {
        try (Connection conn = new Connect().getConnection()) {
            String sql = "SELECT t.AttractionID, a.Name, t.PurchaseTime, t.VisitTime " +
                         "FROM TicketPurchaseRecord t " +
                         "JOIN Attraction a ON t.AttractionID = a.AttractionID " +
                         "WHERE t.VisitorID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, LoginRegisterUI.userID);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(String.valueOf(rs.getInt("AttractionID")));
                row.add(rs.getString("Name"));
                row.add(rs.getTimestamp("PurchaseTime").toString());
                row.add(rs.getTimestamp("VisitTime").toString());
                ticketTableModel.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateUserInfo() {
        try (Connection conn = new Connect().getConnection()) {
            String sql = "UPDATE Visitor SET Name = ?, ContactInfo = ? WHERE IDCard = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nameField.getText());
            pstmt.setString(2, contactInfoField.getText());
            pstmt.setString(3, LoginRegisterUI.userID);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Information updated successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteTicket() {
        int selectedRow = ticketTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a ticket to delete.");
            return;
        }

        String attractionID = (String) ticketTableModel.getValueAt(selectedRow, 0);
        String purchaseTime = (String) ticketTableModel.getValueAt(selectedRow, 2);
        String visitTime = (String) ticketTableModel.getValueAt(selectedRow, 3);

        try (Connection conn = new Connect().getConnection()) {
            String sql = "DELETE FROM TicketPurchaseRecord WHERE AttractionID = ? AND VisitorID = ? AND PurchaseTime = ? AND VisitTime = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(attractionID));
            pstmt.setString(2, LoginRegisterUI.userID);
            pstmt.setTimestamp(3, Timestamp.valueOf(purchaseTime));
            pstmt.setTimestamp(4, Timestamp.valueOf(visitTime));
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Ticket deleted successfully!");
            ticketTableModel.removeRow(selectedRow);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateVisitTime() {
        int selectedRow = ticketTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a ticket to update.");
            return;
        }

        final String attractionID = (String) ticketTableModel.getValueAt(selectedRow, 0);
        final String purchaseTime = (String) ticketTableModel.getValueAt(selectedRow, 2);


        final JDialog visitTimeDialog = new JDialog(this, "Select New Visit Time", true);
        visitTimeDialog.setSize(300, 200);
        visitTimeDialog.setLayout(new BorderLayout());

        JLabel label = new JLabel("Select Visit Time (within 7 days):");
        visitTimeDialog.add(label, BorderLayout.NORTH);


        final JComboBox<Date> visitTimeComboBox = new JComboBox<>();
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < 7; i++) {
            visitTimeComboBox.addItem(calendar.getTime());
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }

        visitTimeDialog.add(visitTimeComboBox, BorderLayout.CENTER);

        JButton confirmButton = new JButton("Confirm");
        visitTimeDialog.add(confirmButton, BorderLayout.SOUTH);

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date newVisitTime = (Date) visitTimeComboBox.getSelectedItem();
                updateVisitTimeInDatabase(attractionID, purchaseTime, newVisitTime);
                visitTimeDialog.dispose();
            }
        });

        visitTimeDialog.setLocationRelativeTo(null); 
        visitTimeDialog.setVisible(true);
    }

    private void updateVisitTimeInDatabase(String attractionID, String purchaseTime, Date newVisitTime) {
        try (Connection conn = new Connect().getConnection()) {
            String sql = "UPDATE TicketPurchaseRecord SET VisitTime = ? WHERE AttractionID = ? AND VisitorID = ? AND PurchaseTime = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setTimestamp(1, new Timestamp(newVisitTime.getTime()));
            pstmt.setInt(2, Integer.parseInt(attractionID));
            pstmt.setString(3, LoginRegisterUI.userID);
            pstmt.setTimestamp(4, Timestamp.valueOf(purchaseTime));
            pstmt.executeUpdate();


            int selectedRow = ticketTable.getSelectedRow();
            ticketTableModel.setValueAt(newVisitTime.toString(), selectedRow, 3);

            JOptionPane.showMessageDialog(this, "Visit time updated successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

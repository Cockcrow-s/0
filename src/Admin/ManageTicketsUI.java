package Admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

import Database.Connect;

public class ManageTicketsUI extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;

    public ManageTicketsUI() {
        setTitle("Manage Tickets");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);


        tableModel = new DefaultTableModel();
        tableModel.addColumn("景点编号");
        tableModel.addColumn("类型");
        tableModel.addColumn("价格");


        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);


        loadData();


        JPanel buttonPanel = new JPanel();
        JButton updateButton = new JButton("更新");
        JButton insertButton = new JButton("插入");
        JButton deleteButton = new JButton("删除");

        buttonPanel.add(updateButton);
        buttonPanel.add(insertButton);
        buttonPanel.add(deleteButton);


        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTicket();
            }
        });

        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertTicket();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteTicket();
            }
        });


        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadData() {
        try (Connection conn = new Connect().getConnection()) {
            String sql = "SELECT * FROM Ticket";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(rs.getString("AttractionID"));
                row.add(rs.getString("Type"));
                row.add(rs.getString("Price"));
                tableModel.addRow(row);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void updateTicket() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to update.");
            return;
        }

        String attractionID = (String) tableModel.getValueAt(selectedRow, 0);
        String type = (String) tableModel.getValueAt(selectedRow, 1);
        String price = (String) tableModel.getValueAt(selectedRow, 2);

        try (Connection conn = new Connect().getConnection()) {
            String sql = "UPDATE Ticket SET Type = ?, Price = ? WHERE AttractionID = ? AND Type = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, type);
            pstmt.setString(2, price);
            pstmt.setString(3, attractionID);
            pstmt.setString(4, type);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Update successful.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void insertTicket() {
        String attractionID = JOptionPane.showInputDialog(this, "输入景点编号:");
        String type = JOptionPane.showInputDialog(this, "输入门票类型:");
        String price = JOptionPane.showInputDialog(this, "输入门票价格:");

        try (Connection conn = new Connect().getConnection()) {
            String sql = "INSERT INTO Ticket (AttractionID, Type, Price) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, attractionID);
            pstmt.setString(2, type);
            pstmt.setString(3, price);
            pstmt.executeUpdate();
            Vector<String> row = new Vector<>();
            row.add(attractionID);
            row.add(type);
            row.add(price);
            tableModel.addRow(row);
            JOptionPane.showMessageDialog(this, "Insert successful.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void deleteTicket() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.");
            return;
        }

        String attractionID = (String) tableModel.getValueAt(selectedRow, 0);
        String type = (String) tableModel.getValueAt(selectedRow, 1);

        try (Connection conn = new Connect().getConnection()) {
            String sql = "DELETE FROM Ticket WHERE AttractionID = ? AND Type = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, attractionID);
            pstmt.setString(2, type);
            pstmt.executeUpdate();
            tableModel.removeRow(selectedRow);
            JOptionPane.showMessageDialog(this, "Delete successful.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ManageTicketsUI().setVisible(true);
            }
        });
    }
}

package Admin;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

import Database.Connect;
public class ManageCitiesUI extends JFrame{
	private JTable table;
    private DefaultTableModel tableModel;

    public ManageCitiesUI() {
        setTitle("城市管理");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);


        tableModel = new DefaultTableModel();
        tableModel.addColumn("名称");



        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

   
        loadData();


        JPanel buttonPanel = new JPanel();
        JButton updateButton = new JButton("更新");
        JButton insertButton = new JButton("增加");
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
            String sql = "SELECT * FROM City";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(rs.getString("Name"));
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

        String Name = (String) tableModel.getValueAt(selectedRow, 0);


        try (Connection conn = new Connect().getConnection()) {
            String sql = "UPDATE City SET Name = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, Name);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Update successful.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void insertTicket() {
        String Name = JOptionPane.showInputDialog(this, "输入名字:");


        try (Connection conn = new Connect().getConnection()) {
            String sql = "INSERT INTO City (Name) VALUES (?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, Name);
            pstmt.executeUpdate();
            Vector<String> row = new Vector<>();
            row.add(Name);
            tableModel.addRow(row);
            JOptionPane.showMessageDialog(this, "成功插入.");
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

        String Name = (String) tableModel.getValueAt(selectedRow, 0);

        try (Connection conn = new Connect().getConnection()) {
            String sql = "DELETE FROM City WHERE Name = ? ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, Name);
            pstmt.executeUpdate();
            tableModel.removeRow(selectedRow);
            JOptionPane.showMessageDialog(this, "成功删除.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ManageCitiesUI().setVisible(true);
            }
        });
    }
}

package Admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

import Database.Connect;

public class ManageCompaniesUI extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;

    public ManageCompaniesUI() {
        setTitle("����˾");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);


        tableModel = new DefaultTableModel();
        tableModel.addColumn("��˾���");
        tableModel.addColumn("����");
        tableModel.addColumn("��ַ");


        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);


        loadData();


        JPanel buttonPanel = new JPanel();
        JButton updateButton = new JButton("����");
        JButton insertButton = new JButton("����");
        JButton deleteButton = new JButton("ɾ��");

        buttonPanel.add(updateButton);
        buttonPanel.add(insertButton);
        buttonPanel.add(deleteButton);

 
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCompany();
            }
        });

        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertCompany();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteCompany();
            }
        });

  
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadData() {
        try (Connection conn = new Connect().getConnection()) {
            String sql = "SELECT * FROM TravelCompany";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(rs.getString("CompanyID"));
                row.add(rs.getString("Name"));
                row.add(rs.getString("Address"));
                tableModel.addRow(row);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void updateCompany() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to update.");
            return;
        }

        String companyID = (String) tableModel.getValueAt(selectedRow, 0);
        String name = (String) tableModel.getValueAt(selectedRow, 1);
        String address = (String) tableModel.getValueAt(selectedRow, 2);

        try (Connection conn = new Connect().getConnection()) {
            String sql = "UPDATE TravelCompany SET Name = ?, Address = ? WHERE CompanyID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, address);
            pstmt.setString(3, companyID);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Update successful.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void insertCompany() {
        String companyID = JOptionPane.showInputDialog(this, "���빫˾���:");
        String name = JOptionPane.showInputDialog(this, "��������:");
        String address = JOptionPane.showInputDialog(this, "�����ַ:");

        try (Connection conn = new Connect().getConnection()) {
            String sql = "INSERT INTO TravelCompany (CompanyID, Name, Address) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, companyID);
            pstmt.setString(2, name);
            pstmt.setString(3, address);
            pstmt.executeUpdate();
            Vector<String> row = new Vector<>();
            row.add(companyID);
            row.add(name);
            row.add(address);
            tableModel.addRow(row);
            JOptionPane.showMessageDialog(this, "Insert successful.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void deleteCompany() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.");
            return;
        }

        String companyID = (String) tableModel.getValueAt(selectedRow, 0);

        try (Connection conn = new Connect().getConnection()) {
            String sql = "DELETE FROM TravelCompany WHERE CompanyID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, companyID);
            pstmt.executeUpdate();
            tableModel.removeRow(selectedRow);
            JOptionPane.showMessageDialog(this, "�ɹ�ɾ��.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ManageCompaniesUI().setVisible(true);
            }
        });
    }
}

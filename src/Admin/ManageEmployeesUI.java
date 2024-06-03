package Admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

import Database.Connect;

public class ManageEmployeesUI extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;

    public ManageEmployeesUI() {
        setTitle("Manage Employees");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);


        tableModel = new DefaultTableModel();
        tableModel.addColumn("ְԱID");
        tableModel.addColumn("����");
        tableModel.addColumn("ְλ");
        tableModel.addColumn("����ʱ��");
        tableModel.addColumn("��ϵ��ʽ");
        tableModel.addColumn("���֤��");


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
                updateEmployee();
            }
        });

        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertEmployee();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteEmployee();
            }
        });


        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadData() {
        try (Connection conn = new Connect().getConnection()) {
            String sql = "SELECT * FROM Employee";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(rs.getString("EmployeeID"));
                row.add(rs.getString("Name"));
                row.add(rs.getString("Position"));
                row.add(rs.getString("WorkHours"));
                row.add(rs.getString("ContactInfo"));
                row.add(rs.getString("IDCard"));
                tableModel.addRow(row);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void updateEmployee() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to update.");
            return;
        }

        String employeeID = (String) tableModel.getValueAt(selectedRow, 0);
        String name = (String) tableModel.getValueAt(selectedRow, 1);
        String position = (String) tableModel.getValueAt(selectedRow, 2);
        String workHours = (String) tableModel.getValueAt(selectedRow, 3);
        String contactInfo = (String) tableModel.getValueAt(selectedRow, 4);
        String idCard = (String) tableModel.getValueAt(selectedRow, 5);

        try (Connection conn = new Connect().getConnection()) {
            String sql = "{CALL UpdateEmployee(?, ?, ?, ?, ?, ?)}";
            CallableStatement cstmt = conn.prepareCall(sql);
            cstmt.setInt(1, Integer.parseInt(employeeID));
            cstmt.setString(2, idCard);
            cstmt.setString(3, name);
            cstmt.setString(4, position);
            cstmt.setString(5, workHours);
            cstmt.setString(6, contactInfo);
            cstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Update successful.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void insertEmployee() {
        String employeeID = JOptionPane.showInputDialog(this, "����ְԱID:");
        String name = JOptionPane.showInputDialog(this, "��������:");
        String position = JOptionPane.showInputDialog(this, "����ְλ:");
        String workHours = JOptionPane.showInputDialog(this, "���빤��ʱ��:");
        String contactInfo = JOptionPane.showInputDialog(this, "������ϵ��ʽ:");
        String idCard = JOptionPane.showInputDialog(this, "�������֤��:");

        try (Connection conn = new Connect().getConnection()) {
            String sql = "INSERT INTO Employee (EmployeeID, Name, Position, WorkHours, ContactInfo, IDCard) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(employeeID));
            pstmt.setString(2, name);
            pstmt.setString(3, position);
            pstmt.setString(4, workHours);
            pstmt.setString(5, contactInfo);
            pstmt.setString(6, idCard);
            pstmt.executeUpdate();
            Vector<String> row = new Vector<>();
            row.add(employeeID);
            row.add(name);
            row.add(position);
            row.add(workHours);
            row.add(contactInfo);
            row.add(idCard);
            tableModel.addRow(row);
            JOptionPane.showMessageDialog(this, "Insert successful.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void deleteEmployee() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.");
            return;
        }

        String employeeID = (String) tableModel.getValueAt(selectedRow, 0);
        String idCard = (String) tableModel.getValueAt(selectedRow, 5);

        Connection conn = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;

        try {
            conn = new Connect().getConnection();
            conn.setAutoCommit(false); // ��ʼ����

            // ɾ��responsible�������Ա����صļ�¼
            String sql1 = "DELETE FROM responsible WHERE EmployeeID = ?";
            pstmt1 = conn.prepareStatement(sql1);
            pstmt1.setInt(1, Integer.parseInt(employeeID));
            pstmt1.executeUpdate();

            // ɾ��employee���е�Ա����¼
            String sql2 = "DELETE FROM employee WHERE EmployeeID = ? AND IDCard = ?";
            pstmt2 = conn.prepareStatement(sql2);
            pstmt2.setInt(1, Integer.parseInt(employeeID));
            pstmt2.setString(2, idCard);
            pstmt2.executeUpdate();

            conn.commit(); // �ύ����
            tableModel.removeRow(selectedRow);
            JOptionPane.showMessageDialog(this, "Delete successful.");
        } catch (SQLException ex) {
            if (conn != null) {
                try {
                    conn.rollback(); // �����쳣ʱ�ع�����
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            ex.printStackTrace();
        } finally {
            try {
                if (pstmt1 != null) pstmt1.close();
                if (pstmt2 != null) pstmt2.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ManageEmployeesUI().setVisible(true);
            }
        });
    }
}

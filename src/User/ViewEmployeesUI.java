package User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import Database.Connect;

public class ViewEmployeesUI extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;

    public ViewEmployeesUI() {
        setTitle("View Employees");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);


        tableModel = new DefaultTableModel();
        tableModel.addColumn("EmployeeID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Position");
        tableModel.addColumn("WorkHours");
        tableModel.addColumn("ContactInfo");


        table = new JTable(tableModel);
        table.setEnabled(false); 
        JScrollPane scrollPane = new JScrollPane(table);

   
        loadData();

      
        add(scrollPane, BorderLayout.CENTER);
    }

    private void loadData() {
        try (Connection conn = new Connect().getConnection()) {
            String sql = "SELECT EmployeeID, Name, Position, WorkHours, ContactInfo FROM Employee";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(String.valueOf(rs.getInt("EmployeeID")));
                row.add(rs.getString("Name"));
                row.add(rs.getString("Position"));
                row.add(rs.getString("WorkHours"));
                row.add(rs.getString("ContactInfo"));
                tableModel.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
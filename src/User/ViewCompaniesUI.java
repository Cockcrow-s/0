package User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import Database.Connect;

public class ViewCompaniesUI extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;

    public ViewCompaniesUI() {
        setTitle("View Companies");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);


        tableModel = new DefaultTableModel();
        tableModel.addColumn("AttractionID");
        tableModel.addColumn("CompanyID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Address");


        table = new JTable(tableModel);
        table.setEnabled(false); 
        JScrollPane scrollPane = new JScrollPane(table);


        loadData();


        add(scrollPane, BorderLayout.CENTER);
    }

    private void loadData() {
        try (Connection conn = new Connect().getConnection()) {
            String sql = "SELECT * FROM companyattractions"; 
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(String.valueOf(rs.getInt("AttractionID")));
                row.add(String.valueOf(rs.getInt("CompanyID")));
                row.add(rs.getString("Name"));
                row.add(rs.getString("Address"));
                tableModel.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ViewCompaniesUI().setVisible(true);
            }
        });
    }
}

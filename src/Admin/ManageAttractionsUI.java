package Admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

import Database.Connect;

public class ManageAttractionsUI extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;

    public ManageAttractionsUI() {
        setTitle("景点管理");
        setSize(1500, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);


        tableModel = new DefaultTableModel();
        tableModel.addColumn("景点编号");
        tableModel.addColumn("景点名称");
        tableModel.addColumn("详细地址");
        tableModel.addColumn("开放时间");
        tableModel.addColumn("所在城市");
        tableModel.addColumn("是否需要预约");
        tableModel.addColumn("人数限制");


        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);


        loadData();


        JPanel buttonPanel = new JPanel();
        JButton updateButton = new JButton("更新");
        JButton insertButton = new JButton("增加");
        JButton deleteButton = new JButton("删除");
        JButton viewDetailsButton = new JButton("查看详情");
        JButton viewImageButton = new JButton("查看图片");

        buttonPanel.add(updateButton);
        buttonPanel.add(insertButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(viewDetailsButton);
        buttonPanel.add(viewImageButton);


        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateAttraction();
            }
        });

        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertAttraction();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteAttraction();
            }
        });

        viewDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewDetails();
            }
        });

        viewImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewImage();
            }
        });


        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadData() {
        try (Connection conn = new Connect().getConnection()) {
            String sql = "SELECT a.AttractionID, a.Name, a.DetailedAddress, a.OpeningHours, a.CityName, "
                       + "fa.ReservationRequired, fa.VisitorLimit as FreeVisitorLimit, "
                       + "pa.VisitorLimit as PaidVisitorLimit "
                       + "FROM attraction a "
                       + "LEFT JOIN freeattraction fa ON a.AttractionID = fa.AttractionID "
                       + "LEFT JOIN paidattraction pa ON a.AttractionID = pa.AttractionID";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(rs.getString("AttractionID"));
                row.add(rs.getString("Name"));
                row.add(rs.getString("DetailedAddress"));
                row.add(rs.getString("OpeningHours"));
                row.add(rs.getString("CityName"));
                row.add(rs.getBoolean("ReservationRequired") ? "是" : "否");
                row.add(rs.getString("FreeVisitorLimit") != null ? rs.getString("FreeVisitorLimit") : rs.getString("PaidVisitorLimit"));
                tableModel.addRow(row);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void updateAttraction() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to update.");
            return;
        }

        String attractionID = (String) tableModel.getValueAt(selectedRow, 0);
        String name = (String) tableModel.getValueAt(selectedRow, 1);
        String detailedAddress = (String) tableModel.getValueAt(selectedRow, 2);
        String openingHours = (String) tableModel.getValueAt(selectedRow, 3);
        String cityName = (String) tableModel.getValueAt(selectedRow, 4);
        String reservationRequired = (String) tableModel.getValueAt(selectedRow, 5);
        String visitorLimit = (String) tableModel.getValueAt(selectedRow, 6);

        try (Connection conn = new Connect().getConnection()) {
            // 调用存储过程
            String sql = "{CALL updateAttraction(?, ?, ?, ?, ?, ?, ?)}";
            CallableStatement cstmt = conn.prepareCall(sql);
            cstmt.setInt(1, Integer.parseInt(attractionID));
            cstmt.setString(2, name);
            cstmt.setString(3, detailedAddress);
            cstmt.setString(4, openingHours);
            cstmt.setString(5, cityName);
            cstmt.setString(6, reservationRequired);
            cstmt.setInt(7, Integer.parseInt(visitorLimit));
            cstmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "Update successful.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    private void insertAttraction() {
        String attractionID = JOptionPane.showInputDialog(this, "输入编号:");
        String name = JOptionPane.showInputDialog(this, "输入名称:");
        String detailedAddress = JOptionPane.showInputDialog(this, "输入详细地址:");
        String openingHours = JOptionPane.showInputDialog(this, "输入开放时间:");
        String cityName = JOptionPane.showInputDialog(this, "输入所在城市:");

        try (Connection conn = new Connect().getConnection()) {
            String sql = "INSERT INTO Attraction (AttractionID, Name, DetailedAddress, OpeningHours, CityName) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(attractionID));
            pstmt.setString(2, name);
            pstmt.setString(3, detailedAddress);
            pstmt.setString(4, openingHours);
            pstmt.setString(5, cityName);
            pstmt.executeUpdate();

            Vector<String> row = new Vector<>();
            row.add(attractionID);
            row.add(name);
            row.add(detailedAddress);
            row.add(openingHours);
            row.add(cityName);
            tableModel.addRow(row);


            int option = JOptionPane.showOptionDialog(this, "是否免费?", "Select Type",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Yes", "No"}, "Yes");
            if (option == JOptionPane.YES_OPTION) {
                String reservationRequired = JOptionPane.showInputDialog(this, "Enter ReservationRequired (true/false):");
                String visitorLimit = JOptionPane.showInputDialog(this, "Enter VisitorLimit:");

                sql = "INSERT INTO freeattraction (AttractionID, ReservationRequired, VisitorLimit) VALUES (?, ?, ?)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, Integer.parseInt(attractionID));
                pstmt.setBoolean(2, Boolean.parseBoolean(reservationRequired));
                pstmt.setInt(3, Integer.parseInt(visitorLimit));
                pstmt.executeUpdate();

                tableModel.setValueAt(Boolean.parseBoolean(reservationRequired) ? "是" : "否", tableModel.getRowCount() - 1, 5);
                tableModel.setValueAt(visitorLimit, tableModel.getRowCount() - 1, 6);
            } else {
                String visitorLimit = JOptionPane.showInputDialog(this, "输入人数限制:");

                sql = "INSERT INTO paidattraction (AttractionID, VisitorLimit) VALUES (?, ?)";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, Integer.parseInt(attractionID));
                pstmt.setInt(2, Integer.parseInt(visitorLimit));
                pstmt.executeUpdate();

                tableModel.setValueAt(null, tableModel.getRowCount() - 1, 5);
                tableModel.setValueAt(visitorLimit, tableModel.getRowCount() - 1, 6);
            }


            int updateCityOption = JOptionPane.showConfirmDialog(this, "是否更新描述", "Update City Description", JOptionPane.YES_NO_OPTION);
            if (updateCityOption == JOptionPane.YES_OPTION) {
                String newDescription = JOptionPane.showInputDialog(this, "添加新的描述:");
                sql = "{CALL UpdateCityDescription(?, ?)}";
                CallableStatement cstmt = conn.prepareCall(sql);
                cstmt.setString(1, cityName);
                cstmt.setString(2, newDescription);
                cstmt.execute();
                JOptionPane.showMessageDialog(this, "City description updated successfully.");
            }

            JOptionPane.showMessageDialog(this, "成功增加.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void deleteAttraction() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.");
            return;
        }

        String attractionID = (String) tableModel.getValueAt(selectedRow, 0);

        try (Connection conn = new Connect().getConnection()) {
            String sql = "DELETE FROM Attraction WHERE AttractionID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(attractionID));
            pstmt.executeUpdate();
            tableModel.removeRow(selectedRow);
            JOptionPane.showMessageDialog(this, "成功删除.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void viewDetails() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to view details.");
            return;
        }

        String attractionID = (String) tableModel.getValueAt(selectedRow, 0);

        try (Connection conn = new Connect().getConnection()) {
            String sql = "SELECT Description FROM Attraction WHERE AttractionID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(attractionID));
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String description = rs.getString("Description");

                JTextArea descriptionArea = new JTextArea(description, 10, 30);
                descriptionArea.setLineWrap(true);
                descriptionArea.setWrapStyleWord(true);
                JScrollPane descriptionScrollPane = new JScrollPane(descriptionArea);

                int option = JOptionPane.showConfirmDialog(this, descriptionScrollPane, "View Details",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (option == JOptionPane.OK_OPTION) {
                    description = descriptionArea.getText();
                    sql = "UPDATE Attraction SET Description = ? WHERE AttractionID = ?";
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, description);
                    pstmt.setInt(2, Integer.parseInt(attractionID));
                    pstmt.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Description updated successfully.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void viewImage() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to view image.");
            return;
        }

        String name = (String) tableModel.getValueAt(selectedRow, 1);

        try {
            String imagePath = "./image/Attractions" + name + ".jpg";
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(1200, 900, Image.SCALE_SMOOTH));
            JLabel imageLabel = new JLabel(imageIcon);
            imageLabel.setPreferredSize(new Dimension(1200, 900));

            JOptionPane.showMessageDialog(this, imageLabel, "View Image", JOptionPane.PLAIN_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ManageAttractionsUI().setVisible(true);
            }
        });
    }
}

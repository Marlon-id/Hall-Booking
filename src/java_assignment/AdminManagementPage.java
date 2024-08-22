package java_assignment;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AdminManagementPage extends JFrame implements ActionListener {

    private JTable adminTable;
    private DefaultTableModel tableModel;
    private JButton editButton, deleteButton, backButton;

    public AdminManagementPage() {
        setupUI();
    }

    private void setupUI() {
        setTitle("Admin Management");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Table setup
        String[] columns = {"Username", "Role"};
        tableModel = new DefaultTableModel(columns, 0);
        adminTable = new JTable(tableModel);
        loadAdminData();

        JScrollPane scrollPane = new JScrollPane(adminTable);
        scrollPane.setBounds(50, 50, 500, 200);
        add(scrollPane);

        // Buttons
        editButton = new JButton("Edit");
        editButton.setBounds(150, 300, 100, 30);
        editButton.addActionListener(this);
        add(editButton);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(270, 300, 100, 30);
        deleteButton.addActionListener(this);
        add(deleteButton);

        backButton = new JButton("Back");
        backButton.setBounds(390, 300, 100, 30);
        backButton.addActionListener(this);
        add(backButton);

        setVisible(true);
    }

    private void loadAdminData() {
        tableModel.setRowCount(0);
        for (Admin admin : FileHandler.allAdmin) {
            if (!admin.getRole().equals("superadmin")) {
                tableModel.addRow(new Object[]{admin.getName(), admin.getRole()});
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        int selectedRow = adminTable.getSelectedRow();

        if (e.getSource() == editButton) {
            if (selectedRow != -1) {
                editAdmin(selectedRow);
            } else {
                JOptionPane.showMessageDialog(this, "Please select an admin to edit.");
            }
        } else if (e.getSource() == deleteButton) {
            if (selectedRow != -1) {
                deleteAdmin(selectedRow);
            } else {
                JOptionPane.showMessageDialog(this, "Please select an admin to delete.");
            }
        } else if (e.getSource() == backButton) {
            dispose();
            new PageAdmin("superadmin");
        }
    }

    private void editAdmin(int selectedRow) {
        String username = (String) tableModel.getValueAt(selectedRow, 0);
        String newRole = JOptionPane.showInputDialog(this, "Enter new role for " + username, tableModel.getValueAt(selectedRow, 1));
        
        if (newRole != null && !newRole.trim().isEmpty()) {
            for (Admin admin : FileHandler.allAdmin) {
                if (admin.getName().equals(username)) {
                    admin.setRole(newRole.trim());
                    FileHandler.write();
                    loadAdminData();
                    JOptionPane.showMessageDialog(this, "Admin role updated successfully.");
                    return;
                }
            }
        }
    }

    private void deleteAdmin(int selectedRow) {
        String username = (String) tableModel.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete admin " + username + "?", "Delete Admin", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            for (int i = 0; i < FileHandler.allAdmin.size(); i++) {
                if (FileHandler.allAdmin.get(i).getName().equals(username)) {
                    FileHandler.allAdmin.remove(i);
                    FileHandler.write();
                    loadAdminData();
                    JOptionPane.showMessageDialog(this, "Admin deleted successfully.");
                    return;
                }
            }
        }
    }
}

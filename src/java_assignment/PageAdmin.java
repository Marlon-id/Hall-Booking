package java_assignment;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class PageAdmin extends JFrame implements ActionListener {

    private String role;
    private JButton manageUsersButton, manageAdminsButton, logoutButton;

    public PageAdmin(String role) {
        this.role = role;
        setupUI();
    }

    private void setupUI() {
        setTitle(role.equals("superadmin") ? "Superadmin Dashboard" : "Admin Dashboard");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        manageUsersButton = new JButton("Manage Users");
        manageUsersButton.setBounds(100, 50, 200, 30);
        manageUsersButton.addActionListener(this);
        add(manageUsersButton);

        if (role.equals("superadmin")) {
            manageAdminsButton = new JButton("Manage Admins");
            manageAdminsButton.setBounds(100, 100, 200, 30);
            manageAdminsButton.addActionListener(this);
            add(manageAdminsButton);
        }

        logoutButton = new JButton("Logout");
        logoutButton.setBounds(150, 200, 100, 30);
        logoutButton.addActionListener(this);
        add(logoutButton);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == manageUsersButton) {
            new UserManagementPage();
        } else if (role.equals("superadmin") && e.getSource() == manageAdminsButton) {
            new AdminManagementPage();
        } else if (e.getSource() == logoutButton) {
            dispose();
            new Login(); // Redirect to login page
        }
    }
}

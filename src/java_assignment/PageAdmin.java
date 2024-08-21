package java_assignment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class PageAdmin extends JFrame implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> roleComboBox;
    private JButton createButton;
    private String currentUserRole;

    public PageAdmin(String role) {
        this.currentUserRole = role;
        setTitle("Admin - Create User");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        usernameField = new JTextField(20);
        usernameField.setBounds(100, 20, 165, 25);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(100, 50, 165, 25);
        panel.add(passwordField);

        JLabel roleLabel = new JLabel("Role:");
        roleLabel.setBounds(10, 80, 80, 25);
        panel.add(roleLabel);

        String[] roles;
        if (currentUserRole.equals("superadmin")) {
            roles = new String[]{"Admin"};
        } else {
            roles = new String[]{"Scheduler", "Manager"};
        }
        roleComboBox = new JComboBox<>(roles);
        roleComboBox.setBounds(100, 80, 165, 25);
        panel.add(roleComboBox);

        createButton = new JButton("Create");
        createButton.setBounds(100, 110, 100, 25);
        createButton.addActionListener(this);
        panel.add(createButton);

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createButton) {
            String username = getUsername();
            String password = getPassword();
            String role = (String) roleComboBox.getSelectedItem();
            
            int passwordInt;
            try {
                passwordInt = Integer.parseInt(password);
                Admin newAdmin = new Admin(role, username, passwordInt);
                FileHandler.allAdmin.add(newAdmin);
                FileHandler.write();
                JOptionPane.showMessageDialog(this, "User created successfully!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Password must be a number.");
            }
        }
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }
}
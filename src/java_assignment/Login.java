package java_assignment;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class Login extends JFrame implements ActionListener {
    
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private JButton exitButton;

    public Login() {
        setupUI();
    }
    private void setupUI() {
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel headingLabel = new JLabel("Hall Booking Program");
        headingLabel.setBounds(80, -5, 250, 25);
        add(headingLabel);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 30, 100, 25);
        add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(150, 30, 100, 25);
        add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 60, 100, 25);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 60, 100, 25);
        add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(50, 100, 90, 25);
        loginButton.addActionListener(this);
        add(loginButton);

        registerButton = new JButton("Register");
        registerButton.setBounds(160, 100, 90, 25);
        registerButton.addActionListener(this);
        add(registerButton);

        exitButton = new JButton("Exit");
        exitButton.setBounds(195, 130, 80, 25);
        exitButton.setBackground(Color.RED);
        exitButton.setForeground(Color.WHITE);
        add(exitButton);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            login();
        } else if (e.getSource() == registerButton) {
            register();
        } else if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }

    private void login() {
        String username = usernameField.getText();
        int password = Integer.parseInt(new String(passwordField.getPassword()));

        for (Admin admin : FileHandler.allAdmin) {
            if (admin.getName().equals(username) && admin.getPassword() == password) {
                String role = admin.getRole();
                JOptionPane.showMessageDialog(this, "Login successful! Redirecting to your dashboard.");

                // Redirect user based on role
                switch (role) {
                    case "Customer":
                        JOptionPane.showMessageDialog(this, "Customer");
                        // new CustomerPage();
                        break;
                    case "Scheduler":
                        JOptionPane.showMessageDialog(this, "Scheduler");
                        // new SchedulerPage();
                        break;
                    case "Manager":
                        JOptionPane.showMessageDialog(this, "Manager");
                        // new ManagerPage();
                        break;
                    case "Admin":
                        // JOptionPane.showMessageDialog(this, "Admin");
                        new PageAdmin(role);
                        break;
                    case "superadmin":
                        new PageAdmin(role);
                        break;
                    default:
                        JOptionPane.showMessageDialog(this, "Unknown role. Please contact support.");
                        break;
                }
                dispose(); // Close the login window
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Invalid credentials. Please try again.");
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    private void register() {
        String username = usernameField.getText();
        int password = Integer.parseInt(new String(passwordField.getPassword()));

        Admin newCustomer = new Admin("customer", username, password);
        FileHandler.allAdmin.add(newCustomer);
        FileHandler.write();

        JOptionPane.showMessageDialog(this, "Registration successful!");
    }
}
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
        login();
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = usernameField.getText();
            String passwordStr = new String(passwordField.getPassword());
            int password;
            
            try {
                password = Integer.parseInt(passwordStr);
                
                if(FileHandler.isValidAdmin(username, password)) {
                    Admin loggedUser = FileHandler.getAdminByUsernameAndPassword(username, password);

                    if (loggedUser != null) {
                        String role = loggedUser.getRole();

                        // Close the login window
                        this.dispose();

                        // Open the appropriate window based on the role
                        if (role.equalsIgnoreCase("superadmin")) {
                            PageAdmin adminPage = new PageAdmin("superadmin");
                            adminPage.setVisible(true);
                        } else if (role.equalsIgnoreCase("admin")) {
                            PageAdmin adminPage = new PageAdmin("admin");
                            adminPage.setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(this, "Access denied. Only superadmin and admin can log in.");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid username or password.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Password must be a number.");
            }
        }else if (e.getSource() == registerButton) {
            register();
        } else if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }

    public void login() {
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 80, 25);
        panel.add(loginButton);

        registerButton = new JButton("Register");
        registerButton.setBounds(100, 80, 100, 25);
        panel.add(registerButton);

        exitButton = new JButton("Exit");
        exitButton.setBounds(195, 130, 80, 25);
        exitButton.setBackground(Color.RED);
        exitButton.setForeground(Color.WHITE);
        panel.add(exitButton);

        passwordField.addActionListener(this);
        usernameField.addActionListener(this);
        loginButton.addActionListener(this);
        registerButton.addActionListener(this);
        exitButton.addActionListener(this);

        add(panel);
        setVisible(true);
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
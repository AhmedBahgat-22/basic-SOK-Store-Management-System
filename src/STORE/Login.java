package STORE;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame implements ActionListener {

    protected JPanel panel;
    protected JTextField usernameField;
    protected JPasswordField passwordField;
    protected JButton loginButton;
    protected JButton backButton;
    protected JLabel messageLabel;
    protected JLabel FORM;

    public Login() {
        this.setTitle("Login");
        this.setSize(400, 350);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        initialize();

        this.setVisible(true);
    }

    protected void initialize() {
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(255, 214, 0));
        panel.setBounds(0, 0, 400, 350);
        this.add(panel);
        
        Font FORMFont = new Font("Times New Roman", Font.BOLD, 40);
        Font labelFont = new Font("Arial", Font.PLAIN, 18);
        Font fieldFont = new Font("Arial", Font.PLAIN, 16);
        Font buttonFont = new Font("Arial", Font.BOLD, 18);
        
        FORM = new JLabel("LOGIN");
        FORM.setFont(FORMFont);
        FORM.setBounds(130, 30, 200, 30);
        panel.add(FORM);
        
        JLabel userLabel = new JLabel("User name:");
        userLabel.setFont(labelFont);
        userLabel.setBounds(10, 90, 150, 30);
        panel.add(userLabel);
        
        usernameField = new JTextField(20);
        usernameField.setFont(fieldFont);
        usernameField.setBounds(120, 90, 250, 30);
        panel.add(usernameField);
        
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(labelFont);
        passwordLabel.setBounds(10, 145, 150, 30);
        panel.add(passwordLabel);
        
        passwordField = new JPasswordField(20);
        passwordField.setFont(fieldFont);
        passwordField.setBounds(120, 145, 250, 30);
        panel.add(passwordField);
        
        loginButton = new JButton("Login");
        loginButton.setFont(buttonFont);
        loginButton.setBounds(30, 250, 150, 40);
        loginButton.setBackground(Color.BLACK);
        loginButton.setForeground(Color.WHITE);
        loginButton.addActionListener(this);
        panel.add(loginButton);

        backButton = new JButton("Back");
        backButton.setFont(buttonFont);
        backButton.setBounds(200, 250, 150, 40);
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this);
        panel.add(backButton);
        
        messageLabel = new JLabel("");
        messageLabel.setFont(fieldFont);
        messageLabel.setBounds(50, 200, 300, 30);
        panel.add(messageLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            
            if (authenticate(username, password)) {
                onLoginSuccess();
            } else {
                messageLabel.setText("Invalid login credentials");
                messageLabel.setForeground(Color.RED);
            }
        } else if (e.getSource() == backButton) {
            new Main().setVisible(true);
            this.dispose();
        }
    }

    protected boolean authenticate(String username, String password) {
        // Placeholder for real authentication logic
        return false;
    }

    protected void onLoginSuccess() {
        // Placeholder for actions after successful login
    }

    public static void main(String[] args) {
        new Login();
    }
}

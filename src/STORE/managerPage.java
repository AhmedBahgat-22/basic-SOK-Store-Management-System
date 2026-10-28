package STORE;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class managerPage extends JFrame implements ActionListener {

    private JPanel panel;
    private JButton backButton;
    private JButton employeesButton;
    private JButton stockInfoButton;
    
    public managerPage() {
        this.setTitle("Manager Page");
        this.setSize(400, 400); // Increased height to accommodate the buttons
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initialize();

        this.setVisible(true);
    }

    private void initialize() {
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(255, 214, 0));
        this.add(panel);

        Font buttonFont = new Font("Arial", Font.BOLD, 18);
        Font labelFont = new Font("TIME NEW ROMAN", Font.BOLD, 36);

        JLabel managerLabel = new JLabel("Manager Control");
        managerLabel.setFont(labelFont);
        managerLabel.setBounds(45, 30, 320, 50); // Positioned at the top
        managerLabel.setForeground(Color.BLACK);
        panel.add(managerLabel);

        backButton = new JButton("Back");
        backButton.setBounds(0, 335, 50, 30); // Positioned at the top left corner
        backButton.addActionListener(this);
        backButton.setFont(buttonFont);
        backButton.setBackground(new Color(255, 214, 0));
        backButton.setForeground(Color.BLACK);
        backButton.setBorder(null);
        panel.add(backButton);

        employeesButton = new JButton("Hire Employees");
        employeesButton.setBounds(70, 150, 240, 50); // Positioned at the bottom center
        employeesButton.addActionListener(this);
        employeesButton.setFont(buttonFont);
        employeesButton.setBackground(Color.BLACK);
        employeesButton.setForeground(Color.WHITE);
        panel.add(employeesButton);

        stockInfoButton = new JButton("Stock Information");
        stockInfoButton.setBounds(70, 220, 240, 50); // Positioned at the bottom center
        stockInfoButton.addActionListener(this);
        stockInfoButton.setFont(buttonFont);
        stockInfoButton.setBackground(Color.BLACK);
        stockInfoButton.setForeground(Color.WHITE);
        panel.add(stockInfoButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            new managerLogin().setVisible(true);
            this.dispose();
            
        } else if (e.getSource() == employeesButton) {
            new HiringPage().setVisible(true);;
            this.dispose();
            
        } else if (e.getSource() == stockInfoButton) {
            new Stockreads().setVisible(true);;
            this.dispose();
        }
    }

    public static void main(String[] args) {
        new managerPage();
    }
}

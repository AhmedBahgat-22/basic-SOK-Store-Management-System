package STORE;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class employeersPage extends JFrame implements ActionListener {

    private JPanel panel;
    private JButton backButton;
    private JButton itemsButton;

    public employeersPage() {
        this.setTitle("Employees Control");
        this.setSize(400, 400);
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
        Font labelFont = new Font("TIME NEW ROMAN", Font.BOLD, 32);

        JLabel managerLabel = new JLabel("Employees Control");
        managerLabel.setFont(labelFont);
        managerLabel.setBounds(45, 30, 320, 50);
        managerLabel.setForeground(Color.BLACK);
        panel.add(managerLabel);

        backButton = new JButton("Back");
        backButton.setBounds(0, 335, 50, 30);
        backButton.addActionListener(this);
        backButton.setFont(buttonFont);
        backButton.setBackground(new Color(255, 214, 0));
        backButton.setForeground(Color.BLACK);
        backButton.setBorder(null);
        panel.add(backButton);

        itemsButton = new JButton("ITEMS");
        itemsButton.setBounds(70, 150, 240, 50);
        itemsButton.addActionListener(this);
        itemsButton.setFont(buttonFont);
        itemsButton.setBackground(Color.BLACK);
        itemsButton.setForeground(Color.WHITE);
        panel.add(itemsButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            new employeeLogin().setVisible(true);
            this.dispose();

        } else if (e.getSource() == itemsButton) {
            new stokPPage().setVisible(true);
            this.dispose();
        }
    }

    public static void main(String[] args) {
        new employeersPage();
    }
}


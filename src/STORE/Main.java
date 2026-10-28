package STORE;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main extends JFrame implements ActionListener {
    
    private JPanel panel;
    private JLabel form;
    private JButton employeers;
    private JButton manager;
    
    public Main() {
        this.setTitle("STORE");
        this.setSize(400, 350);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize components
        initialize();

        this.setVisible(true);
    }
    
    private void initialize() {
        // Set up the panel
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(255,214,0));
        panel.setBounds(0, 0, 400, 350);
        this.add(panel);
        
        form = new JLabel("ŞOK STORE SYSTEM");
        form.setForeground(Color.BLACK);
        form.setFont(new Font("SANTIC", Font.BOLD, 30));
        form.setBounds(40, 30, 320, 35);
        panel.add(form);
        
        // Set up the employeers button
        employeers = new JButton("Employeers");
        employeers.setBounds(100, 120, 200, 50);
        panel.add(employeers);
        employeers.setBackground(Color.BLACK);
        employeers.setForeground(Color.WHITE);
        employeers.addActionListener(this);
        employeers.setFont(new Font("SANTIC", Font.BOLD, 18));
        
        // Set up the manager button
        manager = new JButton("Manager");
        manager.setBounds(100, 200, 200, 50);
        panel.add(manager);
        manager.setBackground(Color.BLACK);
        manager.setForeground(Color.WHITE);
        manager.addActionListener(this);
        manager.setFont(new Font("SANTIC", Font.BOLD, 18));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == employeers) {
            new employeeLogin().setVisible(true);
            this.dispose();
        } else if (e.getSource() == manager) {
            new managerLogin().setVisible(true);
            this.dispose();
        }
    }
    
    public static void main(String arg[]) {
        new Main().setVisible(true);
    }
}
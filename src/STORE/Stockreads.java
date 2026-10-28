package STORE;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class Stockreads extends JFrame implements ActionListener {

    private JTable table;
    private DefaultTableModel model;
    private ArrayList<StockItem> stockItems;
    private JButton backButton;

    public Stockreads() {
        this.setTitle("Stock Reader");
        this.setSize(600, 400);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initialize();
        loadStockItemsFromFile();
        updateTable();

        this.setVisible(true);
    }

    private void initialize() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(255, 214, 0));
        this.add(panel);

        model = new DefaultTableModel();
        String[] columns = {"ID", "Name", "Quantity", "Price"};
        model.setColumnIdentifiers(columns);

        table = new JTable();
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Adding the Back button
        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(backButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadStockItemsFromFile() {
        stockItems = new ArrayList<>();
        File file = new File("src" + File.separator + "STORE" + File.separator + "stockItems.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    StockItem stockItem = new StockItem(parts[0], parts[1], Integer.parseInt(parts[2]),
                            Double.parseDouble(parts[3]));
                    stockItems.add(stockItem);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateTable() {
        model.setRowCount(0);
        for (StockItem stockItem : stockItems) {
            Object[] row = {stockItem.getId(), stockItem.getName(), stockItem.getQuantity(),
                    stockItem.getPrice()};
            model.addRow(row);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            this.dispose();
            new managerPage().setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Stockreads());
    }
}

class StockItem {

    private String id;
    private String name;
    private int quantity;
    private double price;

    public StockItem(String id, String name, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
package STORE;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class stokPPage extends JFrame implements ActionListener {

    private JTextField idField, nameField, quantityField, priceField;
    private JButton addButton, deleteButton, searchButton, updateButton, backButton;
    private JTable table;
    private DefaultTableModel model;

    private ArrayList<StockItem> stockItems;

    public stokPPage() {
        this.setTitle("Stock Management");
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
        panel.setLayout(null);
        panel.setBackground(new Color(255, 214, 0));
        this.add(panel);

        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font fieldFont = new Font("Arial", Font.PLAIN, 14);
        Font buttonFont = new Font("Arial", Font.BOLD, 14);

        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(20, 20, 60, 25);
        idLabel.setFont(labelFont);
        panel.add(idLabel);
        idField = new JTextField();
        idField.setBounds(90, 20, 120, 25);
        idField.setFont(fieldFont);
        panel.add(idField);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(220, 20, 60, 25);
        nameLabel.setFont(labelFont);
        panel.add(nameLabel);
        nameField = new JTextField();
        nameField.setBounds(290, 20, 120, 25);
        nameField.setFont(fieldFont);
        panel.add(nameField);

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setBounds(20, 60, 100, 25);
        quantityLabel.setFont(labelFont);
        panel.add(quantityLabel);
        quantityField = new JTextField();
        quantityField.setBounds(120, 60, 90, 25);
        quantityField.setFont(fieldFont);
        panel.add(quantityField);

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(220, 60, 60, 25);
        priceLabel.setFont(labelFont);
        panel.add(priceLabel);
        priceField = new JTextField();
        priceField.setBounds(290, 60, 120, 25);
        priceField.setFont(fieldFont);
        panel.add(priceField);

        addButton = new JButton("Add");
        addButton.setBounds(455, 20, 100, 25);
        addButton.addActionListener(this);
        addButton.setFont(buttonFont);
        addButton.setBackground(Color.BLACK);
        addButton.setForeground(Color.WHITE);
        panel.add(addButton);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(455, 50, 100, 25);
        deleteButton.addActionListener(this);
        deleteButton.setFont(buttonFont);
        deleteButton.setBackground(Color.BLACK);
        deleteButton.setForeground(Color.WHITE);
        panel.add(deleteButton);

        searchButton = new JButton("Search");
        searchButton.setBounds(455, 80, 100, 25);
        searchButton.addActionListener(this);
        searchButton.setFont(buttonFont);
        searchButton.setBackground(Color.BLACK);
        searchButton.setForeground(Color.WHITE);
        panel.add(searchButton);

        updateButton = new JButton("Update");
        updateButton.setBounds(455, 110, 100, 25);
        updateButton.addActionListener(this);
        updateButton.setFont(buttonFont);
        updateButton.setBackground(Color.BLACK);
        updateButton.setForeground(Color.WHITE);
        panel.add(updateButton);

        backButton = new JButton("Back");
        backButton.setBounds(455, 140, 100, 25);
        backButton.addActionListener(this);
        backButton.setFont(buttonFont);
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        panel.add(backButton);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 180, 540, 160);
        panel.add(scrollPane);

        table = new JTable();
        model = new DefaultTableModel();
        String[] columns = {"ID", "Name", "Quantity", "Price"};
        model.setColumnIdentifiers(columns);
        table.setModel(model);
        scrollPane.setViewportView(table);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            addStockItem();
        } else if (e.getSource() == deleteButton) {
            deleteStockItem();
        } else if (e.getSource() == searchButton) {
            searchStockItem();
        } else if (e.getSource() == updateButton) {
            updateStockItem();
        } else if (e.getSource() == backButton) {
            this.dispose();
            new employeersPage().setVisible(true);
        }
    }

    private void addStockItem() {
        try {
            String id = idField.getText();
            String name = nameField.getText();
            int quantity = Integer.parseInt(quantityField.getText());
            double price = Double.parseDouble(priceField.getText());

            StockItem stockItem = new StockItem(id, name, quantity, price);
            stockItems.add(stockItem);

            saveStockItemsToFile();
            updateTable();
            clearFields();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid data", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteStockItem() {
        String idToDelete = idField.getText();
        stockItems.removeIf(stockItem -> stockItem.getId().equals(idToDelete));
        saveStockItemsToFile();
        updateTable();
        clearFields();
    }

    private void searchStockItem() {
        String idToSearch = idField.getText();
        for (StockItem stockItem : stockItems) {
            if (stockItem.getId().equals(idToSearch)) {
                nameField.setText(stockItem.getName());
                quantityField.setText(String.valueOf(stockItem.getQuantity()));
                priceField.setText(String.valueOf(stockItem.getPrice()));
                return;
            }
        }
        clearFields();
    }

    private void updateStockItem() {
        String idToUpdate = idField.getText();
        for (StockItem stockItem : stockItems) {
            if (stockItem.getId().equals(idToUpdate)) {
                stockItem.setName(nameField.getText());
                stockItem.setQuantity(Integer.parseInt(quantityField.getText()));
                stockItem.setPrice(Double.parseDouble(priceField.getText()));
                saveStockItemsToFile();
                updateTable();
                clearFields();
                return;
            }
        }
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        quantityField.setText("");
        priceField.setText("");
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

    private void saveStockItemsToFile() {
        File file = new File("src" + File.separator + "STORE" + File.separator + "stockItems.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (StockItem stockItem : stockItems) {
                writer.write(stockItem.getId() + "," + stockItem.getName() + ","
                        + stockItem.getQuantity() + "," + stockItem.getPrice() + "\n");
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new stokPPage());
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

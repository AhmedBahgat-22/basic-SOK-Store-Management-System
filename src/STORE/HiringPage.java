package STORE;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class HiringPage extends JFrame implements ActionListener {

    private JTextField idField, nameField, ageField, experienceField, positionField;
    private JButton addButton, deleteButton, searchButton, updateButton, backButton;
    private JTable table;
    private DefaultTableModel model;

    private ArrayList<Employee> employees;

    public HiringPage() {
        this.setTitle("Hiring Page");
        this.setSize(600, 400);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initialize();
        loadEmployeesFromFile();
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

        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setBounds(20, 60, 60, 25);
        ageLabel.setFont(labelFont);
        panel.add(ageLabel);
        ageField = new JTextField();
        ageField.setBounds(90, 60, 120, 25);
        ageField.setFont(fieldFont);
        panel.add(ageField);

        JLabel experienceLabel = new JLabel("Experience:");
        experienceLabel.setBounds(220, 60, 100, 25);
        experienceLabel.setFont(labelFont);
        panel.add(experienceLabel);
        experienceField = new JTextField();
        experienceField.setBounds(310, 60, 100, 25);
        experienceField.setFont(fieldFont);
        panel.add(experienceField);

        JLabel positionLabel = new JLabel("Position:");
        positionLabel.setBounds(20, 100, 80, 25);
        positionLabel.setFont(labelFont);
        panel.add(positionLabel);
        positionField = new JTextField();
        positionField.setBounds(90, 100, 120, 25);
        positionField.setFont(fieldFont);
        panel.add(positionField);

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
        String[] columns = {"ID", "Name", "Age", "Experience", "Position"};
        model.setColumnIdentifiers(columns);
        table.setModel(model);
        scrollPane.setViewportView(table);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            addEmployee();
        } else if (e.getSource() == deleteButton) {
            deleteEmployee();
        } else if (e.getSource() == searchButton) {
            searchEmployee();
        } else if (e.getSource() == updateButton) {
            updateEmployee();
        } else if (e.getSource() == backButton) {
            this.dispose();
            new managerPage().setVisible(true);
        }
    }

    private void addEmployee() {
        try {
            String id = idField.getText();
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            int experience = Integer.parseInt(experienceField.getText());
            String position = positionField.getText();

            Employee employee = new Employee(id, name, age, experience, position);

            employees.add(employee);

            saveEmployeesToFile();
            updateTable();
            clearFields();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid data", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteEmployee() {
        String idToDelete = idField.getText();
        employees.removeIf(employee -> employee.getId().equals(idToDelete));
        saveEmployeesToFile();
        updateTable();
        clearFields();
    }

    private void searchEmployee() {
        String idToSearch = idField.getText();
        for (Employee employee : employees) {
            if (employee.getId().equals(idToSearch)) {
                nameField.setText(employee.getName());
                ageField.setText(String.valueOf(employee.getAge()));
                experienceField.setText(String.valueOf(employee.getExperience()));
                positionField.setText(employee.getPosition());
                return;
            }
        }
        clearFields();
    }

    private void updateEmployee() {
        String idToUpdate = idField.getText();
        for (Employee employee : employees) {
            if (employee.getId().equals(idToUpdate)) {
                employee.setName(nameField.getText());
                employee.setAge(Integer.parseInt(ageField.getText()));
                employee.setExperience(Integer.parseInt(experienceField.getText()));
                employee.setPosition(positionField.getText());
                saveEmployeesToFile();
                updateTable();
                clearFields();
                return;
            }
        }
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        ageField.setText("");
        experienceField.setText("");
        positionField.setText("");
    }

    private void loadEmployeesFromFile() {
        employees = new ArrayList<>();
        File file = new File("src\\store\\employees.txt");
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
                if (parts.length == 5) {
                    Employee employee = new Employee(parts[0], parts[1], Integer.parseInt(parts[2]),
                            Integer.parseInt(parts[3]), parts[4]);
                    employees.add(employee);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveEmployeesToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src\\store\\employees.txt"))) {
            for (Employee employee : employees) {
                writer.write(employee.getId() + "," + employee.getName() + ","
                        + employee.getAge() + "," + employee.getExperience() + "," + employee.getPosition() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateTable() {
        model.setRowCount(0);
        for (Employee employee : employees) {
            Object[] row = {employee.getId(), employee.getName(), employee.getAge(),
                employee.getExperience(), employee.getPosition()};
            model.addRow(row);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HiringPage());
    }
}

class Employee {
    private String id;
    private String name;
    private int age;
    private int experience;
    private String position;

    public Employee(String id, String name, int age, int experience, String position) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.experience = experience;
        this.position = position;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getExperience() {
        return experience;
    }

    public String getPosition() {
        return position;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}

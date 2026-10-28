# ŞOK Store Management System

A Java desktop application for managing supermarket employees and inventory. Built with **Java Swing**, the project provides separate interfaces for managers and employees, with simple text-file storage.

## Features

### Manager
- Sign in through the manager login screen.
- Add, search, update, and delete employee records.
- View inventory information, including product IDs, names, quantities, and prices.

### Employee
- Sign in through the employee login screen.
- Add, search, update, and delete inventory items.
- View products in a table.

## Technologies

- **Java 17** (project source and target level)
- **Java Swing** for the graphical user interface
- **Apache NetBeans** (Ant-based Java project)
- **Object-oriented programming** and `ArrayList`
- **Text files** for basic local data storage

## Getting Started

### Requirements

- JDK 17 or a compatible newer JDK
- Apache NetBeans IDE with Java support

### Run in NetBeans

1. Clone or download this repository.
2. Open **Apache NetBeans**.
3. Select **File → Open Project**, then choose the folder containing `build.xml` and `nbproject/`.
4. Run the project using **F6**. Its configured main class is `STORE.Main`.
5. If necessary, right-click `src/STORE/Main.java` and select **Run File** (Shift + F6).
6. Choose **Manager** or **Employeers** on the home screen and sign in.

### Demo Login Credentials

The current version uses hard-coded credentials **for demonstration only**:

| Role | Username | Password |
| --- | --- | --- |
| Manager | `manager` | `password` |
| Employee | `employee` | `password` |

> **Security note:** These are not secure production credentials. The project does not implement a real authentication backend. Do not reuse these passwords in real systems.

## Project Structure

```text
.
├── src/
│   └── STORE/
│       ├── Main.java               # Home screen
│       ├── Login.java              # Shared login UI
│       ├── managerLogin.java       # Manager login
│       ├── employeeLogin.java      # Employee login
│       ├── managerPage.java        # Manager dashboard
│       ├── employeersPage.java     # Employee dashboard
│       ├── HiringPage.java         # Employee record management
│       ├── Stockreads.java         # Inventory viewer
│       ├── stokPPage.java          # Inventory management
│       ├── employees.txt           # Sample employee data
│       └── stockItems.txt          # Sample inventory data
├── nbproject/                     # NetBeans project configuration
├── build.xml                      # Ant build configuration
├── manifest.mf
├── project report.docx
└── UML diagram.pdf
```

## Data Storage

The application reads and writes local text files, including `src/STORE/employees.txt` and `src/STORE/stockItems.txt`. It expects to run from the project root directory. Changes made through the application may modify these files, so consider backing up the sample data before testing.

## Limitations and Possible Improvements

This is an educational prototype, not a production-ready point-of-sale system.

- Replace hard-coded credentials with secure authentication and password hashing.
- Move employee and inventory data to a database (for example, MySQL or SQLite).
- Add input validation and clearer error handling.
- Improve the UI and accessibility.
- Add sales, invoices, and reporting features.

## Documentation

- `project report.docx` — project report
- `UML diagram.pdf` — UML design diagram

## Author

**Ahmed Abdelmoula**

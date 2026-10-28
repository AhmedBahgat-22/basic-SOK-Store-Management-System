package STORE;

public class employeeLogin extends Login {

    public employeeLogin() {
        super();
        this.setTitle("Employee Login");
    }

    @Override
    protected boolean authenticate(String username, String password) {
        // Placeholder for real employee authentication logic
        return "employee".equals(username) && "password".equals(password);
    }

    @Override
    protected void onLoginSuccess() {
        new employeersPage().setVisible(true);
        this.dispose();
    }

    public static void main(String[] args) {
        new employeeLogin();
    }
}


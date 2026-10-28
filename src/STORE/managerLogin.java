package STORE;

public class managerLogin extends Login {

    public managerLogin() {
        super();
        this.setTitle("Manager Login");
    }

    @Override
    protected boolean authenticate(String username, String password) {
        // Placeholder for real manager authentication logic
        return "manager".equals(username) && "password".equals(password);
    }

    @Override
    protected void onLoginSuccess() {
        new managerPage().setVisible(true);
        this.dispose();
    }

    public static void main(String[] args) {
        new managerLogin();
    }
}

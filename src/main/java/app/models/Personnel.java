package app.models;

public class Personnel extends Account{

    private String status;

    public Personnel(String name, String userName, String password){
        super(name, userName, password, "personnel");
        this.status = "Active";
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Name = " + super.getName() +
                "\nUsername = " + super.getUserName() +
                "\nPassword = " + super.getPassword() +
                "\nStatus = " + status;
    }
}

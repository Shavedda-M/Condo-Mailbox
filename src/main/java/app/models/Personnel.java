package app.models;

public class Personnel extends Account implements Accounts {

    private String status;

    public Personnel(String name, String userName, String password){
        super(name, userName, password, "personnel");
        this.status = "Active";
    }

    @Override
    public boolean checkAccount(String id, String password) {
        if(id.equals(getUserName()) && password.equals(getPassword()) && getStatus().equals("Active")){
            return true;
        }
        return  false;

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

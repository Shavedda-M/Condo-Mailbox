package app.models;

public class Personnel extends Account implements Accounts {

    private String status;
    private int tryLogin;

    public Personnel(String name, String userName, String password, String status, int tryLogin){
        super(name, userName, password, "personnel");
        this.status = status;
        this.tryLogin = tryLogin;
    }

    public Personnel(String name, String userName, String password){
        super(name, userName, password, "personnel");
        this.status = "Activated";
        this.tryLogin = 0;
    }

    @Override
    public boolean checkAccount(String id, String password) {
        if(id.equals(getUserName()) && password.equals(getPassword()) && getStatus().equals("Activated")){
            return true;
        }else if(id.equals(getUserName()) && password.equals(getPassword()) && getStatus().equals("Banned")){
            tryLogin++;
            return false;
        }
        return  false;

    }

    public String getStatus() {
        return status;
    }

    public void changeStatus(){
        if(status.equals("Activated")){
            this.status = "Banned";
        }else if(status.equals("Banned")){
            this.status = "Activated";
        }
    }

    public int getTryLogin() {
        return tryLogin;
    }

    @Override
    public String toString() {
        return  "Name = " + super.getName() + ", " +
                "Username = " + super.getUserName() + ", " +
                "Password = " + super.getPassword() + ", " +
                "Status = " + status + "\n";
    }


}

package app.models;

public class Account {
    private String name;
    private String id;
    private String password;
    private String accountType;

    public Account(String name, String id, String password, String accountType){
        this.name = name;
        this.id = id;
        this.password = password;
        this.accountType = accountType;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "name = " + name + "\nid = " + id + "\npassword = " + password;
    }
}

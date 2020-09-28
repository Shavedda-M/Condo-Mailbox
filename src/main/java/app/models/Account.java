package app.models;

public class Account implements Accounts {

    private String userName;
    private String name;
    private String password;
    private String accountType;

    public Account(String name, String userName, String password, String accountType){
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.accountType = accountType;
    }

    @Override
    public boolean checkAccount(String id, String password) {
        if(id.equals(getUserName()) && password.equals(getPassword())){
            return true;
        }
        return  false;

    }

    public String getName() {
        return name;
    }

    public String getUserName() {
        return userName;
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
        return "name = " + name +
                "\nUsername = " + userName +
                "\npassword = " + password;
    }


}

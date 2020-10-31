package app.models;

import app.exceptions.BannedAccountException;

public class Account implements Accounts {

    private String userName;
    private String name;
    private String password;
    private String accountType;
    private String imageFileName;

    public Account(String name, String userName, String password, String accountType, String imageFileName){
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.accountType = accountType;
        this.imageFileName = imageFileName;
    }

    @Override
    public boolean checkAccount(String userName, String password) throws BannedAccountException {
        if(userName.equals(getUserName()) && password.equals(getPassword())){
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

    public String getImageFileName() {
        return imageFileName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    @Override
    public String toString() {
        return  "Name = " + name + ", " +
                "Username = " + userName + ", " +
                "password = " + password + "\n";
    }


}

package app.models;

import app.exceptions.BannedAccountException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Personnel extends Account implements Accounts {

    private String status;
    private Date lastLoginTime;
    private int tryLogin;


    public Personnel(String name, String userName, String password, String status, Date lastLoginTime, int tryLogin, String imageFileName){
        super(name, userName, password, "personnel", imageFileName);
        this.status = status;
        this.lastLoginTime = lastLoginTime;
        this.tryLogin = tryLogin;
    }

    public Personnel(String name, String userName, String password, String imageFileName){
        super(name, userName, password, "personnel", imageFileName);
        this.status = "Activated";
        this.lastLoginTime = new Date();
        this.tryLogin = 0;
    }

    @Override
    public boolean checkAccount(String id, String password) throws BannedAccountException {
        if(id.equals(getUserName()) && password.equals(getPassword()) && getStatus().equals("Activated")){
            return true;
        }else if(id.equals(getUserName()) && password.equals(getPassword()) && getStatus().equals("Banned")){
            tryLogin++;
            throw new BannedAccountException();
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

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public int getTryLogin() {
        return tryLogin;
    }

    @Override
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return  "Name = " + super.getName() + ", " +
                "Username = " + super.getUserName() + ", " +
                "Password = " + super.getPassword() + ", " +
                "Status = " + status + ", " +
                "LastLogin Time = " + dateFormat.format(lastLoginTime) + ", " +
                "TryLogin = " + tryLogin + "\n";
    }


}

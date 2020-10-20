package app.models;

import app.exceptions.BannedAccountException;
import app.exceptions.NoAccountFoundException;
import app.exceptions.UsernameNotAvailableException;

import java.util.ArrayList;

public class AccountList {

    private ArrayList<Account> accounts;
    private Account currentAccount;

    public AccountList(){
        accounts = new ArrayList<>();
    }

    public boolean addAccount(Account acc){
        for(Account a : accounts){
            if(a.getUserName().equals(acc.getUserName())){
                return false;
            }
        }
        accounts.add(acc);
        return true;
    }

    public boolean checkUsernameAvaliable(String username) throws UsernameNotAvailableException {
        for(Account a : accounts){
            if(a.getUserName().equals(username)){
                throw new UsernameNotAvailableException();
            }
        }
        return true;
    }

    public boolean checkAccount(String username, String password) throws NoAccountFoundException, BannedAccountException {
        for(Account a : accounts){
            if(a.checkAccount(username, password)){
                currentAccount = a;
                return true;
            }
        }
        currentAccount = null;
        throw new NoAccountFoundException();
    }

    public ArrayList<Personnel> getPersonnelAccountList(){
        ArrayList<Personnel> perList = new ArrayList<>();
        for(Account a : accounts){
            if(a.getAccountType().equals("personnel")){
                Personnel per = (Personnel)a;
                perList.add(per);
            }
        }
        return perList;
    }

    public Account getCurrentAccount() {
        return currentAccount;
    }

    public ArrayList<Account> toList(){
        return accounts;
    }

    @Override
    public String toString() {
        return accounts.toString();
    }

}

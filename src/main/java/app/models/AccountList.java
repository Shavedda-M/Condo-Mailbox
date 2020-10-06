package app.models;

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

    public boolean checkAccount(String id, String password){
        for(Account a : accounts){
            if(a.checkAccount(id, password)){
                currentAccount = a;
                return true;
            }
        }
        currentAccount = null;
        return false;
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

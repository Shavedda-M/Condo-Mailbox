package app.models;

import java.util.ArrayList;

public class AccountList {
    private ArrayList<Account> accounts;
    private Account currentAccount;

    public AccountList(){
        accounts = new ArrayList<>();
    }

    public boolean addAccount(Account acc){
        for(int i = 0; i < accounts.size(); i++){
            if(accounts.get(i).getId().equals(acc.getId())){
                return false;
            }
        }
        accounts.add(acc);
        return true;
    }

    public boolean checkAccount(String id, String password){
        for(int i = 0; i < accounts.size(); i++){
            if(accounts.get(i).getId().equals(id) && accounts.get(i).getPassword().equals(password)){
                currentAccount = accounts.get(i);
                return true;
            }
        }
        currentAccount = null;
        return false;
    }

    public Account getCurrentAccount() {
        return currentAccount;
    }

    @Override
    public String toString() {
        return accounts.toString();
    }
}

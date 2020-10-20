package app.models;

import app.exceptions.BannedAccountException;

public interface Accounts {
    boolean checkAccount(String id, String password) throws BannedAccountException;
}

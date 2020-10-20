package app.exceptions;

public class BannedAccountException extends Exception{

    public BannedAccountException(){
        super("This account has been banned");
    }

    public BannedAccountException(String message){
        super(message);
    }
}

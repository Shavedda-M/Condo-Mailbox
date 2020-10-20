package app.exceptions;

public class UsernameNotAvailableException extends Exception{

    public UsernameNotAvailableException(){
        super("This username has been used");
    }

    public UsernameNotAvailableException(String message){
        super(message);
    }
}

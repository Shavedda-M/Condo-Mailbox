package app.exceptions;

public class NoAccountFoundException extends Exception {

    public NoAccountFoundException(){
        super("Wrong Username or Password");
    }

    public NoAccountFoundException(String message){
        super(message);
    }
}

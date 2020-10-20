package app.exceptions;

public class InvalidRoomNumberException extends Exception{

    public InvalidRoomNumberException(){
        super("Invalid room number");
    }

    public InvalidRoomNumberException(String message){
        super(message);
    }
}

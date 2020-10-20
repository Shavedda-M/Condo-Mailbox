package app.exceptions;

public class RoomFullException extends Exception{

    public RoomFullException(){
        super("This room is full");
    }

    public RoomFullException(String message){
        super(message);
    }
}

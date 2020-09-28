package app.models;

public class Item implements Items{

    private Room room;
    private String senderName;
    private double size;

    public Item(Room room, String senderName, double size) {
        this.room = room;
        this.senderName = senderName;
        this.size = size;
    }

    @Override
    public boolean checkItem(Room room, String senderName) {
        if((getRoom().equals(room)) && (getSenderName().equals(senderName))){
            return true;
        }
        return false;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }
}

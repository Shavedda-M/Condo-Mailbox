package app.models;

public class Guest extends Account implements Accounts {

    private Room room;

    public Guest(String name, String userName, String password, Room room, String imageFileName){
        super(name, userName, password, "guest", imageFileName);
        this.room = room;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return  "Name = " + super.getName() + " | " +
                "Username = " + super.getUserName() + " | " +
                "Password = " + super.getPassword() + " | " +
                "Room = " + room.getRoomNumber() + "\n";
    }


}

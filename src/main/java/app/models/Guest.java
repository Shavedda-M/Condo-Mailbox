package app.models;

public class Guest extends Account implements Accounts {

    private Room room;

    public Guest(String name, String id, String password, Room room){
        super(name, id, password, "guest");
        this.room = room;
    }

    @Override
    public boolean checkAccount(String id, String password) {
        if(id.equals(getUserName()) && password.equals(getPassword())){
            return true;
        }
        return  false;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "name = " + super.getName() +
                "\nRoom = " + room.getRoomNumber();
    }


}

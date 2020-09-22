package app.models;

import java.util.ArrayList;

public class RoomList {

    private ArrayList<Room> rooms;

    public RoomList() {
        rooms = new ArrayList<>();
    }

    public boolean addRoom(Room room){
        for(int i = 0; i < rooms.size(); i++){
            if(rooms.get(i).getRoomNumber().equals(room.getRoomNumber())){
                return false;
            }
        }
        rooms.add(room);
        return true;
    }

    public Room findRoom(String name, String roomNumber){
        for(Room r : rooms){
            if(r.findGuest(name) && r.getRoomNumber().equals(roomNumber)){
                return r;
            }
        }
        return null;
    }

    public ArrayList<Room> toList(){
        return  rooms;
    }

    @Override
    public String toString() {
        return rooms.toString();
    }
}

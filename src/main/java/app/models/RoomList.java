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

    public boolean checkRoomAvailable(String building, String roomNumber){
        for(Room r : rooms){
            if(r.getRoomNumber().equals(roomNumber) && r.getBuilding().equals(building)){
                return false;
            }
        }
        return true;
    }

    public boolean checkRoom(String building, String roomNumber){
        for(Room r : rooms){
            if(r.getRoomNumber().equals(roomNumber) && r.getBuilding().equals(building)){
                return true;
            }
        }
        return false;
    }

    public boolean checkRoom(String name,String building, String roomNumber){
        for(Room r : rooms){
            if(r.findGuest(name) && r.getRoomNumber().equals(roomNumber) && r.getBuilding().equals(building)){
                return true;
            }
        }
        return false;
    }

    public Room findRoom(String building, String roomNumber){
        for(Room r : rooms){
            if(r.getRoomNumber().equals(roomNumber) && r.getBuilding().equals(building)){
                return r;
            }
        }
        return null;
    }

    public Room findRoom(String name,String building, String roomNumber){
        for(Room r : rooms){
            if(r.findGuest(name) && r.getRoomNumber().equals(roomNumber) && r.getBuilding().equals(building)){
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

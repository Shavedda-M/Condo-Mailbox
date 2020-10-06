package app.models;

import java.util.ArrayList;

public class Room {

    private String building;
    private String floor;
    private String roomNumber;
    private String roomType;
    private int capacity;
    private ArrayList<String> guestNameList;

    public Room(String building, String floor, String roomNumber, String roomType) {
        this.building = building;
        this.floor = floor;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        if(roomType.equals("Single")){
            this.capacity = 1;
        }else if(roomType.equals("Double")){
            this.capacity = 2;
        }
        guestNameList = new ArrayList<>();
    }

    public Boolean findGuest(String guestName){
        for(String n : guestNameList){
            if(n.equals(guestName)){
                return true;
            }
        }
        return false;
    }

    public boolean addGuest(String guestName){
        if(guestNameList.size() >= capacity){
            return false;
        }
        guestNameList.add(guestName);
        return true;
    }

    public String getBuilding() {
        return building;
    }

    public String getFloor() {
        return floor;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    @Override
    public String toString() {
        return "Guest List = " + guestNameList +
                " Building = " + building +
                " Floor = " + floor +
                " Room Number = " + roomNumber +
                " Room Type = " + roomType + "\n";
    }
}

package app.models;

import java.util.ArrayList;

public class Room {

    private String building;
    private String floor;
    private String roomNumber;
    private String roomType;
    private ArrayList<Guest> guestList;
    private int capacity;

    public Room(String building, String floor, String roomNumber, String roomType) {
        this.building = building;
        this.floor = floor;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        guestList = new ArrayList<>();
    }

    public Boolean findGuest(String name){
        for(Guest g : guestList){
            if(g.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    public boolean addGuest(Guest guest){
        if(guestList.size() >= capacity){
            return false;
        }
        guestList.add(guest);
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
        return "Guest List = " + guestList +
                "\nBuilding = " + building +
                "\nFloor = " + floor +
                "\nRoom Number = " + roomNumber +
                "\nRoom Type = " + roomType;
    }
}

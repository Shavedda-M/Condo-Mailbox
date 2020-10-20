package app.models;

import app.exceptions.RoomFullException;

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
        guestNameList = new ArrayList<String>(){
            @Override
            public String toString(){
                String line = "";
                for(int i = 0; i < guestNameList.size(); i++){
                    line += guestNameList.get(i);
                    if(i < guestNameList.size() - 1) {
                        line += ", ";
                    }
                }
                return line;
            }
        };
    }

    public Boolean findGuest(String guestName){
        for(String n : guestNameList){
            if(n.equals(guestName)){
                return true;
            }
        }
        return false;
    }

    public boolean checkAvailable(String guestName) throws RoomFullException {
        if(guestNameList.size() >= capacity){
            throw new RoomFullException();
        }
        addGuest(guestName);
        return true;
    }

    public void addGuest(String guestName){
        guestNameList.add(guestName);
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

    public ArrayList<String> getGuestNameList() {
        return guestNameList;
    }

    public String getGuestName() {
        String line = "";
        for(int i = 0; i < guestNameList.size(); i++){
            if(i == guestNameList.size() - 1){
                line += guestNameList.get(i);
            }else{
                line += guestNameList.get(i);
                line += ",";
            }
        }
        return line;
    }

    @Override
    public String toString() {
        return roomNumber;
    }
}

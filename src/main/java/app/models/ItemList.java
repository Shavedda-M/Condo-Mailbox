package app.models;

import java.util.ArrayList;

public class ItemList {

    private ArrayList<Item> items;

    public ItemList(){
        items = new ArrayList<>();
    }

    public ArrayList<Item> getReceivedItemList(){
        ArrayList<Item> receivedItem = new ArrayList<>();
        for(Item i : items){
            if(i.getStatus().equals("Received")){
                receivedItem.add(i);
            }
        }
        return receivedItem;
    }

    public ArrayList<Item> getNotReceivedItemList(){
        ArrayList<Item> notReceivedItem = new ArrayList<>();
        for(Item i : items){
            if(i.getStatus().equals("Not Received")){
                notReceivedItem.add(i);
            }
        }
        return notReceivedItem;
    }

    public ArrayList<Item> getReceivedSearchItemList(String roomNumber){
        ArrayList<Item> receivedItem = new ArrayList<>();
        for(Item i : items){
            if(i.getRoom().getRoomNumber().equals(roomNumber) && i.getStatus().equals("Received")){
                receivedItem.add(i);
            }
        }
        return receivedItem;
    }

    public ArrayList<Item> getNotReceivedSearchItemList(String roomNumber){
        ArrayList<Item> notReceivedItem = new ArrayList<>();
        for(Item i : items){
            if(i.getRoom().getRoomNumber().equals(roomNumber) && i.getStatus().equals("Not Received")){
                notReceivedItem.add(i);
            }
        }
        return notReceivedItem;
    }

    public ArrayList<Item> getGuestReceivedItemList(Guest guest){
        ArrayList<Item> guestReceivedItem = new ArrayList<>();
        for(Item i : items){
            if(i.checkGuest(guest) && i.getStatus().equals("Received")){
                guestReceivedItem.add(i);
            }
        }
        return guestReceivedItem;
    }

    public ArrayList<Item> getGuestNotReceivedItemList(Guest guest){
        ArrayList<Item> guestNotReceivedItem = new ArrayList<>();
        for(Item i : items){
            if(i.checkGuest(guest) && i.getStatus().equals("Not Received")){
                guestNotReceivedItem.add(i);
            }
        }
        return guestNotReceivedItem;
    }

    public void addItem(Item item){
        items.add(item);
    }

    public ArrayList<Item> toList(){
        return items;
    }

    @Override
    public String toString() {
        return items.toString();
    }
}

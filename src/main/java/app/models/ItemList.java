package app.models;

import java.util.ArrayList;

public class ItemList {

    private ArrayList<Item> items;
    private Item currentItem;

    public ItemList(){
        items = new ArrayList<>();
    }

    public void addItem(){

    }

    public void checkItem(){

    }

    public Item getCurrentItem() {
        return currentItem;
    }

    public ArrayList<Item> toList(){
        return items;
    }

    @Override
    public String toString() {
        return items.toString();
    }
}

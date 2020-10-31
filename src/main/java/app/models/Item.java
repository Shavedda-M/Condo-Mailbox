package app.models;

import java.util.Date;

public class Item{

    private String itemType;
    private String recipient;
    private Room room;
    private String senderName;
    private String size;
    private String receivingPersonnel;
    private Date dateReceived;
    private String status;
    private Date pickupDate;
    private String imageFileName;

    // With Image
    public Item(String itemType, String recipient, Room room, String senderName, String size, String receivingPersonnel, Date dateReceived, String status, String imageFileName) {
        this.itemType = itemType;
        this.recipient = recipient;
        this.room = room;
        this.senderName = senderName;
        this.size = size;
        this.receivingPersonnel = receivingPersonnel;
        this.dateReceived = dateReceived;
        this.status = status;
        this.imageFileName = imageFileName;
    }

    public Item(String itemType, String recipient, Room room, String senderName, String size, String receivingPersonnel, Date dateReceived, String status, Date pickupDate, String imageFileName) {
        this.itemType = itemType;
        this.recipient = recipient;
        this.room = room;
        this.senderName = senderName;
        this.size = size;
        this.receivingPersonnel = receivingPersonnel;
        this.dateReceived = dateReceived;
        this.status = status;
        this.pickupDate = pickupDate;
        this.imageFileName = imageFileName;
    }
    //

    public Item(String itemType, String recipient, Room room, String senderName, String size, String receivingPersonnel, Date dateReceived, String imageFileName) {
        this.itemType = itemType;
        this.recipient = recipient;
        this.room = room;
        this.senderName = senderName;
        this.size = size;
        this.receivingPersonnel = receivingPersonnel;
        this.dateReceived = dateReceived;
        this.status = "Not Received";
        this.imageFileName = imageFileName;
    }

    public boolean checkGuest(Guest guest){
        if(recipient.equals(guest.getName()) && room.equals(guest.getRoom())){
            return true;
        }
        return false;
    }

    public void changeStatus(){
        if(status.equals("Not Received")){
            this.status = "Received";
        }
    }

    public String getRecipient() {
        return recipient;
    }

    public Date getDateReceived() {
        return dateReceived;
    }

    public Room getRoom() {
        return room;
    }

    public void setDateReceived(Date dateReceived) {
        this.dateReceived = dateReceived;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getItemType() {
        return itemType;
    }

    public String getSize() {
        return size;
    }

    public String getStatus() {
        return status;
    }

    public Date getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(Date pickupDate) {
        this.pickupDate = pickupDate;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getReceivingPersonnel() {
        return receivingPersonnel;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    @Override
    public String toString() {
        return  "ItemType = " + itemType + " | " +
                "Recipient = " + recipient + " | " +
                "Room =  " + room.getRoomNumber() + " | " +
                "SenderName = " + senderName + " | " +
                "Size = " + size + " | " +
                "DateReceived = " + dateReceived + " | " +
                "Status = " + status + "\n";
    }
}

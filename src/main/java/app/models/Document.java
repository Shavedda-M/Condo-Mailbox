package app.models;


import java.util.Date;

public class Document extends Item{

    private String priority;

    public Document(String itemType, String recipient, Room room, String senderName, String size, String receivingPersonnel, Date receiveDate, String status, String priority) {
        super(itemType, recipient, room, senderName, size, receivingPersonnel, receiveDate, status);
        this.priority = priority;
    }

    public Document(String itemType, String recipient, Room room, String senderName, String size, String receivingPersonnel, Date receiveDate, String status, String priority, Date pickupDate) {
        super(itemType, recipient, room, senderName, size, receivingPersonnel, receiveDate, status, pickupDate);
        this.priority = priority;
    }

    public Document(String itemType, String recipient, Room room, String senderName, String size, String receivingPersonnel, Date receiveDate, String priority) {
        super(itemType, recipient, room, senderName, size, receivingPersonnel, receiveDate);
        this.priority = priority;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return  "ItemType = " + super.getItemType() + " | " +
                "Recipient = " + super.getRecipient() + " | " +
                "Room =  " + super.getRoom().getRoomNumber() + " | " +
                "SenderName = " + super.getSenderName() + " | " +
                "Size = " + super.getSize() + " | " +
                "DateReceived = " + super.getDateReceived() + " | " +
                "Priority = " + priority + " | " +
                "Status = " + super.getStatus() + "\n";
    }
}

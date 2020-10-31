package app.models;

import java.util.Date;

public class Parcel extends Item{

    private String serviceName;
    private String trackingNumber;

    public Parcel(String itemType, String recipient, Room room, String senderName, String size, String receivingPersonnel, Date receiveDate, String status, String serviceName, String trackingNumber, String imageFileName) {
        super(itemType, recipient, room, senderName, size, receivingPersonnel, receiveDate, status, imageFileName);
        this.serviceName = serviceName;
        this.trackingNumber = trackingNumber;
    }

    public Parcel(String itemType, String recipient, Room room, String senderName, String size, String receivingPersonnel, Date receiveDate, String status, String serviceName, String trackingNumber, Date pickupDate, String imageFileName) {
        super(itemType, recipient, room, senderName, size, receivingPersonnel, receiveDate, status, pickupDate, imageFileName);
        this.serviceName = serviceName;
        this.trackingNumber = trackingNumber;
    }

    public Parcel(String itemType, String recipient, Room room, String senderName, String size, String receivingPersonnel, Date receiveDate, String serviceName, String trackingNumber, String imageFileName) {
        super(itemType, recipient, room, senderName, size, receivingPersonnel, receiveDate, imageFileName);
        this.serviceName = serviceName;
        this.trackingNumber = trackingNumber;
    }

    public boolean checkTrackingNumber(String trackingNumber){
        if(trackingNumber.equals(getTrackingNumber())){
            return true;
        }
        return false;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    @Override
    public String toString() {
        return  "ItemType = " + super.getItemType() + " | " +
                "Recipient = " + super.getRecipient() + " | " +
                "Room =  " + super.getRoom().getRoomNumber() + " | " +
                "SenderName = " + super.getSenderName() + " | " +
                "Size = " + super.getSize() + " | " +
                "DateReceived = " + super.getDateReceived() + " | " +
                "Service Name = " + serviceName + " | " +
                "Tracking Numebr = " + trackingNumber + " | " +
                "Status = " + super.getStatus() + "\n";
    }
}

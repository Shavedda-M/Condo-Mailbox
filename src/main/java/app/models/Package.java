package app.models;

public class Package extends Item implements Items{

    private String serviceName;
    private String trackingNumber;

    public Package(Room room, String senderName, double size, String serviceName, String trackingNumber) {
        super(room, senderName, size);
        this.serviceName = serviceName;
        this.trackingNumber = trackingNumber;
    }

    @Override
    public boolean checkItem(Room room, String senderName) {
        if((getRoom().equals(room)) && (getSenderName().equals(senderName))){
            return true;
        }
        return false;
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
}

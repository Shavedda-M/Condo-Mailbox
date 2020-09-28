package app.models;

public class Document extends Item implements Items{

    private String priority;

    public Document(Room room, String senderName, double size, String priority) {
        super(room, senderName, size);
        this.priority = priority;
    }

    @Override
    public boolean checkItem(Room room, String senderName) {
        if((getRoom().equals(room)) && (getSenderName().equals(senderName))){
            return true;
        }
        return false;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}

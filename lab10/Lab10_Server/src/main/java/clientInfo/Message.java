package clientInfo;

public class Message {

    private String nameSender;
    private String message;
    private boolean read;

    public Message(String nameSender, String message){
        this.nameSender = nameSender;
        this.message = message;
        read = false;
    }

    @Override
    public String toString() {
        return "From: " + nameSender + "    Mesage:" + message + "      ";
    }

    public String forSaving(){
        return nameSender + ":" + message;
    }

    public boolean getRead() {
        return read;
    }

    public void setRead() {
        this.read = true;
    }
}

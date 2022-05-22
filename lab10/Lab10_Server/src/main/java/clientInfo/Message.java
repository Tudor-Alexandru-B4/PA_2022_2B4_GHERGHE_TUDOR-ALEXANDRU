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
        return "From: " + nameSender + "\n    Message: " + message + "\n";
    }

    public String forSaving(){
        return nameSender + ":" + message + ":" + read;
    }

    public boolean getRead() {
        return read;
    }

    public void setRead() {
        this.read = true;
    }
}

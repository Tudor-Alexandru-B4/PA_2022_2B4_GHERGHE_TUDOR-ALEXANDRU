package clientInfo;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.*;

public class MailBox {

    private List<Message> messages = new ArrayList<>();

    public void receiveMessage(Message message){
        messages.add(message);
    }

    public String readMessages(boolean readAll, boolean deleteRead){
        String toSend = "Messages:\n";
        for(Message it : messages){
            if(!it.getRead() || readAll) {
                toSend += it;
                it.setRead();
            }
        }
        if(deleteRead){
            deleteReadMessages();
        }
        if(toSend.equals("Messages:\n"))
            toSend = "WoW sUcH eMpTy ^_^";
        return toSend;
    }

    public void deleteReadMessages(){
        List<Message> tempMsg = new ArrayList<>();
        for(int i = 0; i < messages.size(); i++){
            if(!messages.get(i).getRead()){
                tempMsg.add(messages.get(i));
            }
        }
        messages = tempMsg;
    }

    public List<Message> getMessages(){
        return messages;
    }

}

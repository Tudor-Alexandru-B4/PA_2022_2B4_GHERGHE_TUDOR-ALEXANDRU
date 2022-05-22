package Commands;

import clientInfo.Client;
import clientInfo.ClientDataBase;
import clientInfo.Message;
import server.ClientSocket;

public class Send {

    public static String executeCommand(ClientSocket clientSocket, String command){
        Client client = ClientDataBase.findByName(clientSocket.getLoginAs());
        String data = command.substring(5,command.length());
        for(Client it : client.getFriends()){
            it.getMailBox().receiveMessage(new Message(client.getName(), data));
        }
        return "Message send successfully!";
    }

}

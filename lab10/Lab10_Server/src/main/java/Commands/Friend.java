package Commands;

import clientInfo.Client;
import clientInfo.ClientDataBase;
import server.ClientSocket;

public class Friend {

    public static String executeCommand(ClientSocket clientSocket, String command){
        String[] friends = command.split(" ");
        String friended = "";
        Client client = ClientDataBase.findByName(clientSocket.getLoginAs());
        for(int i = 1; i < friends.length; i++){
            Client friend = ClientDataBase.findByName(friends[i]);
            if(friend != null && !client.isFriend(friend) && !client.getName().equals(friend.getName())){
                friended += friends[i] + ", ";
                client.AddFriend(friend);
                friend.AddFriend(client);
            }
        }

        if(friended.length() > 0){
            friended = friended.substring(0, friended.length() - 2);
            return "You are now friends with: " + friended;
        }
        return "None of the friend requests worked";
    }

}

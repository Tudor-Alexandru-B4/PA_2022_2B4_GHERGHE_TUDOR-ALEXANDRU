package Commands;

import clientInfo.ClientDataBase;
import server.ClientSocket;

public class Exit implements Command{

    public static String executeCommand(ClientSocket clientSocket, String command){
        String name = clientSocket.getLoginAs();
        if(name != null){
            ClientDataBase.findByName(clientSocket.getLoginAs()).setLoggedIn(false);
            return "Goodbye " + clientSocket.getLoginAs() + "!";
        }else
            return"Goodbye ";
    }

}

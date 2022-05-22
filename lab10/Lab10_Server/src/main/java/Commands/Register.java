package Commands;

import clientInfo.Client;
import clientInfo.ClientDataBase;
import server.ClientSocket;

public class Register {

    public static String executeCommand(ClientSocket clientSocket, String command){
        String[] comm = command.split(" ");
        if(ClientDataBase.findByName(comm[1]) == null){
            ClientDataBase.addClient(new Client(comm[1]));
            ClientDataBase.findByName(comm[1]).setLoggedIn(true);
            if(clientSocket.getLoginAs() != null)
                ClientDataBase.findByName(clientSocket.getLoginAs()).setLoggedIn(false);
            clientSocket.setLoginAs(comm[1]);
            return "Registered: '" + comm[1] + "' successfully!";
        }
        return "Name: '" + comm[1] +"' already taken!";
    }

}

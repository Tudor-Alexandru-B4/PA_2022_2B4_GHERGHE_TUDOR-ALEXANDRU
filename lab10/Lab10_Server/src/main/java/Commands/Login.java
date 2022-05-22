package Commands;

import clientInfo.ClientDataBase;
import server.ClientSocket;

public class Login {

    public static String executeCommand(ClientSocket clientSocket, String command){
        String[] comm = command.split(" ");
        if(clientSocket.getLoginAs() != null && clientSocket.getLoginAs().equals(comm[1])){
            return "Already logged-in as: '" + comm[1] + "'!";
        }
        if(ClientDataBase.findByName(comm[1]) != null){
            if(ClientDataBase.findByName(comm[1]).isLoggedIn()){
                return "Account: '" + comm[1] + "' already online!";
            }

            ClientDataBase.findByName(comm[1]).setLoggedIn(true);
            if(clientSocket.getLoginAs() != null){
                ClientDataBase.findByName(clientSocket.getLoginAs()).setLoggedIn(false);
            }
            clientSocket.setLoginAs(comm[1]);
            return "Welcome " + comm[1] + "!";
        }
        if(ClientDataBase.findByName(comm[1]) != null){
            ClientDataBase.findByName(comm[1]).setLoggedIn(true);
            ClientDataBase.findByName(clientSocket.getLoginAs()).setLoggedIn(false);
            clientSocket.setLoginAs(comm[1]);
            return "Welcome " + comm[1] + "!";
        }
        return "No user: '" + comm[1] + "' found!";
    }

}

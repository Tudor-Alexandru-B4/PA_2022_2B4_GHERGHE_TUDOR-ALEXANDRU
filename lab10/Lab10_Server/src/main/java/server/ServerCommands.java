package server;

import Commands.*;
import clientInfo.Client;
import clientInfo.ClientDataBase;
import clientInfo.Message;

import java.io.IOException;
import java.net.Socket;

public class ServerCommands {

    private static String LOGMSG = "You need to be logged-in to perform this command!";

    public static String chooseCommand(ClientSocket clientSocket, String command){
        String response = "Command not recognized!";

        if(getCommand(command).equals("register")){
            response = Register.executeCommand(clientSocket, command);
        } else if (getCommand(command).equals("login")) {
            response = Login.executeCommand(clientSocket, command);
        } else if (command.equals("exit")){
            response = Exit.executeCommand(clientSocket, command);
        } else if (command.equals("stop")) {
            response = LOGMSG;
            if(clientSocket.getLoginAs() != null){
                response = Stop.executeCommand(clientSocket, command);
            }
        } else if (getCommand(command).equals("friend")) {
            response = LOGMSG;
            if(clientSocket.getLoginAs() != null){
                response = Friend.executeCommand(clientSocket, command);
            }
        } else if (getCommand(command).equals("send")){
            response = LOGMSG;
            if(clientSocket.getLoginAs() != null){
                response = Send.executeCommand(clientSocket, command);
            }
        } else if (getCommand(command).equals("read")){
            response = LOGMSG;
            if(clientSocket.getLoginAs() != null){
                response = Read.executeCommand(clientSocket, command);
            }
        }

        return response;
    }

    private static String getCommand(String command) {
        String[] comm = command.split(" ");
        return comm[0];
    }
}

package server;

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
            response = register(clientSocket, command);
        } else if (getCommand(command).equals("login")) {
            response = login(clientSocket, command);
        } else if (command.equals("exit")){
            response = "Goodbye " + clientSocket.getLoginAs() + "!";
        } else if (command.equals("stop")) {
            response = LOGMSG;
            if(clientSocket.getLoginAs() != null){
                response = stop();
            }
        } else if (getCommand(command).equals("friend")) {
            response = LOGMSG;
            if(clientSocket.getLoginAs() != null){
                response = friend(clientSocket, command);
            }
        } else if (getCommand(command).equals("send")){
            response = LOGMSG;
            if(clientSocket.getLoginAs() != null){
                response = send(clientSocket, command);
            }
        } else if (getCommand(command).equals("read")){
            response = LOGMSG;
            if(clientSocket.getLoginAs() != null){
                response = read(clientSocket, command);
            }
        }

        return response;
    }

    private static String getCommand(String command){
        String[] comm = command.split(" ");
        return comm[0];
    }

    private static String stop(){
        ClientDataBase.save(SocketCreator.PATH);
        SocketCreator.running = false;
        try {
            new Socket("127.0.0.1", 8100);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "Server stopping ...";
    }

    private static String register(ClientSocket clientSocket, String command){
        String[] comm = command.split(" ");
        if(ClientDataBase.findByName(comm[1]) == null){
            ClientDataBase.addClient(new Client(comm[1]));
            ClientDataBase.findByName(comm[1]).setLoggedIn(true);
            clientSocket.setLoginAs(comm[1]);
            return "Registered: '" + comm[1] + "' successfully!";
        }
        return "Name: '" + comm[1] +"' already taken!";
    }

    private static String login(ClientSocket clientSocket, String command){
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

    private static String friend(ClientSocket clientSocket, String command){
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

    private static String send(ClientSocket clientSocket, String command){
        Client client = ClientDataBase.findByName(clientSocket.getLoginAs());
        String data = command.substring(5,command.length());
        for(Client it : client.getFriends()){
            it.getMailBox().receiveMessage(new Message(client.getName(), data));
        }
        return "Message send successfully!";
    }

    private static String read(ClientSocket clientSocket, String command){
        boolean readall = true;
        boolean deleteAll = false;

        if(!command.equals("read")){
            String[] data = command.split(" ");
            if(data.length > 2)
                readall = Boolean.parseBoolean(data[1]);
            if(data.length > 3)
                deleteAll = Boolean.parseBoolean(data[2]);
        }

        return ClientDataBase.findByName(clientSocket.getLoginAs()).getMailBox().readMessages(readall, deleteAll);
    }
}

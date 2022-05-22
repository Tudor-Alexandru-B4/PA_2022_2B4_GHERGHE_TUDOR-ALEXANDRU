package Commands;

import clientInfo.ClientDataBase;
import server.ClientSocket;

public class Read {

    public static String executeCommand(ClientSocket clientSocket, String command){
        boolean readall = true;
        boolean deleteAll = false;

        if(!command.equals("read")){
            String[] data = command.split(" ");
            if(data.length > 1)
                readall = Boolean.parseBoolean(data[1]);
            if(data.length > 2)
                deleteAll = Boolean.parseBoolean(data[2]);
        }

        System.out.println(readall + " " + deleteAll);

        return ClientDataBase.findByName(clientSocket.getLoginAs()).getMailBox().readMessages(readall, deleteAll);
    }

}

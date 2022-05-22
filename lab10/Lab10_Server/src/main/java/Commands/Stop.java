package Commands;

import clientInfo.ClientDataBase;
import server.ClientSocket;
import server.SocketCreator;

import java.io.IOException;
import java.net.Socket;

public class Stop {

    public static String executeCommand(ClientSocket clientSocket, String command){
        ClientDataBase.save(SocketCreator.PATH);
        SocketCreator.running = false;
        try {
            new Socket("127.0.0.1", 8100);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "Server stopping ...";
    }

}

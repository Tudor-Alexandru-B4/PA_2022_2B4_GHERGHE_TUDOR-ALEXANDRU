package server;

import clientInfo.ClientDataBase;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SocketCreator {
    private static int PORT = 0;
    public static String PATH = "src/main/resources/clientData";
    public static boolean running = true;


    public SocketCreator(int port){
        ServerSocket serverSocket = null;
        ClientDataBase.load(PATH);
        List<Thread> threads = new ArrayList<>();
        try {
            serverSocket = new ServerSocket(port);
            while(running){
                System.out.println("Waiting for connection ...");
                Socket socket = serverSocket.accept();
                threads.add(new Thread(new ClientSocket(socket)));
                threads.get(threads.size() - 1).start();
            }
            for(int i = 0; i < threads.size(); i++)
                threads.get(i).join();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

}

package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private Socket socket = null;

    public Client(String adress, int port){
        try {
            socket = new Socket(adress, port);
            clientServerCommunication();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void clientServerCommunication(){
        try {
            while (true){
                //Sending a request
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                String request = readRequest();
                writer.println(request);

                //Receiving a response
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(socket.getInputStream())
                );
                String response = reader.readLine();
                System.out.println("Server response: " + response);
                if(request.equals("exit") || response.equals("Server stopping ...")){
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Server closed!");
        }
    }

    private String readRequest(){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter request: ");
        return keyboard.nextLine();
    }

}

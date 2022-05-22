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
        while (true){
            //Sending a request
            String request = sendRequest();

            //Receiving a response
            String response = "";
            System.out.print("Server response: ");
            try {
                if(request.substring(0,4).equals("read"))
                    receiveMultipleResponses();
                else{
                    response = receiveOneResponse();
                    System.out.println(response);
                }
            }catch (StringIndexOutOfBoundsException e){
                System.out.println("Disconnected from the server!");
                System.exit(0);
            }

            if(request.equals("exit") || response.equals("Server stopping ...")){
                break;
            }
        }
    }

    private String sendRequest(){
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(socket.getOutputStream(), true);
            String request = readRequest();
            writer.println(request);
            writer.flush();
            return request;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String receiveOneResponse(){
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );
            String response = reader.readLine();
            return response;
        } catch (IOException e) {
            System.out.println("Server closed!");
            System.exit(0);
        }
        return null;
    }

    private void receiveMultipleResponses(){
        while (true){
            String response = receiveOneResponse();
            if(response.equals("4682_-_StOp_-_159753"))
                break;
            System.out.println(response);
        }
    }

    private String readRequest(){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter request: ");
        return keyboard.nextLine();
    }

}

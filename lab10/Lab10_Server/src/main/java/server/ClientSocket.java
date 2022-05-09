package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSocket extends Thread{
    private Socket socket = null;
    private String loginAs = null;

    public ClientSocket(Socket socket){
        this.socket = socket;
    }

    public void run(){

        try {
            while(SocketCreator.running){
                //Getting the request
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(socket.getInputStream())
                );
                String request = reader.readLine();

                //Preparing the response
                String response = ServerCommands.chooseCommand(this, request);

                //Sending the response
                PrintWriter writer = new PrintWriter(socket.getOutputStream());
                writer.println(response);
                writer.flush();

                if(response.contains("Goodbye")){
                    System.exit(0);
                }
                if(request.equals("exit")){
                    break;
                }
            }

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

    public String getLoginAs() {
        return loginAs;
    }

    public void setLoginAs(String loginAs) {
        this.loginAs = loginAs;
    }
}

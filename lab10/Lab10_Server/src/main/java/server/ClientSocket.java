package server;

import clientInfo.ClientDataBase;

import java.io.*;
import java.net.Socket;

public class ClientSocket extends Thread{
    private Socket socket = null;
    private String loginAs = null;
    private ClientTimeOut timer = null;
    private static int time = 5; //minutes

    public ClientSocket(Socket socket){
        this.socket = socket;
        timer = new ClientTimeOut(this, time * 60);
        timer.start();
    }

    public void run(){
        while(SocketCreator.running){
            //Getting the request
            String request = receiveRequest();

            //Preparing the response
            String response = ServerCommands.chooseCommand(this, request);

            //Sending the response
            if(request.length() >= 4 && request.substring(0,4).equals("read"))
                sendMultipleResponses(response);
            else
                sendOneResponse(response);

            if(response.equals("Server stopping ...")){
                System.exit(0);
            }
            if(request.equals("exit")){
                break;
            }
        }

        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private String receiveRequest(){
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );
            timer.resetTimer();
            return reader.readLine();
        } catch (IOException e) {
            System.out.println("Client timed-out!");
        }
        return "exit";
    }

    private void sendOneResponse(String response){
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(socket.getOutputStream());
            writer.println(response);
            writer.flush();
            Thread.sleep(10);
            timer.resetTimer();
        } catch (IOException e) {
            return;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendMultipleResponses(String response){
        String[] data = response.split("\n");
        for(int i = 0; i < data.length; i++)
            sendOneResponse(data[i]);
        sendOneResponse("4682_-_StOp_-_159753");
    }

    public void kill(){
        if(loginAs != null)
            ClientDataBase.findByName(loginAs).setLoggedIn(false);
        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getLoginAs() {
        return loginAs;
    }

    public void setLoginAs(String loginAs) {
        this.loginAs = loginAs;
    }
}

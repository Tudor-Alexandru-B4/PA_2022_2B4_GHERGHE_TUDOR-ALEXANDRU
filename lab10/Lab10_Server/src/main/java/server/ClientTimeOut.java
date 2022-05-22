package server;

public class ClientTimeOut extends Thread{

    private ClientSocket clientSocket = null;
    private int timeOutTimer;
    private int currentTime;

    public ClientTimeOut(ClientSocket clientSocket, int maxTime){   //seconds
        this.clientSocket = clientSocket;
        timeOutTimer = maxTime;
        currentTime = 0;
    }

    public void resetTimer(){
        currentTime = 0;
    }

    public void run(){
        while(currentTime < timeOutTimer){
            try {
                Thread.sleep(1000);
                currentTime++;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        clientSocket.kill();
    }

}

package clientInfo;

import java.io.Console;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientDataBase {

    private static List<Client> clients = new ArrayList<>();

    public static void addClient(Client client){
        for(Client it : clients){
            if(it.getName().equals(client.getName()))
                return;
        }
        clients.add(client);
    }

    public static Client findByName(String name){
        for(Client it : clients){
            if(it.getName().equals(name))
                return it;
        }
        return null;
    }

    public static void save(String path){
        try{
            FileWriter writer = new FileWriter(path);
            for(Client it : clients){
                writer.write(it.getName() + "\n[");
                List<Client> friends = it.getFriends();
                for(int i = 0; i < friends.size(); i++){
                    writer.write(friends.get(i).getName());
                    if(i < it.getFriends().size() - 1){
                        writer.write(",");
                    }
                }
                writer.write("]\n{");
                List<Message> messages = it.getMailBox().getMessages();
                for(int i = 0; i < messages.size(); i++){
                    writer.write(messages.get(i).forSaving());
                    if(i < messages.size() - 1){
                        writer.write(",");
                    }
                }
                writer.write("}\n");
            }
            writer.close();
        }catch (IOException e){
            System.out.println(e);
        }
    }

    public static void load(String path){
        try{
            File file = new File(path);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()){
                String nameLine = reader.nextLine();
                if(!reader.hasNextLine())
                    return;
                String friendsLine = reader.nextLine();
                if(!reader.hasNextLine())
                    return;
                String messagesLine = reader.nextLine();

                Client client = findByName(nameLine);
                if(client == null)
                    client = new Client(nameLine);

                if(!friendsLine.equals("[]")){
                    friendsLine = friendsLine.substring(1,friendsLine.length() - 1);
                    String[] friends = friendsLine.split(",");
                    for(int i = 0; i < friends.length; i++){
                        Client friend = findByName(friends[i]);
                        if(friend == null){
                            friend = new Client(friends[i]);
                            addClient(friend);
                        }
                        client.AddFriend(friend);
                    }
                }

                if(!messagesLine.equals("{}")){
                    messagesLine = messagesLine.substring(1,messagesLine.length() - 1);
                    String[] messages = messagesLine.split(",");
                    for(int i = 0; i < messages.length; i++){
                        String[] data = messages[i].split(":");
                        String msg = "";
                        for(int j = 1; j < data.length - 1; j++){
                            msg = msg + data[j];
                            if(j < data.length - 2)
                                msg = msg + ":";
                        }
                        Message message = new Message(data[0], msg);
                        if(Boolean.parseBoolean(data[2]))
                            message.setRead();
                        client.getMailBox().receiveMessage(message);
                    }
                }

                addClient(client);
            }
        }catch (IOException e){
            System.out.println(e);
        }
    }

}

package clientInfo;

import java.util.ArrayList;
import java.util.List;

public class Client {

    private String name;
    private boolean loggedIn = false;
    private List<Client> friends = new ArrayList<>();
    private MailBox mailBox = new MailBox();

    public Client(String name){
        this.name = name;
    }

    public void AddFriend(Client friend){
        for (Client it : friends){
            if(friend.getName().equals(it.getName()))
                return;
        }
        if(ClientDataBase.findByName(friend.getName()) != null){
            friends.add(friend);
        }
    }

    public boolean isFriend(Client friend){
        for(Client it : friends){
            if(it.getName().equals(friend.getName()))
                return true;
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public List<Client> getFriends() {
        return friends;
    }

    public MailBox getMailBox() {
        return mailBox;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}

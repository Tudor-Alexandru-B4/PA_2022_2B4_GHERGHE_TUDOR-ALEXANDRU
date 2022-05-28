import classes.Friend;
import classes.Friendship;
import classes.Person;
import client.Client;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Client client = new Client();
        System.out.println(client.service.getCount());
        //client.service.addPerson("Ion");
        //client.service.addPerson("Ana");
        //client.service.addPerson("Mihai");
        //client.service.addPerson("Gigi");

        client.service.changeName(1,"Luluta");
        client.service.changeName(3,"Luluta");

        Person person = client.service.getById(1);
        System.out.println("Prima persoana: " + person.getName());

        List<Person> persons = client.service.getPersons();
        System.out.println("All persons in the network: ");
        for(var it : persons){
            System.out.println(it.getName());
        }

        //client.service.makeFriends(1,2);
        //client.service.makeFriends(1,3);
        //client.service.makeFriends(1,4);

        int id = 1;
        List<Friend> friends = client.service.getFriendList(id);
        System.out.println("1'st person's friends: ");
        for(var it : friends){
            if(it.getId().getId1() == id)
                System.out.println(it.getId().getId2());
            else
                System.out.println(it.getId().getId1());
        }

        int number = 3;
        List<Person> popular = client.service.getPopular(number);
        System.out.println("Top " + number + "  persons: ");
        for(var it : popular){
            System.out.println(it.getName());
        }

    }

}

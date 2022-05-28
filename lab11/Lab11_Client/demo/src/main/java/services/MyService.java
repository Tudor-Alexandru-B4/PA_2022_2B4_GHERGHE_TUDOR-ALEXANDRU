package services;

import classes.Friend;
import classes.Friendship;
import classes.NameModifier;
import classes.Person;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class MyService {

    private final RestTemplate restTemplate;

    public MyService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public int getCount(){
        return this.restTemplate.getForObject("http://localhost:8081/persons/count", Integer.class);
    }

    public List<Person> getPersons(){
        return Arrays.stream(this.restTemplate.getForObject("http://localhost:8081/persons", Person[].class)).toList();
    }

    public void addPerson(String name){
        HttpEntity<Person> entity = new HttpEntity<>(new Person(name, false));
        this.restTemplate.exchange("http://localhost:8081/persons", HttpMethod.POST, entity, Person.class);
    }

    public void changeName(long id, String name){
        HttpEntity<NameModifier> entity = new HttpEntity<>(new NameModifier(id, name));
        this.restTemplate.exchange("http://localhost:8081/persons", HttpMethod.PUT, entity, NameModifier.class);
    }

    public Person getById(long id){
        return this.restTemplate.getForObject("http://localhost:8081/persons/" + id, Person.class);
    }

    public void makeFriends(long id1, long id2){
        HttpEntity<Friendship> entity = new HttpEntity<>(new Friendship(id1, id2));
        this.restTemplate.exchange("http://localhost:8081/persons/friend", HttpMethod.POST, entity, Friendship.class);
    }

    public List<Friend> getFriendList(long id){
        return Arrays.stream(this.restTemplate.getForObject("http://localhost:8081/persons/friend/" + id, Friend[].class)).toList();
    }

    public List<Person> getPopular(int number){
        return Arrays.stream(this.restTemplate.getForObject("http://localhost:8081/persons/popular/" + number, Person[].class)).toList();
    }

}

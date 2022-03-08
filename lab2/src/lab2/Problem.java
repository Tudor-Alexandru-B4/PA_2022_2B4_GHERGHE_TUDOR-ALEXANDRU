package lab2;

import java.util.ArrayList;

import static java.lang.System.*;
import static java.lang.System.exit;



public class Problem {
    private static final String IOOB = "Index out of bounds";
    private final ArrayList<Event> events = new ArrayList<>();
    private final ArrayList<Room> rooms = new ArrayList<>();

    public void addEvent(Event obj){
        for(Event it : events){
            if(obj.equals(it)){
                return;
            }
        }
        events.add(obj);
    }

    public void addRoom(Room obj){
        for(Room it : rooms){
            if(obj.equals(it)){
                return;
            }
        }
        rooms.add(obj);
    }

    public void setEvent(Event inputEvent, int index){
        if(index > events.size() || index < 0){
            err.println(IOOB);
            exit(1);
        }
        events.set(index, inputEvent);
    }

    public Event getEvent(int index){
        if(index > events.size() || index < 0){
            err.println(IOOB);
            exit(1);
        }
        return events.get(index);
    }

    public int eventSize(){
        return events.size();
    }

    public void setRoom(Room inputRoom, int index){
        if(index > events.size() || index < 0){
            err.println(IOOB);
            exit(1);
        }
        rooms.set(index, inputRoom);
    }

    public Room getRoom(int index){
        if(index > events.size() || index < 0){
            err.println(IOOB);
            exit(1);
        }
        return rooms.get(index);
    }

    public int roomSize(){
        return rooms.size();
    }

    @Override
    public String toString() {
        return "Problem{\n" +
                "events=" + events +
                ",\nrooms=" + rooms +
                "\n}";
    }
}

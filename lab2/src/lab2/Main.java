package lab2;
import lab2.Event;
import lab2.Room;


public class Main {

    public static void main(String[] args) {
        Event event1 = new Event("C1", 100, 8, 10);
        Event event2 = new Event("C2", 100, 10, 12);
        Event event3 = new Event("L1", 30, 8, 10);
        Event event4 = new Event("L2", 30, 8, 10);
        Event event5 = new Event("L3", 30, 10, 12);
        Room room1 = new Room("401", 30, "LAB");
        Room room2 = new Room("403", 30, "LAB");
        Room room3 = new Room("405", 30, "LAB");
        Room room4 = new Room("309", 100, "LECTURE_HALL");

        System.out.println("\nEVENTS:");
        System.out.println(event1.toString());
        System.out.println(event2.toString());
        System.out.println(event3.toString());
        System.out.println(event4.toString());
        System.out.println(event5.toString());
        System.out.println("\n\nROOMS:");
        System.out.println(room1.toString());
        System.out.println(room2.toString());
        System.out.println(room3.toString());
        System.out.println(room4.toString());
    }
}

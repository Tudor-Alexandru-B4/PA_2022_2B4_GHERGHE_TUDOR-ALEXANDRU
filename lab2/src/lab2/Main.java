package lab2;

import java.time.LocalTime;

import static java.lang.System.*;


public class Main {

    public static void main(String[] args) {
        Event event1 = new Event("C1", 100, LocalTime.of(8,0,0), LocalTime.of(10,0,0));
        Event event2 = new Event("C2", 100, LocalTime.of(10,0,0), LocalTime.of(12,0,0));
        Event event3 = new Event("L1", 30, LocalTime.of(8,0,0), LocalTime.of(10,0,0));
        Event event4 = new Event("L2", 30, LocalTime.of(8,0,0), LocalTime.of(10,0,0));
        Event event5 = new Event("L3", 30, LocalTime.of(10,0,0), LocalTime.of(12,0,0));
        Room room1 = new ComputerLab("401", 30, "Windows");
        Room room2 = new ComputerLab("403", 30, "Linux");
        Room room3 = new ComputerLab("405", 30, "MacOS");
        Room room4 = new LectureHall("309", 100, true);
        Problem problem = new Problem();

        problem.addEvent(event1);
        problem.addEvent(event2);
        problem.addEvent(event3);
        problem.addEvent(event4);
        problem.addEvent(event5);
        problem.addRoom(room1);
        problem.addRoom(room2);
        problem.addRoom(room3);
        problem.addRoom(room4);

        Solution solution = new Solution(problem);

        out.println("\n");
        solution.printSolutionDSatur();
        out.println("\n\n==========================================================================================\n\n");
        solution.printSolutionGreedy();

        /*
        CRoom room1 = new CRoom("401", 30, "LAB");
        CRoom room2 = new CRoom("403", 30, "LAB");
        CRoom room3 = new CRoom("405", 30, "LAB");
        CRoom room4 = new CRoom("309", 100, "LECTURE_HALL");

        out.println(event1);
        out.println(event2);
        out.println(event3);
        out.println(event4);
        out.println(event5);
        out.println(room1);
        out.println(room2);
        out.println(room3);
        out.println(room4);
        */
    }
}

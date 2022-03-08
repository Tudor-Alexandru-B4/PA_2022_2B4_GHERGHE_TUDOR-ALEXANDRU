package lab2;

import java.util.ArrayList;
import static java.lang.System.*;

/**
 * This class is used to generate solution for the Events Problem
 */
public class Solution {
    private final Problem problem;

    public Solution(Problem inputProblem){
        problem = inputProblem;
    }

    public void sortByEndTime(){
        for(int i = 0; i < problem.eventSize() - 1; i++){
            for(int j = i + 1; j < problem.eventSize(); j++){
                if(problem.getEvent(i).getEndTime().compareTo(problem.getEvent(j).getEndTime()) > 0){
                    Event temp = problem.getEvent(i);
                    problem.setEvent(problem.getEvent(j), i);
                    problem.setEvent(temp, j);
                }
            }
        }
    }

    public void sortByCapacity(){
        for(int i = 0; i < problem.roomSize() - 1; i++){
            for(int j = i + 1; j < problem.roomSize(); j++){
                if(problem.getRoom(i).capacity > problem.getRoom(j).capacity){
                    Room temp = problem.getRoom(i);
                    problem.setRoom(problem.getRoom(j), i);
                    problem.setRoom(temp, j);
                }
            }
        }
    }

    /**
     * Prints the schedule for the given problem
     * <p>
     *     This method uses sortByCapacity() and sortByEndTime() to
     *     order the components for the Greedy solution
     * </p>
     */
    public void printSolutionGreedy(){
        ArrayList<ArrayList<Event>> eventMatrix = new ArrayList<>();
        for(int i = 0; i < problem.roomSize(); i++){
            eventMatrix.add(new ArrayList<>());
        }

        sortByCapacity();
        sortByEndTime();

        for(int i = 0; i < problem.eventSize(); i++){
            boolean added = false;
            for(int j = 0; j < problem.roomSize() && !added; j++){
                if((problem.getEvent(i).getNoParticipants() <= problem.getRoom(j).getCapacity()) && (eventMatrix.get(j).isEmpty() || eventMatrix.get(j).get(eventMatrix.get(j).size() - 1).getEndTime().compareTo(problem.getEvent(i).getStartTime()) <= 0)){
                    eventMatrix.get(j).add(problem.getEvent(i));
                    added = true;

                }
            }
        }

        for(int i = 0; i < problem.roomSize(); i++){
            out.println(problem.getRoom(i) + ":");
            for(Event it : eventMatrix.get(i)){
                out.println("          " + it);
            }
            out.println();
        }
    }

    /**
     *Prints the schedule for the given problem
     *<p>
     *    This method uses DSotur Algorithm to generate the final listing
     * </p>
     */
    public void printSolutionDSatur(){
        boolean[][] edgeMatrix = new boolean[problem.eventSize()][problem.eventSize()];
        int[] colorEvent = new int[problem.eventSize()];
        int[] colorRoom = new int[problem.eventSize()];

        for(int i = 0; i < problem.eventSize(); i++){
            for(int j = 0; j < problem.eventSize(); j++){
                edgeMatrix[i][j] = problem.getEvent(i).getStartTime().compareTo(problem.getEvent(j).getEndTime()) < 0;
            }
        }

        for(int i = 0; i < problem.eventSize(); i++){
            colorEvent[i] = -1;
        }

        for(int i = 0; i < problem.roomSize(); i++){
            colorRoom[i] = -1;
        }

        colorEvent[0] = 1;
        for(int i = 1; i < problem.eventSize(); i++){
            int color = 1;
            int colorToMatch;

            do{
                colorToMatch = color;
                for(int j = 0; j < problem.eventSize(); j++){
                    if(i != j && edgeMatrix[i][j] && colorEvent[j] == color){
                        color++;
                    }
                }
            }while(color != colorToMatch);

            colorEvent[i] = color;
        }

        ArrayList<ArrayList<Event>> eventMatrix = new ArrayList<>();
        for(int i = 0; i < problem.roomSize(); i++){
            eventMatrix.add(new ArrayList<>());
        }

        sortByCapacity();

        for(int i = 0; i < problem.eventSize(); i++){
            int firstRoom = -1;
            boolean assigned = false;
            for(int j = 0; j < problem.roomSize() && !assigned; j++){
                if(problem.getRoom(j).getCapacity() >= problem.getEvent(i).getNoParticipants()){
                    if(colorRoom[j] == -1 && firstRoom == -1){
                        firstRoom = j;
                    }else if(colorRoom[j] == colorEvent[i]){
                        assigned = true;
                        eventMatrix.get(j).add(problem.getEvent(i));
                    }
                }
            }
            if(!assigned){
                colorRoom[firstRoom] = colorEvent[i];
                eventMatrix.get(firstRoom).add(problem.getEvent(i));
            }
        }

        for(int i = 0; i < problem.roomSize(); i++){
            out.println(problem.getRoom(i) + ":");
            for(Event it : eventMatrix.get(i)){
                out.println("          " + it);
            }
            out.println();
        }
    }

    @Override
    public String toString() {
        return problem.toString();
    }
}

package lab2;

import java.util.Objects;
import java.time.LocalTime;

import static java.lang.System.*;

public class Event {
    private String name;
    private int noParticipants;
    private LocalTime startTime;
    private LocalTime endTime;

    public Event(String inputName, int inputParticipants, LocalTime inputStartTime, LocalTime inputEndTime){
        if(inputName != null && inputName.length() > 0){
            name = inputName;
        }else{
            err.println("Name cannot be NULL");
            exit(1);
        }

        if(inputParticipants > 0) {
            noParticipants = inputParticipants;
        }else{
            err.println("Event cannot have less than 1 participant");
            exit(1);
        }

        startTime = inputStartTime;

        if(inputEndTime.compareTo(startTime) > 0) {
            endTime = inputEndTime;
        }else{
            err.println("End time invalid");
            exit(1);
        }
    }

    public String getName(){
        return name;
    }

    public int getNoParticipants(){
        return noParticipants;
    }

    public LocalTime getStartTime(){
        return startTime;
    }

    public LocalTime getEndTime(){
        return endTime;
    }

    public void setName(String inputName){
        if(inputName != null && inputName.length() > 0){
            name = inputName;
        }else{
            err.println("Name cannot be NULL");
            exit(1);
        }
    }

    public void setNoParticipants(int inputParticipants){
        if(inputParticipants > 0) {
            noParticipants = inputParticipants;
        }else{
            err.println("Event cannot have less than 1 participant");
            exit(1);
        }
    }

    public void setStartTime(LocalTime inputStartTime){
        startTime = inputStartTime;
    }

    public void setEndTime(LocalTime inputEndTime){
        endTime = inputEndTime;
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", noParticipants=" + noParticipants +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return noParticipants == event.noParticipants && Objects.equals(name, event.name) && Objects.equals(startTime, event.startTime) && Objects.equals(endTime, event.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, noParticipants, startTime, endTime);
    }
}

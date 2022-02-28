package lab2;

public class Event {
    private String name;
    private int noParticipants;
    private int startTime;
    private int endTime;

    public Event(String inputName, int inputParticipants, int inputStartTime, int inputEndTime){
        if(inputName != null && inputName.length() > 0){
            name = inputName;
        }else{
            System.err.println("Name cannot be NULL");
            System.exit(1);
        }

        if(inputParticipants > 0) {
            noParticipants = inputParticipants;
        }else{
            System.err.println("Event cannot have less than 1 participant");
            System.exit(1);
        }

        if(inputStartTime > 0 && inputStartTime < 25) {
            startTime = inputStartTime;
        }else{
            System.err.println("Start time invalid");
            System.exit(1);
        }

        if(inputEndTime > 0 && inputEndTime < 25 && inputEndTime > startTime) {
            endTime = inputEndTime;
        }else{
            System.err.println("End time invalid");
            System.exit(1);
        }
    }

    public String getName(){
        return name;
    }

    public int getNoParticipants(){
        return noParticipants;
    }

    public int getStartTime(){
        return startTime;
    }

    public int getEndTime(){
        return endTime;
    }

    public void setName(String inputName){
        if(inputName != null && inputName.length() > 0){
            name = inputName;
        }else{
            System.err.println("Name cannot be NULL");
            System.exit(1);
        }
    }

    public void setNoParticipants(int inputParticipants){
        if(inputParticipants > 0) {
            noParticipants = inputParticipants;
        }else{
            System.err.println("Event cannot have less than 1 participant");
            System.exit(1);
        }
    }

    public void setStartTime(int inputStartTime){
        if(inputStartTime > 0 && inputStartTime < 25) {
            startTime = inputStartTime;
        }else{
            System.err.println("Start time invalid");
            System.exit(1);
        }
    }

    public void setEndTime(int inputEndTime){
        if(inputEndTime > 0 && inputEndTime < 25 && inputEndTime > startTime) {
            endTime = inputEndTime;
        }else{
            System.err.println("End time invalid");
            System.exit(1);
        }
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
}

package lab2;

public class Room {
    private String name;
    private int capacity;
    private Type type;
    enum Type{
        LAB,
        LECTURE_HALL;
    }

    public Room(String inputName, int inputCapacity, String inputType){
        if(inputName != null && inputName.length() > 0){
            name = inputName;
        }else{
            System.err.println("Name cannot be NULL");
            System.exit(1);
        }

        if(inputCapacity > 1){
            capacity = inputCapacity;
        }else{
            System.err.println("Capacity cannot be less than 1");
            System.exit(1);
        }

        type = Type.valueOf(inputType);
    }

    public String getName(){
        return name;
    }

    public int getCapacity(){
        return capacity;
    }

    public Type getType(){
        return type;
    }

    public void setName(String inputName){
        if(inputName != null && inputName.length() > 0){
            name = inputName;
        }else{
            System.err.println("Name cannot be NULL");
            System.exit(1);
        }
    }

    public void setCapacity(int inputCapacity){
        if(inputCapacity > 1){
            capacity = inputCapacity;
        }else{
            System.err.println("Capacity cannot be less than 1");
            System.exit(1);
        }
    }

    public void setType(String inputType){
        type = Type.valueOf(inputType);
    }

    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", capacity=" + capacity +
                ", type='" + type + '\'' +
                '}';
    }
}

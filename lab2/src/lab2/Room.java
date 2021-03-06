package lab2;

import java.util.Objects;

import static java.lang.System.*;

public abstract class Room {
    protected String name;
    protected int capacity;
    protected String type;

    public String getName(){
        return name;
    }

    public int getCapacity(){
        return capacity;
    }

    public String getType(){
        return type;
    }

    public void setName(String inputName){
        if(inputName != null && inputName.length() > 0){
            name = inputName;
        }else{
            err.println("Name cannot be NULL");
            exit(1);
        }
    }

    public void setCapacity(int inputCapacity){
        if(inputCapacity > 1){
            capacity = inputCapacity;
        }else{
            err.println("Capacity cannot be less than 1");
            exit(1);
        }
    }

    public void setType(String inputType){
        type = inputType;
    }

    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", capacity=" + capacity +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return capacity == room.capacity && Objects.equals(name, room.name) && type == room.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, capacity, type);
    }
}

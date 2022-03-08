package lab3;

import static java.lang.System.*;
import static java.lang.System.exit;

public class Computer extends Node implements Storage, Identifiable {
    private int memory;
    private String id;

    public Computer(String inputName, String inputID, int inputMemory){
        if(inputName != null && inputName.length() > 0){
            name = inputName;
        }else{
            err.println("Name cannot be null");
            exit(1);
        }

        if(inputID != null && inputID.length() > 0){
            id = inputID;
        }else{
            err.println("ID cannot be null");
            exit(1);
        }

        if(inputMemory > 0 && (int)(Math.ceil(Math.log(inputMemory) / Math.log(2))) == (int)(Math.floor(Math.log(inputMemory) / Math.log(2)))){
            memory = inputMemory;
        }else{
            err.println("Memory inserted wrong");
            exit(1);
        }
    }

    @Override
    public int getStorage(){
        return memory;
    }

    @Override
    public void setStorage(int inputMemory){
        if(inputMemory > 0 && (int)(Math.ceil((Math.log(inputMemory) / Math.log(2)))) == (int)(Math.floor(Math.log(inputMemory) / Math.log(2)))){
            memory = inputMemory;
        }else{
            err.println("Memory inserted wrong");
            exit(1);
        }
    }

    @Override
    public String getID(){
        return id;
    }

    @Override
    public void setID(String inputID){
        if(inputID != null && inputID.length() > 0){
            id = inputID;
        }else{
            err.println("ID cannot be null");
            exit(1);
        }
    }

    @Override
    public String toString() {
        return "\n       Computer{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", memory=" + memory +
                '}';
    }
}

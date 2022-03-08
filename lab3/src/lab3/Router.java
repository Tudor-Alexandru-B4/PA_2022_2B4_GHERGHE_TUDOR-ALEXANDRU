package lab3;

import static java.lang.System.*;
import static java.lang.System.exit;

public class Router extends Node implements Identifiable {
    private String id;

    public Router(String inputName, String inputID){
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
        return "\n       Router{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}

package lab3;

import static java.lang.System.*;
import static java.lang.System.exit;

public class Switch extends Node{
    public Switch(String inputName){
        if(inputName != null && inputName.length() > 0){
            name = inputName;
        }else{
            err.println("Name cannot be null");
            exit(1);
        }
    }

    @Override
    public String toString() {
        return "\n       Switch{" +
                "name='" + name + '\'' +
                '}';
    }
}

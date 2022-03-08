package lab2;

import static java.lang.System.*;
import static java.lang.System.exit;

public class ComputerLab extends Room {
    private String operatingSystem;

    public ComputerLab(String inputName, int inputCapacity, String inputOS){
        if(inputName != null && inputName.length() > 0){
            name = inputName;
        }else{
            err.println("Name cannot be NULL");
            exit(1);
        }

        if(inputCapacity > 1){
            capacity = inputCapacity;
        }else{
            err.println("Capacity cannot be less than 1");
            exit(1);
        }

        if(inputOS != null && inputOS.length() > 0){
            operatingSystem = inputOS;
        }else{
            err.println("Operating System cannot be NULL");
            exit(1);
        }

        type = "ComputerLab";
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String inputOS){
        if(inputOS != null && inputOS.length() > 0){
            operatingSystem = inputOS;
        }else{
            err.println("Operating System cannot be NULL");
            exit(1);
        }
    }
}

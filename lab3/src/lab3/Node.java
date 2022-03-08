package lab3;

import static java.lang.System.*;

public abstract class Node implements Comparable<Node> {
    protected String name;

    public String getName(){
        return name;
    }

    public void setName(String inputName){
        if(inputName != null && inputName.length() > 0){
            name = inputName;
        }else{
            err.println("Name cannot be null");
            exit(1);
        }
    }

    @Override
    public int compareTo(Node toCompare){
        return this.name.compareTo(toCompare.getName());
    }
}

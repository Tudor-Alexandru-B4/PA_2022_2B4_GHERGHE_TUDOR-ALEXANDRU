package lab3;

import java.util.*;

import static java.lang.System.*;

public abstract class Node implements Comparable<Node> {
    protected String name;
    protected Map<Node,Integer> map = new HashMap<>();
    protected Map<Node,Double> probabilityMap = new HashMap<>();

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

    public void addNodeToMap(Node inputNode, Integer inputValue){
        map.put(inputNode,inputValue);
    }

    public void addNodeToProbabilityMap(Node inputNode, Double inputValue){
        probabilityMap.put(inputNode,inputValue);
    }

    @Override
    public int compareTo(Node toCompare){
        return this.name.compareTo(toCompare.getName());
    }
}

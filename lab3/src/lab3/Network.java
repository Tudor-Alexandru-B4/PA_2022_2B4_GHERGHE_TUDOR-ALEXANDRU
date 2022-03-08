package lab3;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import static java.lang.System.*;
import static java.lang.System.exit;

public class Network{
    private final List<Node> networkList = new ArrayList<>();

    public void addNode(Node inputNode){
        networkList.add(inputNode);
        Collections.sort(networkList);
    }

    public Node getNode(int index){
        if(index < 0 || index > networkList.size()){
            err.println("Index out of bounds");
            exit(1);
        }
        return networkList.get(index);
    }

    @Override
    public String toString() {
        return "Network{\n" +
                "   network=" + networkList +
                "\n}";
    }
}

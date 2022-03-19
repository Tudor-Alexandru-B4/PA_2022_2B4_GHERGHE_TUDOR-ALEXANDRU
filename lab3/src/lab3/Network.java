package lab3;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Map;

import static java.lang.System.*;
import static java.lang.System.exit;

public class Network{
    private static final int INF = 99999;
    private static final Double DINF = 99999.0;
    private final List<Node> networkList = new ArrayList<>();

    private int returnIndex(Node node){
        for(int i = 0; i < networkList.size(); i++){
            if(networkList.get(i) == node){
                return i;
            }
        }
        return -1;
    }

    private int returnShortestIndex(Double[] shortest, boolean[] visited){
        Double min = DINF;
        int indexMin = -1;

        for(int i = 0; i < shortest.length; i++){
            if(shortest[i] <= min && !visited[i]){
                min = shortest[i];
                indexMin = i;
            }
        }
        return indexMin;
    }

    private void printBacktrack(int start, int finish, int[] backtrack, boolean reversed){
        int current = finish;
        ArrayList<Integer> order= new ArrayList<>();

        while(current != start){
            order.add(current);
            current = backtrack[current];
        }
        order.add(current);

        out.println("Safest route from " + networkList.get(start).getName() + " to " + networkList.get(finish).getName());

        if(reversed){
            for(int i = 0; i < order.size(); i++){
                out.print(networkList.get(order.get(i)).getName() + " ");
            }
        }else{
            for(int i = order.size() - 1; i >= 0; i--){
                out.print(networkList.get(order.get(i)).getName() + " ");
            }
        }
    }

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

    public void addEdge(Node node1, Node node2, Integer inputValue, Double probability){
        node1.addNodeToMap(node2,inputValue);
        node2.addNodeToMap(node1,inputValue);
        node1.addNodeToProbabilityMap(node2, probability);
        node2.addNodeToProbabilityMap(node1, probability);
    }

    public void printNetwork(){
        out.println("Network time costs:");
        for(Node it : networkList){
            for(Map.Entry<Node,Integer> m : it.map.entrySet()){
                if(it.getName().compareTo(m.getKey().getName()) < 0){
                    out.println(it.getName() + "--" + m.getKey().getName() + "  :  " + m.getValue());
                }
            }
        }
    }

    public void printIdentifiable(){
        ArrayList<Node> idArray = new ArrayList<>();
        for(Node it : networkList){
            if(it instanceof Computer || it instanceof Router){
                idArray.add(it);
            }
        }

        for(int i = 0; i < idArray.size() - 1; i++){
            for(int j = i + 1; j < idArray.size(); j++){
                String node1;
                String node2;

                if(idArray.get(i) instanceof Computer){
                    node1 = ((Computer) idArray.get(i)).getID();
                }else{
                    node1 = ((Router) idArray.get(i)).getID();
                }

                if(idArray.get(j) instanceof Computer){
                    node2 = ((Computer) idArray.get(j)).getID();
                }else{
                    node2 = ((Router) idArray.get(j)).getID();
                }

                if(node1.compareTo(node2) > 0){
                    Node temp = idArray.get(i);
                    idArray.set(i,idArray.get(j));
                    idArray.set(j,temp);
                }
            }
        }

        out.println("\n\nIdentifiable nodes:");
        for(Node it : idArray){
            out.println(it);
        }
    }

    public void printFloydWarshall(){
        int size = networkList.size();
        int[][] graph = new int[size][size];

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                graph[i][j] = INF;
            }
        }

        for(Node it : networkList) {
            for (Map.Entry<Node, Integer> m : it.map.entrySet()) {
                int i = returnIndex(it);
                int j = returnIndex(m.getKey());
                graph[i][j] = graph[j][i] = m.getValue();
            }
        }

        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (graph[i][j] > (graph[i][k] + graph[k][j])
                            && (graph[k][j] != INF
                            && graph[i][k] != INF))
                        graph[i][j] = graph[i][k] + graph[k][j];
                }
            }
        }

        out.println("All shortest paths between identifiable:");
        for(int i = 0; i < networkList.size(); i++){
            if(networkList.get(i) instanceof Switch){
                continue;
            }
            out.print(networkList.get(i).getName() + ":  ");
            for(int j = 0; j < networkList.size(); j++){
                if(networkList.get(j) instanceof Switch){
                    continue;
                }
                if(i == j){
                    graph[i][j] = 0;
                }
                if(graph[i][j] == INF){
                    out.print("INF ");
                }else{
                    out.print(graph[i][j]+" ");
                }
            }
            out.println();
        }
    }

    public void printSafestPath(Node start, Node finish){
        boolean reversed = false;
        if(returnIndex(finish) < returnIndex(start)){
            Node temp = start;
            start = finish;
            finish = temp;
            reversed = true;
        }
        if(returnIndex(start) == returnIndex(finish)){
            out.println("Same node: 0");
            return;
        }

        int size = networkList.size();
        Double[][] graph = new Double[size][size];
        boolean[] visited = new boolean[size];
        Double[] shortest = new Double[size];
        int[] before = new int[size];

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                graph[i][j] = DINF;
            }
        }

        for(Node it : networkList) {
            for (Map.Entry<Node, Double> m : it.probabilityMap.entrySet()) {
                int i = returnIndex(it);
                int j = returnIndex(m.getKey());
                graph[i][j] = graph[j][i] = m.getValue();
            }
        }

        for(int i = 0; i < size; i++){
            visited[i] = false;
            shortest[i] = DINF;
        }
        shortest[returnIndex(start)] = 0.0;
        before[returnIndex(start)] = -1;

        for(int i = 0; i < size - 1; i++){
            int processing = returnShortestIndex(shortest,visited);
            visited[processing] = true;
            for(int j = 0; j < size; j++){
                if(!graph[processing][j].equals(DINF) && shortest[j] >= (shortest[processing] + graph[processing][j])){
                    shortest[j] = shortest[processing] + graph[processing][j];
                    before[j] = processing;
                }
            }
        }

        printBacktrack(returnIndex(start), returnIndex(finish), before, reversed);
    }

    @Override
    public String toString() {
        return "\n\nNetwork{\n" +
                "   network=" + networkList +
                "\n}";
    }
}

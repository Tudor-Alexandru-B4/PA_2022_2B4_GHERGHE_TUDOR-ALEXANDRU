package lab3;

import static java.lang.System.*;

public class Main {

    public static void main(String[] args) {
        Node v6 = new Computer("6", "v1", 32);
        Node v5 = new Router("5", "v2");
        Node v4 = new Switch("4");
        Node v3 = new Switch("3");
        Node v2 = new Router("2", "v5");
        Node v1 = new Computer("1", "v6", 1024);

        Network network = new Network();

        network.addNode(v1);
        network.addNode(v2);
        network.addNode(v3);
        network.addNode(v4);
        network.addNode(v5);
        network.addNode(v6);

        network.addEdge(v1,v2,10, 0.1);
        network.addEdge(v1,v3,50, 0.5);
        network.addEdge(v2,v3,20, 0.2);
        network.addEdge(v2,v4,20, 0.2);
        network.addEdge(v2,v5,10, 0.1);
        network.addEdge(v3,v4,20, 0.2);
        network.addEdge(v4,v5,30, 0.3);
        network.addEdge(v4,v6,10, 0.1);
        network.addEdge(v5,v6,20, 0.2);

        out.println("\n");
        network.printNetwork();

        out.println(network);

        network.printIdentifiable();

        out.println("\n");
        network.printFloydWarshall();

        out.println("\n");
        network.printSafestPath(v3,v6);
    }
}

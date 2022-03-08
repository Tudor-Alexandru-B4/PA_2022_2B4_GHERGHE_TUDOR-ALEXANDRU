package lab3;

import static java.lang.System.*;

public class Main {

    public static void main(String[] args) {
        Node v1 = new Computer("6", "v1", 32);
        Node v2 = new Router("5", "v2");
        Node v3 = new Switch("4");
        Node v4 = new Switch("3");
        Node v5 = new Router("2", "v5");
        Node v6 = new Computer("1", "v6", 1024);
        Network network = new Network();

        network.addNode(v1);
        network.addNode(v2);
        network.addNode(v3);
        network.addNode(v4);
        network.addNode(v5);
        network.addNode(v6);

        out.println(network);
    }
}

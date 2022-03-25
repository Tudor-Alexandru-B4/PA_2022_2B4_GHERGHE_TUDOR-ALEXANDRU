import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.stream.IntStream;

import static java.lang.System.*;

public class Main {

    public static void main(String[] args) {

        var nodes = IntStream.rangeClosed(1,9).mapToObj(i -> new Intersection("v" + i)).toArray(Intersection[]::new);
        int[] lengths = {2,2,2,2,1,3,2,2,3,1,1,2,3,1,1,1};
        var edges = IntStream.rangeClosed(1,16).mapToObj(i -> new Street("s" + i, lengths[i-1])).toArray(Street[]::new);

        nodes[0].returnList().addAll(Arrays.asList(edges[0],edges[1],edges[2]));
        nodes[1].returnList().addAll(Arrays.asList(edges[0],edges[3],edges[5]));
        nodes[2].returnList().addAll(Arrays.asList(edges[1],edges[3],edges[4],edges[6],edges[7]));
        nodes[3].returnList().addAll(Arrays.asList(edges[2],edges[4],edges[8]));
        nodes[4].returnList().addAll(Arrays.asList(edges[5],edges[9],edges[10],edges[11]));
        nodes[5].returnList().addAll(Arrays.asList(edges[7],edges[8],edges[9],edges[12]));
        nodes[6].returnList().addAll(Arrays.asList(edges[6],edges[13],edges[14]));
        nodes[7].returnList().addAll(Arrays.asList(edges[10],edges[13],edges[15]));
        nodes[8].returnList().addAll(Arrays.asList(edges[11],edges[12],edges[14],edges[15]));

        for(Intersection it : nodes){
            for(Street str : it.returnList()){
                str.addBound(it);
            }
        }

        LinkedList<Street> streetList = new LinkedList<>(Arrays.stream(edges).toList());

        streetList.sort(Street::compareTo);

        out.println(streetList);

        HashSet<Intersection> intersectionHashSet = new HashSet<>(Arrays.asList(nodes));

        City city = new City(streetList,intersectionHashSet);

        city.printStreetsLongerThan(2);

        out.println("\n\n");
        city.generateRandomNames();
        city.printIntersections();

        out.println("\n\n");
        out.println("\nMinimum cost of the network: " + city.returnMinimumCostPrim());
    }
}
import com.github.javafaker.Faker;

import java.util.*;

import static java.lang.System.*;

public class City {
    private final LinkedList<Street> streets;
    private final HashSet<Intersection> intersections;
    private final Map<Intersection, List<Street>> cityMap = new HashMap<>();

    private void generateMap(){
        for(Intersection it : intersections){
            cityMap.put(it, it.returnList());
        }
    }

    public City(List<Street> streets, Set<Intersection> intersections){
        this.streets = new LinkedList<>(streets);
        this.intersections = new HashSet<>(intersections);
        generateMap();
    }

    public List<Street> getStreets(){
        return streets;
    }

    public Set<Intersection> getIntersections(){
        return intersections;
    }

    public Map<Intersection, List<Street>> getCityMap() {
        return cityMap;
    }

    public void printIntersections(){
        for(Map.Entry<Intersection, List<Street>> it : cityMap.entrySet()){
            out.println(it.getKey());
        }
    }

    public void printStreets(){
        for(Map.Entry<Intersection, List<Street>> it : cityMap.entrySet()){
            out.println(it.getValue());
        }
    }

    public void printStreetsLongerThan(int value){
        streets.stream().filter(s -> s.getStreetLength() >= value && ( s.getBound(0).returnList().size() + s.getBound(1).returnList().size() - 2) >= 3).forEach(out::println);
    }

    public void generateRandomNames(){
        for(Street it : streets){
            Faker faker = new Faker();
            it.setStreetName(faker.address().streetName());
        }

        for(Intersection it : intersections){
            Faker faker = new Faker();
            it.setName(faker.leagueOfLegends().champion());
        }
    }

    public int returnMinimumCostPrim(){
        int iSize = intersections.size();
        int cost = 0;
        Set<Street> chosenStreets = new HashSet<>();
        Set<Intersection> chosenIntersections = new HashSet<>();

        chosenIntersections.add(streets.get(0).getBound(0));

        while(iSize != chosenIntersections.size()){
            Street minLengthStreet = new Street("WRONG",-999);
            int minLength = Integer.MAX_VALUE;

            for(Intersection it : chosenIntersections){
                for(Street str : it.returnList()){
                    if(str.getStreetLength() < minLength && !chosenStreets.contains(str)
                            && (!chosenIntersections.contains(str.getBound(0)) || !chosenIntersections.contains(str.getBound(1)))){
                        minLength = str.getStreetLength();
                        minLengthStreet = str;
                    }
                }
            }

            chosenStreets.add(minLengthStreet);
            chosenIntersections.add(minLengthStreet.getBound(0));
            chosenIntersections.add(minLengthStreet.getBound(1));
        }

        for(Street it : chosenStreets){
            out.println(it.getStreetName() + "  :  " + it.getStreetLength());
            cost += it.getStreetLength();
        }

        return cost;
    }
}

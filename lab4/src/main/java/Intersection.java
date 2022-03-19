import static java.lang.System.*;
import java.util.ArrayList;
import java.util.List;

public class Intersection {
    private String name;
    private final List<Street> streets = new ArrayList<>();

    public Intersection(String name){
        this.name = name;
    }

    public void addStreet(Street street){
        streets.add(street);
    }

    public List<Street> returnList(){
        return streets;
    }

    public Street getStreet(int index){
        if(index < 0 || index >= streets.size()){
            err.println("Index out of bounds");
            exit(1);
        }
        return streets.get(index);
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Intersection{\n" +
                "name='" + name + '\'' +
                "\n, streets=" + streets +
                "}\n";
    }
}

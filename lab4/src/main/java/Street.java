public class Street implements Comparable<Street>{
    private String streetName;
    private int streetLength;

    public Street(String streetName, int streetLength){
        this.streetName = streetName;
        this.streetLength = streetLength;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public void setStreetLength(int streetLength) {
        this.streetLength = streetLength;
    }

    public String getStreetName() {
        return streetName;
    }

    public int getStreetLength() {
        return streetLength;
    }

    @Override
    public int compareTo(Street toCompare) {
        return Integer.compare(streetLength, toCompare.getStreetLength());
    }

    @Override
    public String toString() {
        return "Street{" +
                "streetName='" + streetName + '\'' +
                ", streetLength=" + streetLength +
                '}';
    }
}

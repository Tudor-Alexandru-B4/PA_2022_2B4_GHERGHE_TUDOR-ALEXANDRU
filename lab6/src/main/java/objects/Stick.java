package objects;

public class Stick {

    private Stone stone1;
    private Stone stone2;

    public Stick(Stone stone1, Stone stone2){
        this.stone1 = stone1;
        this.stone2 = stone2;
    }

    public Stone getStone1() {
        return stone1;
    }

    public Stone getStone2() {
        return stone2;
    }
}

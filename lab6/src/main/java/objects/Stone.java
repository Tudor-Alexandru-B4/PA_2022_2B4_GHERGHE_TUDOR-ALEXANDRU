package objects;

public class Stone {
    private int row;
    private int col;
    private String team;

    public Stone(int row, int col){
        this.row = row;
        this.col = col;
        this.team = "NA";
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }
}

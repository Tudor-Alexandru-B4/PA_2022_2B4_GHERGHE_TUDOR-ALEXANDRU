package objects;

import java.util.*;

public class Board {

    private int rows, cols;
    private final Stone[][] stones = new Stone[100][100];
    private final Map<Stone,List<Stone>> stickMap = new HashMap<>();
    private boolean redTurn;
    private int turn;

    public Board(int rows, int cols){
        this.turn = 0;
        this.redTurn = true;
        this.rows = rows;
        this.cols = cols;
        init();
    }

    private void init(){
        int stickSpaces = (rows - 1) * rows + (cols - 1) * cols;
        int row, col, rNear, cNear, way;
        Random rand = new Random();
        //init stones
        for(int i = 0; i < rows; i++)
            for(int j = 0; j < cols; j++){
                stones[i][j] = new Stone(i, j);
                stickMap.put(stones[i][j],new ArrayList<>());
            }
        //generate sticks
        for(int i = 0; i < 3*stickSpaces/4; i++){
            row = rand.nextInt(rows);
            col = rand.nextInt(cols);
            way = rand.nextInt(4);

            switch (way) {
                case 0 -> {
                    rNear = row - 1;
                    cNear = col;
                }
                case 1 -> {
                    rNear = row;
                    cNear = col + 1;
                }
                case 2 -> {
                    rNear = row + 1;
                    cNear = col;
                }
                default -> {
                    rNear = row;
                    cNear = col - 1;
                }
            }

            if(existsStick(row, col, rNear, cNear)){
                i--;
            }else{
                stickMap.get(stones[row][col]).add(stones[rNear][cNear]);
                stickMap.get(stones[rNear][cNear]).add(stones[row][col]);
            }
        }
    }

    private boolean existsStick(int row1, int col1, int row2, int col2){
        if(row2 < 0 || row2 >= rows || col2 < 0 || col2 >= cols || stickMap.get(stones[row1][col1]).contains(stones[row2][col2]))
            return true;
        return false;
    }

    public void paintStone(int row, int col){
        if(wrongMove(row, col)){
            return;
        }

        if(redTurn){
            stones[row][col].setTeam("red");
        }else{
            stones[row][col].setTeam("blue");
        }
        redTurn = !redTurn;
        turn ++;

        if(!existsMove()){
            redTurn = !redTurn;
            System.out.println(getCurrentTeam() + " wins!");
        }
    }

    private boolean wrongMove(int row, int col){
        if(row < 0 || row >= rows || col < 0 || col >= cols || stones[row][col].getTeam().compareTo("NA") != 0 || !nearSameColor(row, col))
            return true;

        return false;
    }

    private boolean nearSameColor(int row, int col){
        if(turn < 2)
            return true;
        for(Stone it : stickMap.get(stones[row][col])){
            if(it.getTeam().compareTo(getCurrentTeam()) == 0)
                return true;
        }
        return false;
    }

    private boolean existsMove(){
        if(turn < 2)
            return true;
        for(Map.Entry<Stone, List<Stone>> it : stickMap.entrySet()) {
            if(it.getKey().getTeam().compareTo(getCurrentTeam()) == 0){
                for (Stone stone : it.getValue()) {
                    if(stone.getTeam().compareTo("NA") == 0)
                        return true;
                }
            }
        }
        return false;
    }

    private String getCurrentTeam(){
        if(redTurn)
            return "red";
        return "blue";
    }

    public Stone[][] getStones(){
        return stones;
    }

    public Map<Stone, List<Stone>> getStickMap(){
        return stickMap;
    }

    public int getTurn(){
        return turn;
    }

    public void setTurn(int turn){
        this.turn = turn;
    }

    public boolean getPlayerTurn(){
        return redTurn;
    }

    public void setPlayerTurn(boolean turn){
        redTurn = turn;
    }
}

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Bag bag;
    private final Board board;
    private final List<Player> players = new ArrayList<>();

    public Game(String input){
        bag = new Bag(input);
        board = new Board(input);
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public void play(){
        for(Player it : players){
            new Thread(it).start();
        }
        while(!bag.getTiles().isEmpty()){

        }
        for(Player it : players){
            it.stop = true;
        }

        board.printWords();
        board.printRanking();
    }

    public Bag getBag() {
        return bag;
    }

    public Board getBoard() {
        return board;
    }
}

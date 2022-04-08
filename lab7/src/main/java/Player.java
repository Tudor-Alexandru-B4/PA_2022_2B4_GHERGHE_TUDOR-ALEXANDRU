import java.util.concurrent.CopyOnWriteArrayList;

import static java.lang.System.*;

public class Player implements Runnable, Comparable<Player>{
    private final int HANDSIZE = 7;
    private String name;
    private int score = 0;
    private Game game;
    public boolean stop = false;
    CopyOnWriteArrayList<Tile> hand = new CopyOnWriteArrayList<>();

    public Player(Game game, String name){
        this.game = game;
        this.name = name;
    }

    public void run(){
        hand.addAll(game.getBag().extractTiles(HANDSIZE));
        while(!stop){
            showHand();
            String word = "";
            for(Tile it : hand){
                word += it.getLetter();
            }
            if(!word.isEmpty()){
                game.getBoard().addWord(this,word);
            }
            hand.clear();
            hand.addAll(game.getBag().extractTiles(HANDSIZE));
        }
    }

    private void showHand(){
        if(hand.isEmpty())
            return;
        String output = name + "'s hand:\n";
        for(Tile it : hand){
            output += it.getLetter() + " ' ";
        }
        out.println(output);
    }

    public void addScore(int points){
        score += points;
    }

    public String getName() {
        return name;
    }

    public int getScore(){
        return score;
    }

    @Override
    public int compareTo(Player o) {
        return Integer.compare(o.getScore(), score);
    }
}

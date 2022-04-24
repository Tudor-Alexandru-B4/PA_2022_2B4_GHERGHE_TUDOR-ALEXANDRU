import java.util.Scanner;
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
            synchronized (game.currentPlayer){
                try {
                    game.currentPlayer.put(this);
                } catch (InterruptedException e) {
                    try {
                        game.currentPlayer.wait();
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }

            if(stop)
                break;

            String word;
            showHand();
            do{
                Scanner keyboard = new Scanner(in);
                out.print("Enter word ('0' - change hand):");
                word = keyboard.nextLine().toUpperCase();
            }while (word.compareTo("0") != 0 && !validWord(word));

            if(word.compareTo("0") != 0){
                game.getBoard().addWord(this, word);
                removeTiles(word);
            }else{
                for(Tile it : hand){
                    game.getBag().returnTile(it);
                }
                hand.clear();
            }
            hand.addAll(game.getBag().extractTiles(HANDSIZE - hand.size()));

            try {
                game.currentPlayer.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (game.currentPlayer){
                game.currentPlayer.notify();
            }
        }
    }

    private void removeTiles(String word){
        for(int i = 0; i < word.length(); i++)
            for(Tile it : hand)
                if(it.getLetter() == word.charAt(i)){
                    hand.remove(it);
                    break;
                }
    }

    private boolean validWord(String word){
        return game.getDictionary().isWord(word) && hasTiles(word);
    }

    private boolean hasTiles(String word){
        if(word.length() <= 0 || word.length() > 7)
            return false;
        for(int i = 0; i < word.length(); i++){
            boolean hasLetter = false;
            for(Tile it : hand)
                if(it.getLetter() == word.charAt(i)){
                    hasLetter = true;
                    break;
                }
            if(!hasLetter)
                return false;
        }
        return true;
    }

    private void showHand(){
        if(hand.isEmpty())
            return;
        String output = "\n" + name + "'s hand:\n";
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

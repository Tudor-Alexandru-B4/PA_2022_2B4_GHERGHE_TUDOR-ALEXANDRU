import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class Game {
    private final Bag bag;
    private final Board board;
    private final Dictionary dictionary;
    private final List<Player> players = new ArrayList<>();
    private int time;
    public LinkedBlockingQueue<Player> currentPlayer = new LinkedBlockingQueue<>(1);
    public final LinkedBlockingQueue<Integer> blocker = new LinkedBlockingQueue<>(1);

    public Game(int time, String input, String dex){
        this.time = time;
        bag = new Bag(input, this);
        board = new Board(input);
        dictionary = new Dictionary(dex);
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public void play(){
        board.setUpPlayers(players);
        for(Player it : players){
            new Thread(it).start();
        }
        startTimer();
        synchronized (blocker){
            try {
                blocker.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for(Player it : players){
            it.stop = true;
        }

        board.printWords();
        board.printRanking();
        System.exit(0);
    }

    private void startTimer(){
        Timer timekeeper = new Timer(true);
        timekeeper.schedule(new TimerTask() {
            @Override
            public void run() {
                synchronized (blocker){
                    blocker.notify();
                }
            }
        }, time * 1000);
    }

    public Bag getBag() {
        return bag;
    }

    public Board getBoard() {
        return board;
    }

    public Dictionary getDictionary() {
        return dictionary;
    }
}

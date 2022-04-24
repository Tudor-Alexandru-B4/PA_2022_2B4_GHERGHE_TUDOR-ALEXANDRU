import java.io.File;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.lang.System.*;

public class Bag {
    private final CopyOnWriteArrayList<Tile> tiles = new CopyOnWriteArrayList<>();
    private final Game game;
    private Random rand = new Random();

    public Bag(String input, Game game){
        init(input);
        this.game = game;
    }

    private void init(String input){
        try{
            try (Scanner reader = new Scanner(new File(input))) {
                while (reader.hasNextLine()) {
                    String[] data = reader.nextLine().split(" ");
                    for(int i = 0; i < Integer.parseInt(data[2]); i++)
                        tiles.add(new Tile(data[0].charAt(0), Integer.parseInt(data[1])));
                }
            }
        }catch (Exception e){
            err.println(e);
        }
    }

    public synchronized List<Tile> extractTiles(int howMany){
        List<Tile> extracted = new ArrayList<>();
        for(int i = 0; i < howMany; i++){
            if(tiles.isEmpty()) {
                forceExit();
                break;
            }
            int index = rand.nextInt(tiles.size());
            extracted.add(tiles.get(index));
            tiles.remove(index);
        }
        return extracted;
    }

    private void forceExit(){
        synchronized (game.blocker){
            game.blocker.notifyAll();
        }
    }
    public synchronized void returnTile(Tile tile){
        tiles.add(tile);
    }

    public CopyOnWriteArrayList<Tile> getTiles(){
        return tiles;
    }
}

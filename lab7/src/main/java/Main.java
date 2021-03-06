import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args){
        Game game = new Game(30, "target/input.txt", "target/dictionary");
        var players = IntStream.rangeClosed(1,3).mapToObj(i -> new Player(game, "Player" + i)).toArray(Player[]::new);
        IntStream.rangeClosed(0,2).forEach(i -> game.addPlayer(players[i]));
        game.play();
    }
}
import java.io.File;
import java.util.*;

import static java.lang.System.*;

public class Board {
    private final Map<Player, List<String>> words = new HashMap<>();
    private final Map<Character, Integer> scoreTable = new HashMap<>();

    public Board(String input){
        init(input);
    }

    private void init(String input){
        try{
            try (Scanner reader = new Scanner(new File(input))) {
                while (reader.hasNextLine()) {
                    String[] data = reader.nextLine().split(" ");
                    scoreTable.put(data[0].charAt(0), Integer.parseInt(data[1]));
                }
            }
        }catch (Exception e){
            err.println(e);
        }
    }

    public void setUpPlayers(List<Player> players){
        for(Player it : players)
            words.put(it, new ArrayList<>());
    }

    public synchronized void addWord(Player player, String word){
        words.get(player).add(word);
    }

    public void printWords(){
        for(Player player : words.keySet()){
            out.println("\n\n" + player.getName() + "'s words:");
            for(String word : words.get(player)){
                out.print(word + ",  ");
            }
        }
        out.println();
    }

    public void printRanking(){
        List<Player> ranking = new ArrayList<>();
        for(Player player : words.keySet()){
            for(String word : words.get(player)){
                player.addScore(pointsForWord(word));
            }
            ranking.add(player);
        }

        Collections.sort(ranking);
        out.println("\n\n\nThe final rankings:\n```````````````````");
        for(int i = 1; i <= ranking.size(); i++){
            out.println(i + "." + ranking.get(i-1).getName() + " : " + ranking.get(i-1).getScore() + "\n");
        }
    }

    private int pointsForWord(String word){
        int points = 0;
        for(int i = 0; i < word.length(); i++){
            points += scoreTable.get(word.charAt(i));
        }
        return points;
    }
}

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

import static java.lang.System.*;

public class Dictionary {
    private final ConcurrentHashMap<String, Boolean> dex = new ConcurrentHashMap<>();

    public Dictionary(String inputFile){
        try{
            try (Scanner reader = new Scanner(new File(inputFile))) {
                while (reader.hasNextLine()) {
                    String newWord = reader.nextLine();
                    if(newWord.length() <= 7)
                        dex.put(newWord.toUpperCase(), false);
                }
            }
        }catch (Exception e){
            err.println(e);
        }
    }

    public Boolean isWord(String word){
        return dex.get(word.toUpperCase()) != null;
    }
}

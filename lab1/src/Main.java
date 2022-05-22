import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void compulsory(){
        System.out.println("\n\nHello World!");
        String[] languages=new String[] {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
        int n = (int) (Math.random() * 1_000_000);

        n *= 3;
        String binary = "10101";
        int bin1 = Integer.parseInt(binary,2);

        n += bin1;
        String hexadec = "FF";
        int hex1 = Integer.parseInt(hexadec,16);

        n += hex1;
        n *= 6;
        while(n > 9) {
            int sum = 0;
            while (n > 9) {
                sum += n / 10;
                n %= 10;
            }
            sum += n;
            n = sum;
        }
        System.out.println("Willy-nilly, this semester I will learn " + languages[n] + "\n");
    }

    public static boolean generatingNeighbors(String str1, String str2){
        for(int i = 0; i < str1.length(); i++){
            if(str2.contains(String.valueOf(str1.charAt(i)))){
                return true;
            }
        }
        return false;
    }

    public static void homework(String[] args){
        long begin = System.nanoTime();

        //Argument validation
        if(args.length < 3){
            System.err.println("Not Enough Arguments");
            System.exit(1);
        }
        int n = 0;

        try{
            n=Integer.parseInt(args[0]);
        }catch(NumberFormatException nfe){
            System.err.println("First Argument Not an Integer");
            System.exit(2);
        }
        int p = 0;

        try{
            p=Integer.parseInt(args[1]);
        }catch(NumberFormatException nfe){
            System.err.println("Secound Argument Not an Integer");
            System.exit(3);
        }
        ArrayList<Character> alph = new ArrayList<Character>();

        for(int i = 2; i < args.length; i++){
            if(args[i].length() > 1 || args[i].charAt(0) < 'A' || args[i].charAt(0) > 'Z'){
                System.err.println("Argument no." + (i + 1) + " Not a Letter");
                System.exit((i + 1));
            }else{
                alph.add(args[i].charAt(0));
            }
        }

        //Generating words array

        //Bonus Example
        //n=8;
        //String[] words = {"AT", "AM", "MA", "AT", "AC", "CC", "CM", "MA"};
        String[] words = new String[n];

        for(int i = 0; i < n; i++){
            words[i] = new String();
            for(int j = 0; j < p; j++){
                Random rand = new Random();
                int randInt = rand.nextInt(alph.size());
                words[i]+=String.valueOf(alph.get(randInt));
            }
        }
        for(int i = 0; i < n; i++){
            System.out.println(words[i]);
        }
        System.out.println("\n");

        //Generating generatingNeighbors matrix
        boolean[][] generatingNeighbors = new boolean[n][n];

        for(int i = 0; i < words.length; i++){
            for(int j = 0; j < words.length; j++){
                generatingNeighbors[i][j] = generatingNeighbors(words[i], words[j]);
            }
        }

        //Generating DataStructure
        ArrayList<ArrayList<String>> dataStructure = new ArrayList<ArrayList<String>>();
        for(int i = 0; i < words.length; i++) {
            ArrayList<String> tempArr = new ArrayList<String>();
            for (int j = 0; j < words.length; j++) {
                if(generatingNeighbors[i][j] == true){
                    tempArr.add(words[j]);
                }
            }
            dataStructure.add(tempArr);
        }
        long end = System.nanoTime();
        long time = end-begin;

        if(n<51){
            for(int i = 0; i < n; i++){
                System.out.print(words[i] + ": ");
                for(String index: dataStructure.get(i)){
                    System.out.print(index + " ");
                }
                System.out.println("");
            }
        }else{
            System.out.println("Running time: " + time + " nanoseconds");
        }

        //cd C:\Users\tudor\PA_2022_2B4_GHERGHE_TUDOR-ALEXANDRU\lab1\src
        //java Main.java 100 7 A C G T

        Bonus(n,generatingNeighbors,words);
    }

    public static void Bonus(int n, boolean[][] generatingNeighbors, String[] words){
        Integer[] lmax = new Integer[n];
        Boolean[] finished = new Boolean[n];
        int max = -1, imax = -1;

        lmax[0] = 1;
        finished[0] = false;
        for(int i = 1; i < n; i++){
            lmax[i] = 1;
            finished[i] = false;
            for(int j = 0; j < i; j++){
                if(!finished[j]){
                    if(words[j] == words[i] && max < lmax[j]){
                        max = lmax[j];
                        imax = j;
                    }
                    if(generatingNeighbors[i - 1][i]){
                        lmax[j]++;
                    }else{
                        finished[j] = true;
                    }
                }
            }
        }
        if(max > 2){
            System.out.println("\n\nFound subset of length " + max + " from index " + imax + ":");
            for(int i = 0; i < max; i++){
                System.out.print(words[imax + i] + " ");
            }
        }else{
            System.out.println("\n\nSubset longer than 2 not found");
        }
    }

    @Test
    public static void callMe(){
        System.out.println("Hello from the other lab!");
    }

    public static void main(String[] args) {
        compulsory();
        homework(args);

        //Bonus - is called from the homework method
    }
}

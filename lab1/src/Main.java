import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void Compulsory(){
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

        while(n>9) {
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

    public static boolean Neighbors(String str1, String str2){
        for(int i=0;i<str1.length();i++){
            if(str2.contains(String.valueOf(str1.charAt(i)))){
                return true;
            }
        }
        return false;
    }

    public static void Homework(String[] args){
        long begin = System.nanoTime();

        //Argument validation

        if(args.length<3){
            System.err.println("Not Enough Arguments");
            System.exit(1);
        }

        int n=0;
        try{
            n=Integer.parseInt(args[0]);
        }catch(NumberFormatException nfe){
            System.err.println("First Argument Not an Integer");
            System.exit(2);
        }

        int p=0;
        try{
            p=Integer.parseInt(args[1]);
        }catch(NumberFormatException nfe){
            System.err.println("Secound Argument Not an Integer");
            System.exit(3);
        }

        ArrayList<Character> alph = new ArrayList<Character>();
        for(int i=2;i< args.length;i++){
            if(args[i].length()>1 || args[i].charAt(0)<'A' || args[i].charAt(0)>'Z'){
                System.err.println("Argument no." + (i+1) + " Not a Letter");
                System.exit((i+1));
            }else{
                alph.add(args[i].charAt(0));
            }
        }

        //Generating words array

        String[] words = new String[n];

        for(int i=0;i<n;i++){
            words[i] = new String();
            for(int j=0;j<p;j++){
                Random rand = new Random();
                int randInt = rand.nextInt(alph.size());
                words[i]+=String.valueOf(alph.get(randInt));
            }
        }

        for(int i=0;i<n;i++){
            System.out.println(words[i]);
        }
        System.out.println("\n");

        //Generating neighbors matrix

        boolean[][] neighbors = new boolean[n][n];

        for(int i=0;i< words.length;i++){
            for(int j=0;j< words.length;j++){
                neighbors[i][j] = Neighbors(words[i],words[j]);
            }
        }

        //Generating DataStructure

        ArrayList<ArrayList<String>> dataStructure = new ArrayList<ArrayList<String>>();
        for(int i=0;i< words.length;i++) {
            ArrayList<String> tempArr= new ArrayList<String>();
            for (int j = 0; j < words.length; j++) {
                if(neighbors[i][j]==true){
                    tempArr.add(words[j]);
                }
            }
            dataStructure.add(tempArr);
        }

        long end = System.nanoTime();
        long time = end-begin;

        if(n<51){
            for(int i=0;i<n;i++){
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

        Bonus(n,neighbors,words);

    }

    public static void Bonus(int n, boolean[][] neighbors, String[] words){
        Integer[] lmax = new Integer[n];
        Boolean[] finished = new Boolean[n];
        lmax[0]=1;
        finished[0]=false;
        for(int i=1;i<n;i++){
            lmax[i]=1;
            finished[i]=false;
            for(int j=0;j<i;j++){
                if(finished[j]==false){
                    if(neighbors[i-1][i]==true){
                        lmax[j]++;
                    }else{
                        finished[j]=true;
                    }
                }
            }
        }

        int max=-1,imax=-1;
        for(int i=0;i<n;i++){
            if(lmax[i]>max){
                max=lmax[i];
                imax=i;
            }
        }

        if(max>3){
            System.out.println("\n\nFound subset of length " + max + " from index " + imax + ":");
            for(int i=0;i<max;i++){
                System.out.print(words[imax+i] + " ");
            }
        }else{
            System.out.println("\n\nSubset longer than 3 not found");
        }
    }

    public static void main(String[] args) {
        Compulsory();
        Homework(args);
        //Bonus - is called from the Homework method
    }
}
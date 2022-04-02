package model;

import commands.*;
import com.github.javafaker.Faker;
import java.util.Date;
import java.util.Random;
import java.util.stream.IntStream;

import static java.lang.System.*;

public class Main {
    public static void main(String[] args) {
        Faker faker = new Faker();
        Random rand = new Random();
        String[] location = {
                "C:\\Users\\tudor\\Desktop\\nothing.url",
                "C:\\Users\\tudor\\Desktop\\HTML\\Lab 1",
                "C:\\Users\\tudor\\Desktop\\ulei.url",
                "D:\\PROGRAME\\JAD",
                "D:\\GAMES\\Riot Games\\Riot Client\\Resources",
                "C:\\Games",
                "C:\\Users\\tudor\\Desktop\\generare.url",
                "C:\\Users\\tudor\\Desktop\\broken.url"
        };

        var books = IntStream.rangeClosed(1,4).mapToObj(i -> new Book(faker.idNumber().valid(), faker.book().title(), location[i - 1], 1900 + rand.nextInt(100), faker.book().author())).toArray(Book[]::new);
        var articles = IntStream.rangeClosed(5,8).mapToObj(i -> new Article(faker.idNumber().valid(), faker.book().title(), location[i - 1], faker.date().between(new Date(1900,1,1), new Date(2022,1,1)).toString(), faker.book().author(), rand.nextInt(501))).toArray(Article[]::new);

        Catalog catalog = new Catalog();
        AddCommand add = new AddCommand();
        IntStream.rangeClosed(0,3).forEach(i -> add.command(catalog, books[i]));
        IntStream.rangeClosed(0,3).forEach(i -> add.command(catalog, articles[i]));

        toStringCommand toStr = new toStringCommand();
        toStr.command(catalog);

        SaveCommand save = new SaveCommand();
        try{
            save.command(catalog,"target/catalog.json");
        }catch (Exception e){
            err.println(e);
        }

        Catalog loader = new Catalog();
        LoadCommand load = new LoadCommand();
        ListCommand list = new ListCommand();
        try{
            load.command(loader,"target/catalog.json");
        }catch (Exception e){
            err.println(e);
        }
        list.command(loader);

        ViewCommand view = new ViewCommand();
        try{
            view.command(catalog,0);
        }catch (Exception e){
            err.println(e);
        }

        ReportCommand report = new ReportCommand();
        try{
            report.command(catalog);
        }catch (Exception e){
            err.println(e);
        }
    }
}

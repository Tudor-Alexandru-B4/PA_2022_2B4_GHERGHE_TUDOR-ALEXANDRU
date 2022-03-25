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
                "https://www.youtube.com/watch?v=dQw4w9WgXcQ",
                "C:\\Users\\tudor\\Desktop\\HTML\\Lab 1",
                "https://www.emag.ro/ulei-de-floarea-soarelui-unisol-2l-5948990130251/pd/D28BNMMBM/?ref=fam#2-l",
                "D:\\PROGRAME\\JAD",
                "D:\\GAMES\\Riot Games\\Riot Client\\Resources",
                "C:\\Games",
                "https://randomwordgenerator.com/picture.php",
                "https://mail.info.uaic.ro/?_task=mail&_mbox=INBOX"
        };

        var books = IntStream.rangeClosed(1,4).mapToObj(i -> new Book(faker.idNumber().valid(), faker.book().title(), location[i - 1], 1900 + rand.nextInt(100), faker.book().author())).toArray(Book[]::new);
        var articles = IntStream.rangeClosed(5,8).mapToObj(i -> new Article(faker.idNumber().valid(), faker.book().title(), location[i - 1], faker.date().between(new Date(1900,1,1), new Date(2022,1,1)).toString(), faker.book().author(), rand.nextInt(501))).toArray(Article[]::new);

        Catalog catalog = new Catalog();
        IntStream.rangeClosed(0,3).forEach(i -> catalog.add(books[i]));
        IntStream.rangeClosed(0,3).forEach(i -> catalog.add(articles[i]));

        catalog.save("target/catalog.json");

        Catalog loader = new Catalog();
        loader.load("target/catalog.json");

        out.println(loader);
    }
}

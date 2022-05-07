package others;

import DAO.CityDAO;
import DAO.ContinentDAO;
import DAO.CountryDAO;

import java.sql.SQLException;

import static java.lang.System.*;

public class Main {
    public static void main(String[] args) {

        Database db = new Database();

//        ContinentDAO continent = new ContinentDAO(db);
//        try {
//            continent.create("abcd");
//            out.println(continent.findById(7));
//            out.println(continent.findByName("abcde"));
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        out.println();
//
//        CountryDAO country = new CountryDAO(db);
//        try {
//            country.create("test", "NULL", "Antartica");
//            out.println(country.findById(10));
//            out.println(country.findByName("test"));
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        out.println();
//
//        CityDAO city = new CityDAO(db);
//        try{
//            city.create("test", "Romania", false, 45.407379, 25.497711);
//            out.println(city.findById(3));
//            out.println(city.findByName("Busteni"));
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

//        DataLoader dl = new DataLoader(db, "target/concap.csv");
//        try {
//            dl.load();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }

//        CityDAO city = new CityDAO(db);
//        try {
//            out.println(city.distanceCalc("Moscow", "London"));
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

        db.closeConnection();
    }
}

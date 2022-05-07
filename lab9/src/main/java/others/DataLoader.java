package others;

import DAO.CityDAO;
import DAO.CountryDAO;

import java.io.File;
import java.sql.Connection;
import java.util.Scanner;

public class DataLoader {

    private Database db;
    private String inputFile;

    public DataLoader(Database db, String inputFile){
        this.db = db;
        this.inputFile = inputFile;
    }

    public void load() throws Exception{
        CountryDAO country = new CountryDAO(db);
        CityDAO city = new CityDAO(db);

        File file = new File(inputFile);
        Scanner reader = new Scanner(file);
        reader.nextLine();      //header

        while(reader.hasNextLine()){
            String line = reader.nextLine();
            //System.out.println(line);
            String data[] = line.split(",");

            country.create(quoteHandler(data[0]), data[4], data[5]);
            if(data[1].compareTo("N/A") != 0)
                city.create(quoteHandler(data[1]),quoteHandler(data[0]),true, Math.round(Double.parseDouble(data[2])*1000000.0)/1000000.0, Math.round(Double.parseDouble(data[3])*1000000.0)/1000000.0);
        }
    }

    private String quoteHandler(String string){
        string = string.replace("'","_");
        return string;
    }

}

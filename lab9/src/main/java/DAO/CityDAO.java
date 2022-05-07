package DAO;

import others.Database;

import java.sql.*;
import java.util.ArrayList;

import static java.lang.System.*;

public class CityDAO {

    private static Connection connection;

    public CityDAO(Database db){
        connection = db.getConnection();
    }

    public void create(String name, String country, boolean isCapital, double latitude, double longitude) throws SQLException {
        if(latitude < -90 || latitude > 90){
            out.println("latitude out of bounds[-90,90]");
            return;
        }
        if(longitude < -180 || longitude > 180){
            out.println("longitude out of bounds[-180,180]");
            return;
        }

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(
                "SELECT name FROM cities WHERE name='" + name + "'"
        );
        if(rs.next()){
            out.println("Tuple already exists");
            return;
        }
        rs.close();

        rs = stmt.executeQuery(
                "SELECT id FROM countries WHERE name='" + country + "'"
        );
        if(!rs.next()){
            out.println("Country doesn't exist");
            return;
        }
        int countryId = rs.getInt(1);

        PreparedStatement pstmt = connection.prepareStatement(
                "INSERT INTO cities SELECT COUNT(*)+1,?,?,?,?,? FROM cities"
        );

        pstmt.setInt(1, countryId);
        pstmt.setString(2, name);
        pstmt.setInt(3, isCapital ? 1 : 0);
        pstmt.setDouble(4, latitude);
        pstmt.setDouble(5, longitude);
        pstmt.executeUpdate();
        pstmt.close();
        rs.close();
        stmt.close();
    }

    public Integer findByName(String name) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(
                "SELECT id FROM cities WHERE name='" + name + "'"
        );
        Integer toReturn = rs.next() ? rs.getInt(1) : null;
        rs.close();
        stmt.close();
        return toReturn;
    }

    public String findById(int id) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(
                "SELECT name FROM cities WHERE id='" + id + "'"
        );
        String toReturn = rs.next() ? rs.getString(1) : null;
        rs.close();
        stmt.close();
        return toReturn;
    }

    public Double distanceCalc(String city1, String city2) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(
                "SELECT latitude, longitude FROM cities WHERE name='" + city1 + "'"
        );
        if(!rs.next()){
            out.println("First city does not exist");
            return -1d;
        }
        double lat1 = Math.toRadians(rs.getDouble(1));
        double lon1 = Math.toRadians(rs.getDouble(2));
        rs.close();

        rs = stmt.executeQuery(
                "SELECT latitude, longitude FROM cities WHERE name='" + city2 + "'"
        );
        if(!rs.next()){
            out.println("Second city does not exist");
            return -1d;
        }
        double lat2 = Math.toRadians(rs.getDouble(1));
        double lon2 = Math.toRadians(rs.getDouble(2));
        rs.close();

        stmt.close();

        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2),2);

        double c = 2 * Math.asin(Math.sqrt(a));
        double r = 6371;

        return(c * r);
    }
}

package DAO;

import others.Database;

import java.sql.*;
import java.util.Locale;

import static java.lang.System.*;

public class CountryDAO {

    private static Connection connection;

    public CountryDAO(Database db){
        connection = db.getConnection();
    }

    public void create(String name, String code, String continent) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(
                "SELECT name FROM countries WHERE name='" + name + "'"
        );
        if(rs.next()){
            out.println("Tuple already exists");
            return;
        }
        rs.close();

        rs = stmt.executeQuery(
                "SELECT id FROM continents WHERE name='" + continent + "'"
        );
        if(!rs.next()){
            out.println("Continent doesn't exist");
            return;
        }
        int continentId = rs.getInt(1);

        PreparedStatement pstmt = connection.prepareStatement(
                "INSERT INTO countries SELECT COUNT(*)+1,?,?,? FROM countries"
        );

        pstmt.setString(1, name);
        pstmt.setString(2, code);
        pstmt.setInt(3, continentId);
        pstmt.executeUpdate();
        pstmt.close();
        rs.close();
        stmt.close();
    }

    public Integer findByName(String name) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(
                "SELECT id FROM countries WHERE name='" + name + "'"
        );
        Integer toReturn = rs.next() ? rs.getInt(1) : null;
        rs.close();
        stmt.close();
        return toReturn;
    }

    public String findById(int id) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(
                "SELECT name FROM countries WHERE id='" + id + "'"
        );
        String toReturn = rs.next() ? rs.getString(1) : null;
        rs.close();
        stmt.close();
        return toReturn;
    }

}

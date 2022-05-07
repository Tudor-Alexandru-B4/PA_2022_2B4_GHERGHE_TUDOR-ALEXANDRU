package DAO;

import others.Database;
import java.sql.*;

import static java.lang.System.*;

public class ContinentDAO {

    private static Connection connection;

    public ContinentDAO(Database db){
        connection = db.getConnection();
    }

    public void create(String name) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(
                "SELECT name FROM continents WHERE name='" + name + "'"
        );
        if(rs.next()){
            out.println("Tuple already exists");
            return;
        }

        PreparedStatement pstmt = connection.prepareStatement(
                "INSERT INTO continents SELECT COUNT(*)+1, ? FROM continents"
        );

        pstmt.setString(1, name);
        pstmt.executeUpdate();
        pstmt.close();
        rs.close();
        stmt.close();
    }

    public Integer findByName(String name) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(
                "SELECT id FROM continents WHERE name='" + name + "'"
        );
        Integer toReturn = rs.next() ? rs.getInt(1) : null;
        rs.close();
        stmt.close();
        return toReturn;
    }

    public String findById(int id) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(
                "SELECT name FROM continents WHERE id='" + id + "'"
        );
        String toReturn = rs.next() ? rs.getString(1) : null;
        rs.close();
        stmt.close();
        return toReturn;
    }

}

package Networking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=public";
    private String dbname = "postgres";
    private String password = "bakaVeki95";

    public DBConnection()
    {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        }
        catch (SQLException e)
        {
            System.out.println("Errooorrr");
            e.printStackTrace();
        }
    }
    public Connection getDBConnection() throws SQLException
    {
        return DriverManager.getConnection(url, dbname, password);
    }
}

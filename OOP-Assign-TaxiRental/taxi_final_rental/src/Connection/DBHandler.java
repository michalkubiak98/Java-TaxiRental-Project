package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHandler {

    private Connection dbconnection;

    public Connection getConnection() throws SQLException, ClassNotFoundException {


        //Protected information is substituted here from the Config class
        String connectionString = "jdbc:mysql://" + Config.host + ":" + Config.port + "/" + Config.database;

        Class.forName("com.mysql.jdbc.Driver");

        dbconnection = DriverManager.getConnection(connectionString, Config.user, Config.pass);


        return dbconnection;

    }
}


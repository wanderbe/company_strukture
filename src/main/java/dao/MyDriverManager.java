package dao;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by wanderbe on 27.06.2016.
 */
public class MyDriverManager {
    private static final String URL = "jdbc:mysql://localhost:3306/companystructure_db";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "";
    private static Driver driver; // todo: must be final

    private MyDriverManager(){
    }

    public static Connection getConnection() throws SQLException {
        driver = getDriver();
        driver.acceptsURL(URL);

        Properties properties = new Properties();
        properties.put("user", LOGIN);
        properties.put("pasword", PASSWORD);

        Connection conn = driver.connect(URL, properties);

        return conn;
    }

    synchronized private static Driver getDriver() throws SQLException{
        if(driver == null){
            driver = new com.mysql.jdbc.Driver();
        }
        return driver;
    }
}

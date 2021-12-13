package entity.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Logger;

import utils.Utils;

public class EcobikeDB {
	private static Logger LOGGER = Utils.getLogger(Connection.class.getName());
	private static Connection connect;

    public static Connection getConnection() {
        if (connect != null) return connect;
        try {
        	Class.forName("com.mysql.jdbc.Driver"); 
        	String url = "jdbc:mysql://localhost:3306/ecobike_rental";
        	connect =DriverManager.getConnection(url, "root", "");  
            LOGGER.info("Connect database successfully");
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
        } 
        return connect;
    }
}

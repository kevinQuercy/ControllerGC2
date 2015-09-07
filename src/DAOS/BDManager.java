package DAOS;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;


public class BDManager {
	private static String url = "jdbc:mysql://localhost:3306/garbagecollector";
    //private static String url = "jdbc:mysql://192.168.20.2:3306/garbagecollector";
    private static String driver = "com.mysql.jdbc.Driver";
    private static String login = "root";
    private static String password = "";
    
    public static Connection getConnexion() throws Exception{
        Class c = Class.forName(driver);
        Driver d = (Driver) c.newInstance();
        DriverManager.registerDriver(d);
        Connection cnx = DriverManager.getConnection(url, login, password);
        return cnx;
    }
}

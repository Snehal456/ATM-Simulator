
// This class handles the MySQL JDBC connection.
import java.sql.*;

public class Conn {
    public Connection c;
    public Statement s;

    public Conn() {
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to MySQL with correct password
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm_db", "root", "Root@123");

            // Create a statement object
            s = c.createStatement();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

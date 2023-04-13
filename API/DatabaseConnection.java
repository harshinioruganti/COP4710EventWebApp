import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private Connection connection;
    private final String url = "jdbc:oracle:thin:@localhost:1521:orcl"; // our database URL
    private final String username = "sys";
    private final String password = "harshini";

    public Connection getConnection() {
        try
        {
            // Load the JDBC driver
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
            // Establish the database connection
            connection = DriverManager.getConnection(url, username, password);
            return connection;
        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}

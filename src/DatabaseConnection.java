
import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    String databaseName = "RegistrarDB";
    String user = "Registrar";
    String password = "admin";
    static Connection con;

    public DatabaseConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=" + databaseName + ";encrypt=true;trustServerCertificate=true";
            con = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.err.println("Error connection: " + e.getMessage());
        }
    }

}

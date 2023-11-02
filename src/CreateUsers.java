import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateUsers {
    public static String getSecurePassword(String password

    ) {

        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/printer_users_db", "root", "94NkIBdasNKtyO0");
        return connection;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, SQLException {
        String password = getSecurePassword("HellowCrikField123");
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            statement.execute("insert into users (username, password) values('user1','"
                    + password + "')");
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}

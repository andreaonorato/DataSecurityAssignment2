package auth;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class AuthService extends UnicastRemoteObject implements AuthServiceI {
    public AuthService() throws RemoteException {
        super();
    }

    @Override
    public String generateToken(String username, String password) throws RemoteException {
        String token = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/printer_users_db", "root", "94NkIBdasNKtyO0");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from users where password ='" + username + "'");

            while (resultSet.next()) {
                    long currentTimeInMilisecond = Instant.now().plus(5, ChronoUnit.MINUTES).toEpochMilli();
                    token = currentTimeInMilisecond + "-" + UUID.randomUUID().toString();
                    statement.execute("update users set token = '" + token + "' where password ='" + password+"'");

            }
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return token;
    }

    @Override
    public boolean validateToken(String token) throws RemoteException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/printer_users_db", "root", "94NkIBdasNKtyO0");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from users where token ='" + token + "'");
            if (resultSet.next()) {
                String storedToken = resultSet.getString("token");
                if (storedToken != null) {
                    int index = storedToken.indexOf("-");
                    Instant expiry = Instant.ofEpochMilli(Long.parseLong(storedToken.substring(0, index)));
                    if (Instant.now().isAfter(expiry))
                        return false;

                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return true;
    }
}

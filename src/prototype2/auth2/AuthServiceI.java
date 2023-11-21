package prototype2.auth2;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AuthServiceI extends Remote {

  //verify username password and generate auth token  
 public String generateToken (String username, String password) throws RemoteException;

 //validate token
 public boolean validateToken (String token) throws RemoteException;

 public String getUsername(String token) throws RemoteException;
 
}
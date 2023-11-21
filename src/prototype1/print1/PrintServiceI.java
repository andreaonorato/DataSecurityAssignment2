package prototype1.print1;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import prototype1.auth1.AuthServiceI;

public interface PrintServiceI extends Remote {
    //get auth rmi instance;
    public AuthServiceI connectAuth() throws NotBoundException, MalformedURLException, RemoteException;
    // prints file filename on the specified printer
   public boolean printFile(String filename, String printer,String token) throws RemoteException;
   // lists the print queue for a given printer on the user's display in lines of the form <job number>   <file name>
   public boolean printQueue(String printer, String token) throws RemoteException;
   // moves job to the top of the queue
   public boolean topQueue(String printer, int job,String token) throws RemoteException;
   // starts the print server
   public boolean start(String token) throws RemoteException; 
   // stops the print server  
   public boolean stop(String token) throws RemoteException;
   // stops the print server, clears the print queue and starts the print server again
   public boolean restart(String token) throws RemoteException;
   // prints status of printer on the user's display
   public boolean status(String printer, String token) throws RemoteException;
   // prints the value of the parameter on the print server to the user's display
   public boolean readConfig(String parameter, String token) throws RemoteException;
   // sets the parameter on the print server to value
   public boolean setConfig(String parameter, String value, String token) throws RemoteException;
    
}

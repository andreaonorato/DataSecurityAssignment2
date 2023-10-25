import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PrintServiceI extends Remote {
    // prints file filename on the specified printer
   public void printFile(String filename, String printer) throws RemoteException;
   // lists the print queue for a given printer on the user's display in lines of the form <job number>   <file name>
   public void printQueue(String printer) throws RemoteException;
   // moves job to the top of the queue
   public void topQueue(String printer, int job) throws RemoteException;
   // starts the print server
   public void start() throws RemoteException; 
   // stops the print server  
   public void stop() throws RemoteException;
   // stops the print server, clears the print queue and starts the print server again
   public void restart() throws RemoteException;
   // prints status of printer on the user's display
   public void status(String printer) throws RemoteException;
   // prints the value of the parameter on the print server to the user's display
   public void readConfig(String parameter) throws RemoteException;
   // sets the parameter on the print server to value
   public void setConfig(String parameter, String value) throws RemoteException;
    
}

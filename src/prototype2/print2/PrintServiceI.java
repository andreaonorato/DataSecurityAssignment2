package prototype2.print2;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import prototype2.auth2.AuthServiceI;

public interface PrintServiceI extends Remote {
    // get auth rmi instance;
    public AuthServiceI connectAuth() throws NotBoundException, MalformedURLException, RemoteException;

    // prints file filename on the specified printer
    public String printFile(String filename, String printer, String token) throws RemoteException;

    // lists the print queue for a given printer on the user's display in lines of
    // the form <job number> <file name>
    public String printQueue(String printer, String token) throws RemoteException;

    // moves job to the top of the queue
    public String topQueue(String printer, int job, String token) throws RemoteException;

    // starts the print server
    public String start(String token) throws RemoteException;

    // stops the print server
    public String stop(String token) throws RemoteException;

    // stops the print server, clears the print queue and starts the print server
    // again
    public String restart(String token) throws RemoteException;

    // prints status of printer on the user's display
    public String status(String printer, String token) throws RemoteException;

    // prints the value of the parameter on the print server to the user's display
    public String readConfig(String parameter, String token) throws RemoteException;

    // sets the parameter on the print server to value
    public String setConfig(String parameter, String value, String token) throws RemoteException;

        // prints the value of the parameter on the print server to the user's display
    public String setUserStatus(String token,String username,String status) throws RemoteException;

        // prints the value of the parameter on the print server to the user's display
    public String changeUserRole(String token,String username, String role) throws RemoteException;
        // prints the value of the parameter on the print server to the user's display
    public String addMethodToRole(String token, String role,String method) throws RemoteException;
        // prints the value of the parameter on the print server to the user's display
    public String RemoveMethodFromRole(String token, String role,String method) throws RemoteException;

}

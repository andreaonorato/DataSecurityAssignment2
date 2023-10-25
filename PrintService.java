import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PrintService extends UnicastRemoteObject implements PrintServiceI{
    public PrintService() throws RemoteException
    {
        super();
    }
    @Override
    public void printFile(String filename, String printer) throws RemoteException {
        System.out.println("file name: "+filename+"printer: "+printer);
        
    }
    @Override
    public void printQueue(String printer) throws RemoteException {
        System.out.println("print queue:\n1  abc.pdf\n2  def.pdf\n3  demo.txt");
        
    }
    
    @Override
    public void topQueue(String printer, int job) throws RemoteException{

        System.out.println("Job: "+job+" moved to top of the queue for printer: "+printer);
    }

    @Override
    public void start() throws RemoteException {
        System.out.println("print server started");
    }

    @Override
    public void stop() throws RemoteException {
        System.out.println("print server stopped");
    }

    @Override
    public void restart() throws RemoteException{
        System.out.println("print server restarted");
    }

    @Override
    public void status(String printer) throws RemoteException{
        System.out.println(printer+" status: printing...");
    }

    @Override
    public void readConfig(String parameter) throws RemoteException {
        System.out.println("read parameter: "+parameter);
    }

    @Override
    public void setConfig(String parameter, String value) throws RemoteException{
        System.out.println("set parameter "+parameter+" with value "+value);
    }
}

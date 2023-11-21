package prototype1.print1;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class PrintServer {
    public static void main(String[] args) throws RemoteException {
       Registry registery =  LocateRegistry.createRegistry(5099);
       registery.rebind("printer", new PrintService());
    }
}

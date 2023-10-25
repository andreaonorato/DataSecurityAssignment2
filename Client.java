import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {
    public static void main(String[] args)throws NotBoundException ,MalformedURLException, RemoteException {
      PrintServiceI service = (PrintServiceI)  Naming.lookup("rmi://localhost:5099/printer");
        service.status("printer_1");
        service.start();
    }
}

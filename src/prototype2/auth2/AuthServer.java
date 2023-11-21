package prototype2.auth2;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class AuthServer {
    public static void main(String[] args) throws RemoteException {
        Registry registery = LocateRegistry.createRegistry(5098);
        registery.rebind("Auth", new AuthService());
    }
}

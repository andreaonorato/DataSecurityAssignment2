package prototype1.print1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import prototype1.auth1.AuthServiceI;


public class PrintService extends UnicastRemoteObject implements PrintServiceI {
    public PrintService() throws RemoteException {
        super();
    }

    @Override
    public AuthServiceI connectAuth() throws NotBoundException, MalformedURLException, RemoteException {
        AuthServiceI auth = (AuthServiceI) Naming.lookup("rmi://localhost:5098/Auth");
        return auth;
    }

    @Override
    public boolean printFile(String filename, String printer, String token) throws RemoteException {

        try {
            AuthServiceI auth = connectAuth();
            boolean isValid = auth.validateToken(token);
            String username = auth.getUsername(token);
            boolean isAlllowed = isMethodAllowed(username, "printFile");
            if (isValid && isAlllowed) {
                System.out.println("file name: " + filename + " printer: " + printer);
                return true;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;

    }

    @Override
    public boolean printQueue(String printer, String token) throws RemoteException {

        try {
            AuthServiceI auth = connectAuth();
            boolean isValid = auth.validateToken(token);
            String username = auth.getUsername(token);
            boolean isAlllowed = isMethodAllowed(username, "printQueue");
            if (isValid && isAlllowed) {
                System.out.println("print queue:\n1  abc.pdf\n2  def.pdf\n3  demo.txt");
                return true;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;

    }

    @Override
    public boolean topQueue(String printer, int job, String token) throws RemoteException {

        try {
            AuthServiceI auth = connectAuth();
            boolean isValid = auth.validateToken(token);
            String username = auth.getUsername(token);
            boolean isAlllowed = isMethodAllowed(username, "topQueue");
            if (isValid && isAlllowed) {
                System.out.println("Job: " + job + " moved to top of the queue for printer: " + printer);
                return true;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;

    }

    @Override
    public boolean start(String token) throws RemoteException {

        try {
            AuthServiceI auth = connectAuth();
            boolean isValid = auth.validateToken(token);
            String username = auth.getUsername(token);
            boolean isAlllowed = isMethodAllowed(username, "start");
            if (isValid && isAlllowed) {
                System.out.println("print server started");
                return true;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;

    }

    @Override
    public boolean stop(String token) throws RemoteException {

        try {
            AuthServiceI auth = connectAuth();
            boolean isValid = auth.validateToken(token);
            String username = auth.getUsername(token);
            boolean isAlllowed = isMethodAllowed(username, "stop");
            if (isValid && isAlllowed) {
                System.out.println("print server stopped");
                return true;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;

    }

    @Override
    public boolean restart(String token) throws RemoteException {
        try {
            AuthServiceI auth = connectAuth();
            boolean isValid = auth.validateToken(token);
            String username = auth.getUsername(token);
            boolean isAlllowed = isMethodAllowed(username, "restart");
            if (isValid && isAlllowed) {
                System.out.println("print server restarted");
                return true;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;

    }

    @Override
    public boolean status(String printer, String token) throws RemoteException {
        try {
            AuthServiceI auth = connectAuth();
            boolean isValid = auth.validateToken(token);
            String username = auth.getUsername(token);
            boolean isAlllowed = isMethodAllowed(username, "status");
            if (isValid && isAlllowed) {
                System.out.println(printer + " status: printing...");
                return true;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;

    }

    @Override
    public boolean readConfig(String parameter, String token) throws RemoteException {
        try {
            AuthServiceI auth = connectAuth();
            boolean isValid = auth.validateToken(token);
            String username = auth.getUsername(token);
            boolean isAlllowed = isMethodAllowed(username, "readConfig");
            if (isValid && isAlllowed) {
                System.out.println("read parameter: " + parameter);
                return true;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;

    }

    @Override
    public boolean setConfig(String parameter, String value, String token) throws RemoteException {
        try {
            AuthServiceI auth = connectAuth();
            boolean isValid = auth.validateToken(token);
            String username = auth.getUsername(token);
            boolean isAlllowed = isMethodAllowed(username, "setConfig");
            if (isValid && isAlllowed) {
                System.out.println("set parameter " + parameter + " with value " + value);
                return true;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;

    }

    private boolean isMethodAllowed(String username, String method) {
        try (BufferedReader reader = new BufferedReader(new FileReader("src\\data\\Acl.txt"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                // Process each line as needed
                if (line.contains(username) && line.contains(method)&&line.contains("active"))
                    return true;
            }
        } catch (IOException e) {
            // Handle exceptions, such as file not found or unable to read
            e.printStackTrace();
        }
        return false;
    }
}

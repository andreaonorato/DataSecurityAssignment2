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
    public String printFile(String filename, String printer, String token) throws RemoteException {

        try {
            AuthServiceI auth = connectAuth();
            boolean isValid = auth.validateToken(token);
            String username = auth.getUsername(token);
            boolean isAlllowed = isMethodAllowed(username, "printFile");
            if (isValid && isAlllowed) {
                System.out.println("file name: " + filename + " printer: " + printer);
                return "Success";
            }else if( isAlllowed == false)
            {
                return "Operation not allowed";
            }else if(isValid == false){
                return "User authentication failed";
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return "Operation cannot be executed";

    }

    @Override
    public String printQueue(String printer, String token) throws RemoteException {

        try {
            AuthServiceI auth = connectAuth();
            boolean isValid = auth.validateToken(token);
            String username = auth.getUsername(token);
            boolean isAlllowed = isMethodAllowed(username, "printQueue");
            if (isValid && isAlllowed) {
                System.out.println("print queue:\n1  abc.pdf\n2  def.pdf\n3  demo.txt");
                return "Success";
            }else if( isAlllowed == false)
            {
                return "Operation not allowed";
            }else if(isValid == false){
                return "User authentication failed";
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return "Operation cannot be executed";

    }

    @Override
    public String topQueue(String printer, int job, String token) throws RemoteException {

        try {
            AuthServiceI auth = connectAuth();
            boolean isValid = auth.validateToken(token);
            String username = auth.getUsername(token);
            boolean isAlllowed = isMethodAllowed(username, "topQueue");
            if (isValid && isAlllowed) {
                System.out.println("Job: " + job + " moved to top of the queue for printer: " + printer);
                return "Success";
            }else if( isAlllowed == false)
            {
                return "Operation not allowed";
            }else if(isValid == false){
                return "User authentication failed";
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return "Operation cannot be executed";

    }

    @Override
    public String start(String token) throws RemoteException {

        try {
            AuthServiceI auth = connectAuth();
            boolean isValid = auth.validateToken(token);
            String username = auth.getUsername(token);
            boolean isAlllowed = isMethodAllowed(username, "start");
            if (isValid && isAlllowed) {
                System.out.println("print server started");
                return "Success";
            }else if( isAlllowed == false)
            {
                return "Operation not allowed";
            }else if(isValid == false){
                return "User authentication failed";
            }

        } catch (Exception e) {
            System.out.println(e);
        }
       return "Operation cannot be executed";

    }

    @Override
    public String stop(String token) throws RemoteException {

        try {
            AuthServiceI auth = connectAuth();
            boolean isValid = auth.validateToken(token);
            String username = auth.getUsername(token);
            boolean isAlllowed = isMethodAllowed(username, "stop");
            if (isValid && isAlllowed) {
                System.out.println("print server stopped");
                return "Success";
            }else if( isAlllowed == false)
            {
                return "Operation not allowed";
            }else if(isValid == false){
                return "User authentication failed";
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return "Operation cannot be executed";

    }

    @Override
    public String restart(String token) throws RemoteException {
        try {
            AuthServiceI auth = connectAuth();
            boolean isValid = auth.validateToken(token);
            String username = auth.getUsername(token);
            boolean isAlllowed = isMethodAllowed(username, "restart");
            if (isValid && isAlllowed) {
                System.out.println("print server restarted");
                return "Success";
            }else if( isAlllowed == false)
            {
                return "Operation not allowed";
            }else if(isValid == false){
                return "User authentication failed";
            }

        } catch (Exception e) {
            System.out.println(e);
        }
       return "Operation cannot be executed";

    }

    @Override
    public String status(String printer, String token) throws RemoteException {
        try {
            AuthServiceI auth = connectAuth();
            boolean isValid = auth.validateToken(token);
            String username = auth.getUsername(token);
            boolean isAlllowed = isMethodAllowed(username, "status");
            if (isValid && isAlllowed) {
                System.out.println(printer + " status: printing...");
                return "Success";
            }else if( isAlllowed == false)
            {
                return "Operation not allowed";
            }else if(isValid == false){
                return "User authentication failed";
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return "Operation cannot be executed";

    }

    @Override
    public String readConfig(String parameter, String token) throws RemoteException {
        try {
            AuthServiceI auth = connectAuth();
            boolean isValid = auth.validateToken(token);
            String username = auth.getUsername(token);
            boolean isAlllowed = isMethodAllowed(username, "readConfig");
            if (isValid && isAlllowed) {
                System.out.println("read parameter: " + parameter);
                return "Success";
            }else if( isAlllowed == false)
            {
                return "Operation not allowed";
            }else if(isValid == false){
                return "User authentication failed";
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return "Operation cannot be executed";

    }

    @Override
    public String setConfig(String parameter, String value, String token) throws RemoteException {
        try {
            AuthServiceI auth = connectAuth();
            boolean isValid = auth.validateToken(token);
            String username = auth.getUsername(token);
            boolean isAlllowed = isMethodAllowed(username, "setConfig");
            if (isValid && isAlllowed) {
                System.out.println("set parameter " + parameter + " with value " + value);
                return "Success";
            }else if( isAlllowed == false)
            {
                return "Operation not allowed";
            }else if(isValid == false){
                return "User authentication failed";
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return "Operation cannot be executed";

    }

    private boolean isMethodAllowed(String username, String method) {
        try (BufferedReader reader = new BufferedReader(new FileReader("src\\prototype1\\data1\\Acl.txt"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                // Process each line as needed
                String[] data = line.split(",");
                String[] methods = line.split(",", 2);
                String status = line.substring(line.lastIndexOf(",") + 1, line.length());
                if (data[0].trim().equals(username) && methods[1].contains(method) && status.trim().equals("active"))
                    return true;
            }
        } catch (IOException e) {
            // Handle exceptions, such as file not found or unable to read
            e.printStackTrace();
        }
        return false;
    }
}

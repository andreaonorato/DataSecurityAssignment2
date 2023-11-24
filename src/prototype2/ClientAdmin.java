package prototype2;

import java.io.Console;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import prototype2.auth2.AuthServiceI;
import prototype2.print2.PrintServiceI;

public class ClientAdmin {
    Scanner sc = new Scanner(System.in);

    public static String getSecurePassword(String password

    ) {

        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    public static String getToken(Scanner sc) throws NotBoundException, MalformedURLException, RemoteException {
        Console console = System.console();
        String username = console.readLine("Enter username: ");
        String password = String.valueOf(console.readPassword("Enter password: "));
        AuthServiceI auth = (AuthServiceI) Naming.lookup("rmi://localhost:5098/Auth");
        String token = auth.generateToken(username, getSecurePassword(password));
        return token;

    }

    public static void executePrintService(int choice, String token)
            throws NotBoundException, MalformedURLException, RemoteException {
        Scanner sc = new Scanner(System.in);
        String inputToken = token;
        PrintServiceI service = (PrintServiceI) Naming.lookup("rmi://localhost:5099/printer");
        String result;
        Console console = System.console();
        switch (choice) {
            case 1:

                String username = console.readLine("Enter username: ");
                String status = console.readLine("Enter status: ");
                result = service.setUserStatus(token, username, status);
                if (result.trim().equals("Success")) {
                    System.out.println("----Operation executed---\n");
                } else {
                    System.out.println("----" + result + "-----\n");
                    inputToken = getToken(sc);
                }
                break;
            case 2:
                String addUsername = console.readLine("Enter username: ");
                String userRole = console.readLine("Enter role: ");
                result = service.changeUserRole(token, addUsername, userRole);
                if (result.trim().equals("Success")) {
                    System.out.println("----Operation executed---\n");
                } else {
                    System.out.println("----" + result + "-----\n");
                    inputToken = getToken(sc);
                }
                break;
            case 3:
                String addRole = console.readLine("Enter role: ");
                String addMethod = console.readLine("Method name: ");
                result = service.addMethodToRole(token, addRole, addMethod);
                if (result.trim().equals("Success")) {
                    System.out.println("----Operation executed---\n");
                } else {
                    System.out.println("----" + result + "-----\n");
                    inputToken = getToken(sc);
                }
                break;
            case 4:
                String rmvRole = console.readLine("Enter role: ");
                String rmvMethod = console.readLine("Method name: ");
                result = service.RemoveMethodFromRole(token, rmvRole, rmvMethod);
                if (result.trim().equals("Success")) {
                    System.out.println("----Operation executed---\n");
                } else {
                    System.out.println("----" + result + "-----\n");
                    inputToken = getToken(sc);
                }
                break;
            default:
                break;
        }
        choosePrintService(inputToken);
    }

    public static void choosePrintService(String token)
            throws NotBoundException, MalformedURLException, RemoteException {
        Scanner sc = new Scanner(System.in);
        System.out.println(
                "1. Set user status\n2. Change user role\n3. Add method to role acess\n4. Remove method from role ");
        int choice = sc.nextInt();
        if (token.length() > 0 && choice != 4) {
            executePrintService(choice, token);
        }
        sc.close();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            String token = getToken(sc);
            if (token != null) {
                choosePrintService(token);
            } else {
                System.out.println("Invalid credentials, user could not be autheticated");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}

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



public class Client {
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
    switch (choice) {
      case 1:
        result = service.printFile("abc.txt", "printer_1", token);
        if (result.trim().equals("Success")) {
          System.out.println("----Operation executed---\n");
        } else {
          System.out.println("----"+result+"-----\n");
          inputToken = getToken(sc);
        }
        break;
      case 2:
        result = service.printQueue("printer_1", token);
        if (result.trim().equals("Success")) {
          System.out.println("----Operation executed---\n");
        } else {
          System.out.println("----"+result+"-----\n");
          inputToken = getToken(sc);
        }
        break;
      case 3:
        result = service.topQueue("printer_1", 1, token);
        if (result.trim().equals("Success")) {
          System.out.println("----Operation executed---\n");
        } else {
         System.out.println("----"+result+"-----\n");
          inputToken = getToken(sc);
        }
        break;
      case 4:
        result = service.start(token);
        if (result.trim().equals("Success")) {
          System.out.println("----Operation executed---\n");
        } else {
          System.out.println("----"+result+"-----\n");
          inputToken = getToken(sc);
        }
        break;
      case 5:
        result = service.stop(token);
        if (result.trim().equals("Success")) {
          System.out.println("----Operation executed---\n");
        } else {
          System.out.println("----"+result+"-----\n");
          inputToken = getToken(sc);
        }
        break;
      case 6:
        result = service.restart(token);
        if (result.trim().equals("Success")) {
          System.out.println("----Operation executed---\n");
        } else {
          System.out.println("----"+result+"-----\n");
          inputToken = getToken(sc);
        }
        break;
      case 7:
        result = service.status("printer_1", token);
        if (result.trim().equals("Success")) {
          System.out.println("----Operation executed---\n");
        } else {
          System.out.println("----"+result+"-----\n");
          inputToken = getToken(sc);
        }
        break;
      case 8:
        result = service.readConfig("page-size", token);
        if (result.trim().equals("Success")) {
          System.out.println("----Operation executed---\n");
        } else {
        System.out.println("----"+result+"-----\n");
          inputToken = getToken(sc);
        }
        break;
      case 9:
        result = service.setConfig("pages", "4", token);
        if (result.trim().equals("Success")) {
          System.out.println("----Operation executed---\n");
        } else {
          System.out.println("----"+result+"-----\n");
          inputToken = getToken(sc);
        }
        break;

      default:
        break;
    }
    choosePrintService(inputToken);
  }

  public static void choosePrintService(String token) throws NotBoundException, MalformedURLException, RemoteException {
    Scanner sc = new Scanner(System.in);
    System.out.println(
        "1. print file\n2. print queue\n3. move job to queue top\n4. start printing\n5. stop printing\n6. restart printing\n7. show printer status\n8. show config\n9. set config\n10. exit ");
    int choice = sc.nextInt();
    if (token.length() > 0 && choice != 10) {
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
      }else{
        System.out.println("Invalid credentials, user could not be autheticated");
      }
    } catch (Exception e) {
      System.out.println(e);
    }

  }
}

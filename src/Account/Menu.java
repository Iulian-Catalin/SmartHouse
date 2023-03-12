package Account;
import java.util.Scanner;

public class Menu {
    public void start() {
        Login on = new Login();
        User a = new User();
        boolean logged = on.login();
        while (logged) {
            System.out.println("Select from available options below:");
            System.out.println("1. Create account;");
            System.out.println("2. Logout and change account;");
            System.out.println("3. Exit;");
            System.out.print("Insert: ");
            int insert = new Scanner(System.in).nextInt();
            switch (insert) {
                case 1 -> {
                    System.out.print("\nInser username: ");
                    String user = new Scanner(System.in).nextLine();
                    a.setUser(user);
                    System.out.print("\nInser password: ");
                    String pwd = new Scanner(System.in).nextLine();
                    a.setPassword(pwd);
                    System.out.print("\nAdministrator type account: ");
                    boolean admin = new Scanner(System.in).nextBoolean();
                    a.setAdministrator(admin);
                    a.createAccount(a);
                }
                case 2 -> logged = on.login();
                case 3 -> {
                    logged = false;
                    System.out.println("Shuting down ...");
                }
            }
        }
    }
}

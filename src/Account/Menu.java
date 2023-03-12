package Account;
import java.util.Scanner;

public class Menu {
    public void start() {
        Login on = new Login();
        boolean logged = on.login();
        while (logged) {
            System.out.println("Select from available options below: " +
                    "\n1. Create account;" +
                    "\n2. Logout and change account;" +
                    "\n3. Delete account;" +
                    "\n4. Exit;" +
                    "\nInsert: ");
            int insert = new Scanner(System.in).nextInt();
            switch (insert) {
                case 1 -> on.createAccount();
                case 2 -> logged = on.login();
                case 3 -> on.removeAccount();
                case 4 -> {
                    logged = false;
                    System.out.println("Shuting down ...");
                }
            }
        }
    }
}

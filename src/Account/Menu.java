package Account;

import java.util.Scanner;

public class Menu {
    public void start() {
        Login logged = new Login();
        User temp = logged.login();

        boolean on = true;

        while (on) {
            if (temp.isAdministrator()) {
                System.out.println("Select from available options below: " +
                        "\n1. Create account;" +
                        "\n2. Logout and change account;" +
                        "\n3. Delete account;" +
                        "\n4. Exit;" +
                        "\nInsert: ");
                int insert = new Scanner(System.in).nextInt();
                switch (insert) {
                    case 1 -> logged.createAccount();
                    case 2 -> temp = logged.login();
                    case 3 -> logged.removeAccount();
                    case 4 -> {
                        on = false;
                        System.out.println("Shuting down ...");
                    }
                }
            } else {
                System.out.println("Select from available options below: " +
                        "\n3. Logout and change account;" +
                        "\n4. Exit;" +
                        "\nInsert: ");
                int insert = new Scanner(System.in).nextInt();
                switch (insert) {
                    case 3 -> temp = logged.login();
                    case 4 -> {
                        on = false;
                        System.out.println("Shuting down ...");
                    }
                }
            }
        }
    }
}
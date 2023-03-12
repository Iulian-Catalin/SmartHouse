package Account;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

public class Login {
    private static List<User> listOfUsers;

    public User login() {
        User temp = new User();
        listOfUsers = loadAccounts();
        showDb();
        System.out.println("Welcome to your smart house" +
                "\nPlease log into your account to continue ...");

        boolean result = false;
        int limit = 0;
        do {
            System.out.print("Insert username: ");
            String user = new Scanner(System.in).nextLine();
            System.out.print("\nInsert password: ");
            String password = new Scanner(System.in).nextLine();
            User logged = new User(user, password);
            boolean loggedIn = false;
            for (User u : listOfUsers) {
                if (u.equals(logged)) {
                    temp.setUser(u.getUser());
                    temp.setPassword(u.getPassword());
                    temp.setAdministrator(u.isAdministrator());
                    System.out.println("You're logged in.");
                    loggedIn = result = true;
                }
            }
            if (!loggedIn) {
                System.out.println("Attempt failed !");
                limit++;
                int maxTries = 3;
                if (limit == maxTries) {
                    limit = 0;
                    System.out.println("Waiting 3 seconds ...");
                    try {
                        int WAITTIME = 3;
                        TimeUnit.SECONDS.sleep(WAITTIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        while (!result);
        return temp;

    }


    private List<User> loadAccounts() {
        Path pIn = Paths.get("Accounts.txt");
        List<User> listOfUsers = new ArrayList<>();
        List<String> listOfUsersAsString = new ArrayList<>();
        try {
            listOfUsersAsString = Files.readAllLines(pIn);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int x = 0; x < listOfUsersAsString.size(); x++) {
            User temp = new User();
            String curentLne = listOfUsersAsString.get(x);
            StringTokenizer st = new StringTokenizer(curentLne, ",");
            while (st.hasMoreTokens()) {
                String u = st.nextToken().trim();
                String p = st.nextToken().trim();
                String ad = st.nextToken().trim();
                temp.setUser(u);
                temp.setPassword(p);
                if (ad.equalsIgnoreCase("true")) {
                    temp.setAdministrator(true);
                }
            }
            listOfUsers.add(temp);
        }
        return listOfUsers;
    }

    public void createAccount() {
        showDb();
        User a = new User();
        System.out.print("\nInser username: ");
        String user = new Scanner(System.in).nextLine();
        a.setUser(user);
        System.out.print("\nInser password: ");
        String pwd = new Scanner(System.in).nextLine();
        a.setPassword(pwd);
        System.out.print("\nAdministrator type account: ");
        boolean admin = new Scanner(System.in).nextBoolean();
        a.setAdministrator(admin);
        listOfUsers.add(a);
        actualizeDb();
    }

    public void removeAccount() {
        showDb();
        boolean result = false;
        do {
            System.out.print("Insert username: ");
            String user = new Scanner(System.in).nextLine();
            User del = new User();
            boolean loggedIn = false;
            for (User u : listOfUsers) {
                if (u.getUser().equals(user)) {
                    del.setUser(u.getUser());
                    del.setPassword(u.getPassword());
                    del.setAdministrator(u.isAdministrator());
                    loggedIn = true;
                } else {
                    System.out.println("Attempt failed !");
                }
            }
            if (loggedIn) {
                listOfUsers.remove(del);
                System.out.println("Account deleted !");
                result = true;
                actualizeDb();
            }
        }
        while (!result);
    }

    private void actualizeDb() {
        File os = Paths.get("Accounts.txt").toFile();
        os.delete();

        os = new File("Accounts.txt");
        try {
            os.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Path p = Paths.get("Accounts.txt");
        String txt;
        for (int x = 0; x < listOfUsers.size(); x++) {
            if (x == listOfUsers.size() - 1) {
                txt = listOfUsers.get(x).getUser() + ", " +
                        listOfUsers.get(x).getPassword() + ", " +
                        listOfUsers.get(x).isAdministrator();
            } else txt = listOfUsers.get(x).getUser() + ", " +
                    listOfUsers.get(x).getPassword() + ", " +
                    listOfUsers.get(x).isAdministrator() + "\n";
            try {
                Files.write(p, txt.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void showDb() {
        for (User u : listOfUsers) {
            System.out.println(u.toString());
        }
    }
}

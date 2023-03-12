package Account;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Login {
    private static List<User> listOfUsers;

    public boolean login() {
        listOfUsers = loadAccounts();
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
            while (!result) ;
            return result;

    }


        private List<User> loadAccounts () {
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
                System.out.println(listOfUsersAsString);
            }
            return listOfUsers;
        }

        public void createAccount (User p){
            String user = p.getUser();
            String password = p.getPassword();
            boolean administrator = p.isAdministrator();
            String newRow = "\n" + user + ", " + password + ", " + administrator;
            Path pOut = Paths.get("Accounts.txt");
            try {
                Files.write(pOut, newRow.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public void removeAccount () {
            boolean result = false;
            do {
                System.out.print("Insert username: ");
                String user = new Scanner(System.in).nextLine();
                System.out.print("\nInsert password: ");
                String password = new Scanner(System.in).nextLine();
                User logged = new User(user, password);
                boolean loggedIn = false;
                for (User u : listOfUsers) {
                    if (u.equals(logged)) {
                        System.out.println("Account deleted !");
                        result = true;
                    }
                }
                if (!loggedIn) {
                    System.out.println("Attempt failed !");
                }
            }
            while (!result);
        }

    }

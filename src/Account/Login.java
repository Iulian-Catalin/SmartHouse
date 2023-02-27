package Account;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Login {
    private static List<User> listOfUsers;
    public void login(){
        listOfUsers=loadAccounts();
        do {
            System.out.print("Insert username: ");
            String user = new Scanner(System.in).nextLine();
            System.out.print("\nInsert password: ");
            String password = new Scanner(System.in).nextLine();
            User logged = new User(user, password);
           for(User u : listOfUsers){
                if (u.equals(logged)) {
                    System.out.println("You're logged in.");
                } else System.out.println("Fail !");
             }
//            if (logged.equals(listOfUsers)){
//                System.out.println("You're logged in.");
//            } else System.out.println("Fail !");
        }
        while (true);
    }



    private List<User> loadAccounts(){
        Path pIn = Paths.get("Accounts.txt");
        List<User> listOfUsers = new ArrayList<>();
        List<String> listOfUsersAsString = new ArrayList<>();
        try {
            listOfUsersAsString = Files.readAllLines(pIn);
        }catch (IOException e){
            e.printStackTrace();
        }
        System.out.println(listOfUsersAsString);
        for ( int x = 0; x < listOfUsersAsString.size(); x++) {
            User temp = new User();
            String curentLne = listOfUsersAsString.get(x);
            StringTokenizer st = new StringTokenizer(curentLne, ",");
            while (st.hasMoreTokens()){
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
            System.out.println(Arrays.toString(listOfUsers.toArray()));
        }
        return listOfUsers;
    }

    public void create(){
        String x = "joi e joi";
        File fi = new File("Nou.txt");
        try {
            FileWriter p = new FileWriter(fi);
            BufferedWriter i = new BufferedWriter(p);
            i.write(x);
            i.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

package Account;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Login {
    private static List<User> listOfUsers;



    private List<User> loadAccounts(){
        Path pIn = Paths.get("Accounts.txt");
        List<User> listOfUsers = new ArrayList<>();
        List<String> listOfUsersAsString = new ArrayList<>();
        try {
            listOfUsersAsString = Files.readAllLines(pIn);
        }catch (IOException e){
            e.printStackTrace();
        }
        return listOfUsers;
    }
}

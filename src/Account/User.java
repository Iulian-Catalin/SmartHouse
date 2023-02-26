package Account;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
public class User {
    private String user;
    private String password;
    private boolean administrator;

    public User(String user, String password, boolean administrator){
        user=this.user;
        password=this.password;
        administrator=this.administrator;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void createAccount(User p){
        String user = p.user;
        String password = p.password;
        boolean administrator = p.administrator;
        String newRow = user+" "+password+" "+administrator;
        Path pOut = Paths.get("Accounts.txt");
        try{
            Files.write(pOut,newRow.getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}

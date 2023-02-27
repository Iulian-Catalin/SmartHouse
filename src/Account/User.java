package Account;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Objects;

public class User {
    private String user;
    private String password;
    private boolean administrator;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user1 = (User) o;
        return Objects.equals(user, user1.user) && Objects.equals(password, user1.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, password);
    }

    public User(String user, String password){
        this.user = user;
        this.password = password;
    }

    public User() {}

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

    public boolean isAdministrator() {
        return administrator;
    }

    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
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

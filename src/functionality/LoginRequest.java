package functionality;

import java.io.Serializable;

public class LoginRequest implements Serializable {

    static final long serialVersionUID = -687991492884005033L;
    private String username;
    private String password;

    public LoginRequest(){

    }

    public LoginRequest(String username, String password){

        this.username = username;
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

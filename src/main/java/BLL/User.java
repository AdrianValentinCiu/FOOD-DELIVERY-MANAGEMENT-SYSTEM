package BLL;

import java.io.Serializable;

public class User implements Serializable {
    private final int clientId;
    private final String userName;
    private final String password;
    public static int curId = 0;

    public User(int clientId, String userName, String password) {
        this.clientId = clientId;
        this.userName = userName;
        this.password = password;
    }

    public String toString(){
        return clientId + " " + userName;
    }

    public int getClientId() {
        return clientId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}

package BLL;

import DataAccess.Serializator;

import java.util.LinkedList;

public class LogInRegister {
    private LinkedList<User> users;
    private String path;
    public static int userId = 0;

    public LogInRegister(String path){
        this.path = path;
        LinkedList<User> clients = null;
        Serializator serializator = new Serializator();
        this.users = (LinkedList<User>) serializator.deserialize(path);
        if(this.users == null)
            this.users = new LinkedList<>();
    }

    /**
     * This method is used to see if a user has been added
     * @param userName - the username
     * @param password - the user password
     * @return
     */
    public boolean checkUser(String userName, String password){
        boolean userFound = false;
        int contor = 0;
        if(users == null)
            return false;
        for(User user : users) {
            contor++;
            if ((user.getUserName()).equals(userName) && (user.getPassword()).equals(password))
                userFound = true;
            userId = contor;
        }
        return userFound;
    }

    /**
     * This method is used to register a new user
     * @param userName - the username
     * @param password - the user password
     * @param user - the list in which the user will be added
     */
    public void registerUser(String userName, String password, LinkedList<User> user){
        if(user == null)
            user = new LinkedList<>();
        if(!checkUser(userName, password))
            user.add(new User(User.curId, userName, password));
        Serializator serializator = new Serializator();
        serializator.serialize(path, user);
    }
}

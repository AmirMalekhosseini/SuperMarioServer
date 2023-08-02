package Controller.Menu;

import Model.Game.OnlineUser;
import MyProject.MyProject;

import java.util.Objects;

public class UsernameLogic {

    public boolean addUser(String username) {


        return !MyProject.getInstance().getDatabase().getAllUsers().containsKey(username);
    }

    public boolean signInUser(String username) {


        return MyProject.getInstance().getDatabase().getAllUsers().containsKey(username);
    }

    public boolean checkPassword(String username, String password) {

        return MyProject.getInstance().getDatabase().getAllUsers().get(username).getUserData().getPassword().equals(password);
    }

}

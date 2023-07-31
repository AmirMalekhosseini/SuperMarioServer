package Controller.Menu;

import Model.Game.OnlineUser;
import MyProject.MyProject;

import java.util.Objects;

public class UsernameLogic {

    private boolean isUsernameOK;
    private boolean isUsernameOKForSignIn;
    private boolean isPasswordOK;

    private int usernameIndex;

    public boolean addUser(String username) {

        int usernameCounter = 0;
        for (int i = 0; i < 100000000; i++) {
            if (MyProject.getInstance().getDatabase().getAllUsers().size() == 0) {
                return true;
            }
            if (!Objects.equals(MyProject.getInstance().getDatabase().getAllUsers().get(i).getUserData().getUsername(), username)) {
                usernameCounter++;
                if (usernameCounter == MyProject.getInstance().getDatabase().getAllUsers().size()) {
                    isUsernameOK = true;
                    break;
                }
            } else {
                isUsernameOK = false;
                break;
            }
        }

        return isUsernameOK;
    }

    public boolean signInUser(String username) {

        isUsernameOKForSignIn = MyProject.getInstance().getDatabase().getAllUsers().containsKey(username);


        return isUsernameOKForSignIn;
    }

    public boolean checkPassword(String username,String password) {

        isPasswordOK = MyProject.getInstance().getDatabase().getAllUsers().get(username).getUserData().getPassword().equals(password);

        return isPasswordOK;
    }

    public int getUsernameIndex() {
        return usernameIndex;
    }

    public void setUsernameIndex(int usernameIndex) {
        this.usernameIndex = usernameIndex;
    }
}

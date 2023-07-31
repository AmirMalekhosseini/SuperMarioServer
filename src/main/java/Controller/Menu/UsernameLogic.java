package Controller.Menu;

import Model.Game.OnlineUser;
import MyProject.MyProject;
import java.util.Objects;

public class UsernameLogic {

    private boolean isUsernameOK;
    private boolean isUsernameOKForSignIn;
    private boolean isPasswordOK;

    private int usernameIndex;

    public boolean addUser(OnlineUser onlineUser) {

            int usernameCounter = 0;
            for (int i = 0; i < 100000000; i++) {
                if (MyProject.getInstance().getDatabase().getAllUsers().size() == 0) {
                    return true;
                }
                if (!Objects.equals(MyProject.getInstance().getDatabase().getAllUsers().get(i).getUserData().getUsername(), onlineUser.getUserData().getUsername())) {
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

        for (int i = 0; i < MyProject.getInstance().getDatabase().getAllUsers().size(); i++) {
            if (Objects.equals(MyProject.getInstance().getDatabase().getAllUsers().get(i).getUserData().getUsername(), username)) {
                isUsernameOKForSignIn = true;
                usernameIndex = i;
                break;
            } else {
                isUsernameOKForSignIn = false;
            }
        }

        return isUsernameOKForSignIn;
    }

    public boolean checkPassword(String password) {

            if (Objects.equals(MyProject.getInstance().getDatabase().getAllUsers().get(usernameIndex).getUserData().getPassword(), password)) {
                isPasswordOK = true;
            } else {
                isPasswordOK = false;
            }

        return isPasswordOK;
    }

    public int getUsernameIndex() {
        return usernameIndex;
    }

    public void setUsernameIndex(int usernameIndex) {
        this.usernameIndex = usernameIndex;
    }
}

package Controller.NetworkCommunication.MessageHandler;

import Controller.Menu.UsernameLogic;
import Model.Game.OnlineUser;
import Model.NetworkCommunication.Message.Message;
import Model.NetworkCommunication.Message.SignUpMessage;
import MyProject.MyProject;

import java.io.IOException;
import java.util.ArrayList;

public class SignUpHandler implements MessageHandler {
    private final UsernameLogic usernameLogic;

    public SignUpHandler() {
        usernameLogic = new UsernameLogic();
    }

    @Override
    public void handleMessage(Message message) {

        if (usernameLogic.addUser(((SignUpMessage) message).getUsername())) {// OK to Sign up
            OnlineUser newUser = new OnlineUser();
            newUser.getUserData().setUsername(((SignUpMessage) message).getUsername());
            newUser.getUserData().setPassword(((SignUpMessage) message).getPassword());
            MyProject.getInstance().getDatabase().getAllUsers().put(newUser.getUserData().getUsername(), newUser);
            // Create Message Queue
            MyProject.getInstance().getDatabase().getMessageQueueMap().put(newUser.getUserData().getUsername(), new ArrayList<>());
            ((SignUpMessage) message).setPasswordOK(true);
        }

        // Send SignUpMessage Back
        try {
            MyProject.getInstance().getDatabase().getClientHandlersMap().get("").sendMessage(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}

package Model.NetworkCommunication.MessageHandler;

import Controller.Menu.UsernameLogic;
import Model.Game.OnlineUser;
import Model.NetworkCommunication.Message.Message;
import Model.NetworkCommunication.Message.SignUpMessage;
import MyProject.MyProject;

import java.io.IOException;

public class SignUpHandler implements MessageHandler {
    private final UsernameLogic usernameLogic;

    public SignUpHandler() {
        usernameLogic = new UsernameLogic();
    }

    @Override
    public void handleMessage(Message message) {


        System.out.println(message.getMessageType());
        System.out.println(message.getClass().getName());
        if (usernameLogic.addUser(((SignUpMessage) message).getUsername())) {// OK to Sign up
            OnlineUser newUser = new OnlineUser();
            newUser.getUserData().setUsername(((SignUpMessage) message).getUsername());
            newUser.getUserData().setPassword(((SignUpMessage) message).getPassword());
            MyProject.getInstance().getDatabase().getAllUsers().put(newUser.getUserData().getUsername(), newUser);
            ((SignUpMessage) message).setPasswordOK(true);
        }

        // Send SignUpMessage Back
        try {
            System.out.println(MyProject.getInstance().getDatabase().getMessageHandlerMap().get(""));
            MyProject.getInstance().getDatabase().getClientHandlersMap().get("").sendMessage(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}

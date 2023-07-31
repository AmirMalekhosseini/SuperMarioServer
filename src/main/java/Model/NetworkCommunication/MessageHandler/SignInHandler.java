package Model.NetworkCommunication.MessageHandler;

import Controller.Menu.UsernameLogic;
import Model.NetworkCommunication.ClientHandler;
import Model.NetworkCommunication.Message.Message;
import Model.NetworkCommunication.Message.SignInMessage;
import MyProject.MyProject;

import java.io.IOException;

public class SignInHandler implements MessageHandler {

    private final UsernameLogic usernameLogic;

    public SignInHandler() {
        usernameLogic = new UsernameLogic();
    }

    @Override
    public void handleMessage(Message message) {
        if (message instanceof SignInMessage) {

            SignInMessage signInMessage = (SignInMessage) message;
            ClientHandler handler = MyProject.getInstance().getDatabase().getClientHandlersMap().get("");
            if (usernameLogic.signInUser(signInMessage.getUsername())) {
                signInMessage.setUsernameOK(true);
                if (usernameLogic.checkPassword(signInMessage.getUsername(), signInMessage.getPassword())) {// Ok for Sign In
                    signInMessage.setPasswordOK(true);
                    signInMessage.setSignedInUser(MyProject.getInstance().getDatabase().getAllUsers().get(signInMessage.getUsername()));
                    // Set ClientHandler

                    handler.setUsername(signInMessage.getUsername());
                    MyProject.getInstance().getDatabase().getClientHandlersMap().remove("");
                    MyProject.getInstance().getDatabase().getClientHandlersMap().put(signInMessage.getUsername(), handler);
                } else {
                    signInMessage.setPasswordOK(false);
                }

            } else {
                signInMessage.setUsernameOK(false);
            }

            // Send SignInMessage Back.
            try {
                handler.sendMessage(signInMessage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}

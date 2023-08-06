package Controller.NetworkCommunication.MessageHandler;

import Model.NetworkCommunication.Message.FriendRequestMessage;
import Model.NetworkCommunication.Message.Message;
import MyProject.MyProject;

import java.io.IOException;

public class FriendRequestHandler implements MessageHandler{
    @Override
    public void handleMessage(Message message) {

        if (message instanceof FriendRequestMessage) {
            FriendRequestMessage requestMessage = (FriendRequestMessage) message;
            String target = requestMessage.getTargetUser();
            if (!MyProject.getInstance().getDatabase().getAllUsers().containsKey(target)) {
                return;
            }
            // Target is Online:
            if (MyProject.getInstance().getDatabase().getClientHandlersMap().containsKey(target)) {
                try {
                    MyProject.getInstance().getDatabase().getClientHandlersMap().get(target).sendMessage(requestMessage);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {// Target is Offline
                MyProject.getInstance().getDatabase().getMessageQueueMap().get(target).add(requestMessage);
            }
        }

    }
}

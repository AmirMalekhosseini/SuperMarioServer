package Controller.NetworkCommunication.MessageHandler;

import Model.NetworkCommunication.Message.FriendRequestMessage;
import Model.NetworkCommunication.Message.Message;
import Model.NetworkCommunication.Message.RemoveLobbyMessage;
import MyProject.MyProject;

import java.io.IOException;

public class RemoveLobbyHandler implements MessageHandler{


    @Override
    public void handleMessage(Message message) {

        if (message instanceof RemoveLobbyMessage) {
            RemoveLobbyMessage requestMessage = (RemoveLobbyMessage) message;
            String target = requestMessage.getTargetUser();
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

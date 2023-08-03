package Controller.NetworkCommunication.MessageHandler;

import Model.NetworkCommunication.Message.FriendRequestMessage;
import Model.NetworkCommunication.Message.GameRequestMessage;
import Model.NetworkCommunication.Message.Message;
import MyProject.MyProject;

import java.io.IOException;

public class GameRequestHandler implements MessageHandler{


    @Override
    public void handleMessage(Message message) {

        if (message instanceof GameRequestMessage) {
            GameRequestMessage requestMessage = (GameRequestMessage) message;
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

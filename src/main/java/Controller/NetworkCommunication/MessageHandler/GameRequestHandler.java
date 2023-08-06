package Controller.NetworkCommunication.MessageHandler;

import Model.NetworkCommunication.Message.FriendRequestMessage;
import Model.NetworkCommunication.Message.GameRequestMessage;
import Model.NetworkCommunication.Message.Message;
import MyProject.MyProject;

import java.io.IOException;
import java.util.ArrayList;

public class GameRequestHandler implements MessageHandler {


    @Override
    public void handleMessage(Message message) {

        if (message instanceof GameRequestMessage) {
            GameRequestMessage requestMessage = (GameRequestMessage) message;
            String target = requestMessage.getTargetUser();
            ArrayList<String> lobbyMembers = MyProject.getInstance().getDatabase().getLobbyMap().get(requestMessage.getLobbyName()).getMembers();
            requestMessage.setMembers(lobbyMembers);
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

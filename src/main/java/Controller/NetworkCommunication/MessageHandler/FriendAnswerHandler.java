package Controller.NetworkCommunication.MessageHandler;

import Model.NetworkCommunication.Message.FriendRequestAnswer;
import Model.NetworkCommunication.Message.Message;
import MyProject.MyProject;

import java.io.IOException;

public class FriendAnswerHandler implements MessageHandler{
    @Override
    public void handleMessage(Message message) {

        if (message instanceof FriendRequestAnswer) {
            FriendRequestAnswer requestAnswer = (FriendRequestAnswer) message;
            String sender = requestAnswer.getSenderUser();
            String target = requestAnswer.getTargetUser();
            if (requestAnswer.isAnswer()) {
                // Add Friends
                MyProject.getInstance().getDatabase().getAllUsers().get(sender).getUserFriends().add(target);
                MyProject.getInstance().getDatabase().getAllUsers().get(target).getUserFriends().add(sender);

                try {
                    MyProject.getInstance().getDatabase().getClientHandlersMap().get(target).sendMessage(requestAnswer);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }

    }
}

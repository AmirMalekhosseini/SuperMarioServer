package Controller.NetworkCommunication.MessageHandler;

import Model.NetworkCommunication.Message.GameRequestAnswer;
import Model.NetworkCommunication.Message.Message;
import Model.NetworkCommunication.Message.MessageType;
import Model.NetworkCommunication.Message.NewLobbyMemberMessage;
import MyProject.MyProject;

import java.io.IOException;
import java.util.ArrayList;

public class GameRequestAnswerHandler implements MessageHandler{


    @Override
    public void handleMessage(Message message) {

        if (message instanceof GameRequestAnswer) {
            GameRequestAnswer requestAnswer = (GameRequestAnswer) message;
            boolean answer = requestAnswer.isAnswer();
            if (answer) {// Game Request Accepted
                MyProject.getInstance().getDatabase().getLobbyMap().get(requestAnswer.getLobbyName()).getMembers().add(requestAnswer.getSenderUser());
                NewLobbyMemberMessage lobbyMemberMessage = new NewLobbyMemberMessage();
                lobbyMemberMessage.setMessageType(MessageType.NEW_LOBBY_MEMBER);
                lobbyMemberMessage.setNewMemberName(requestAnswer.getSenderUser());
                ArrayList<String> lobbyMembers = MyProject.getInstance().getDatabase().getLobbyMap().get(requestAnswer.getLobbyName()).getMembers();
                // Send NewLobbyMemberMessage to all Lobby Members:
                for (String member : lobbyMembers) {
                    // Continue if Server Wants to send it to himself:
                    if (member.equals(requestAnswer.getSenderUser())) {
                        continue;
                    }
                    try {
                        MyProject.getInstance().getDatabase().getClientHandlersMap().get(member).sendMessage(lobbyMemberMessage);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        }

    }
}

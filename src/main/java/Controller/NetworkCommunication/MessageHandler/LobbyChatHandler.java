package Controller.NetworkCommunication.MessageHandler;

import Model.NetworkCommunication.Message.LobbyChatMessage;
import Model.NetworkCommunication.Message.Message;
import MyProject.MyProject;

import java.io.IOException;
import java.util.ArrayList;

public class LobbyChatHandler implements MessageHandler{


    @Override
    public void handleMessage(Message message) {

        if (message instanceof LobbyChatMessage) {
            LobbyChatMessage lobbyChatMessage = (LobbyChatMessage) message;
            String lobbyName = lobbyChatMessage.getLobbyName();
            String sender = lobbyChatMessage.getSenderUser();

            ArrayList<String> lobbyMembers = MyProject.getInstance().getDatabase().getLobbyMap().get(lobbyName).getMembers();
            // Send NewMessage to all Lobby Members:
            for (String member : lobbyMembers) {
                // Continue if Server Wants to send it to himself:
                if (member.equals(sender)) {
                    continue;
                }
                try {
                    MyProject.getInstance().getDatabase().getClientHandlersMap().get(member).sendMessage(lobbyChatMessage);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }

        }

    }
}

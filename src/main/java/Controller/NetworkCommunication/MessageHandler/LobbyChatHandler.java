package Controller.NetworkCommunication.MessageHandler;

import Model.NetworkCommunication.Message.LobbyChatMessage;
import Model.NetworkCommunication.Message.Message;
import MyProject.MyProject;

import java.io.IOException;
import java.util.ArrayList;

public class LobbyChatHandler implements MessageHandler {


    @Override
    public void handleMessage(Message message) {

        if (message instanceof LobbyChatMessage) {
            LobbyChatMessage lobbyChatMessage = (LobbyChatMessage) message;
            String lobbyName = lobbyChatMessage.getLobbyName();
            String sender = lobbyChatMessage.getSenderUser();
            String messageContext = lobbyChatMessage.getMessageContext();
            String mentionUser = mentionChecker(messageContext);
            // Send for Mention User only:
            if (mentionUser != null) {
                try {
                    MyProject.getInstance().getDatabase().getClientHandlersMap().get(mentionUser).sendMessage(lobbyChatMessage);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return;
            }

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

    private String mentionChecker(String messageContext) {

        String username = null;
        if (messageContext.startsWith("@")) {
            int endIndex = messageContext.indexOf(' ', 1);
            if (endIndex != -1) {
                username = messageContext.substring(1, endIndex);
            }
            // Check if Username Valid
            if (!MyProject.getInstance().getDatabase().getAllUsers().containsKey(username)) {
                return null;
            }
        }
        return username;
    }
}

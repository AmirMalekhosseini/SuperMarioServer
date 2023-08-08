package Controller.NetworkCommunication.MessageHandler;

import Model.Game.Lobby;
import Model.NetworkCommunication.Message.Message;
import Model.NetworkCommunication.Message.MessageType;
import Model.NetworkCommunication.Message.RemoveLobbyMessage;
import MyProject.MyProject;
import java.io.IOException;
import java.util.ArrayList;

public class RemoveLobbyMessageHandler implements MessageHandler {

    @Override
    public void handleMessage(Message message) {

        if (message instanceof RemoveLobbyMessage) {
            RemoveLobbyMessage removeLobby = (RemoveLobbyMessage) message;
            String sender = removeLobby.getSenderUser();
            String lobbyName = removeLobby.getLobbyName();
            if (canSenderRemoveLobby(lobbyName, sender)) {
                removeLobby(lobbyName);
            }

        }

    }

    private boolean canSenderRemoveLobby(String lobbyName, String sender) {
        return MyProject.getInstance().getDatabase().getLobbyMap().get(lobbyName).getAdmin().equals(sender);
    }

    private void removeLobby(String lobbyName) {

        Lobby lobby = MyProject.getInstance().getDatabase().getLobbyMap().get(lobbyName);
        ArrayList<String> members = lobby.getMembers();
        RemoveLobbyMessage removeLobbyMessage = new RemoveLobbyMessage();
        removeLobbyMessage.setMessageType(MessageType.REMOVE_LOBBY_MESSAGE);
        for (String member : members) {
            try {
                MyProject.getInstance().getDatabase().getClientHandlersMap().get(member).sendMessage(removeLobbyMessage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        MyProject.getInstance().getDatabase().getLobbyMap().remove(lobbyName);

    }

}

package Controller.NetworkCommunication.MessageHandler;

import Model.Game.Lobby;
import Model.NetworkCommunication.Message.Message;
import Model.NetworkCommunication.Message.RemoveLobbyMemberMessage;
import MyProject.MyProject;

import java.io.IOException;

public class RemoveLobbyMemberHandler implements MessageHandler {


    @Override
    public void handleMessage(Message message) {

        if (message instanceof RemoveLobbyMemberMessage) {
            RemoveLobbyMemberMessage removeLobbyMemberMessage = (RemoveLobbyMemberMessage) message;
            String lobbyName = removeLobbyMemberMessage.getLobbyName();
            String sender = removeLobbyMemberMessage.getSenderUser();
            String target = removeLobbyMemberMessage.getTargetUser();
            Lobby lobby = MyProject.getInstance().getDatabase().getLobbyMap().get(lobbyName);
            if (!checkSender(sender, lobby)) {
                // Sender has no Power:)
                return;
            }

            if (isTargetCoAdmin(target, lobby)) {
                if (isSenderAdmin(sender, lobby)) {
                    try {
                        sendRemoveMessageToMembers(target,lobbyName,removeLobbyMemberMessage);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    lobby.getMembers().remove(target);
                }
            } else {
                try {
                    sendRemoveMessageToMembers(target,lobbyName,removeLobbyMemberMessage);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                lobby.getMembers().remove(target);
            }

        }

    }

    private void sendRemoveMessageToMembers(String target, String lobbyName, RemoveLobbyMemberMessage message) throws IOException {
        for (String member : MyProject.getInstance().getDatabase().getLobbyMap().get(lobbyName).getMembers()) {
            if (member.equals(target)) {// If User is Removed:
                message.setUserRemoved(true);
                MyProject.getInstance().getDatabase().getClientHandlersMap().get(member).sendMessage(message);
            }
            message.setUserRemoved(false);
            MyProject.getInstance().getDatabase().getClientHandlersMap().get(member).sendMessage(message);
        }
    }

    private boolean checkSender(String sender, Lobby lobby) {
        return lobby.getCoAdmins().contains(sender) || lobby.getAdmin().equals(sender);
    }

    private boolean isTargetCoAdmin(String target, Lobby lobby) {
        return lobby.getCoAdmins().contains(target) || lobby.getAdmin().equals(target);
    }

    private boolean isSenderAdmin(String sender, Lobby lobby) {
        return lobby.getAdmin().equals(sender);
    }

}

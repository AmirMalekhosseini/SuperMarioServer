package Controller.NetworkCommunication.MessageHandler;

import Model.Game.Lobby;
import Model.NetworkCommunication.Message.Message;
import Model.NetworkCommunication.Message.RemoveLobbyMemberMessage;
import MyProject.MyProject;

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
                    lobby.getMembers().remove(target);
                }
            } else {
                lobby.getMembers().remove(target);
            }

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

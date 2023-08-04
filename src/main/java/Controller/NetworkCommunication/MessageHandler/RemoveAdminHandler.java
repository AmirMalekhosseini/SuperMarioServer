package Controller.NetworkCommunication.MessageHandler;

import Model.Game.Lobby;
import Model.NetworkCommunication.Message.Message;
import Model.NetworkCommunication.Message.RemoveAdminMessage;
import MyProject.MyProject;

public class RemoveAdminHandler implements MessageHandler{


    @Override
    public void handleMessage(Message message) {

        if (message instanceof RemoveAdminMessage) {
            RemoveAdminMessage removeAdminMessage = (RemoveAdminMessage) message;
            String lobbyName = removeAdminMessage.getLobbyName();
            String target = removeAdminMessage.getTargetUser();
            Lobby lobby = MyProject.getInstance().getDatabase().getLobbyMap().get(lobbyName);
            if (!checkSender(removeAdminMessage.getSenderUser(), lobby)) {
                // Sender has no Power:)
                return;
            }

            lobby.getCoAdmins().remove(target);
        }

    }

    private boolean checkSender(String sender, Lobby lobby) {
        return lobby.getAdmin().equals(sender);
    }


}

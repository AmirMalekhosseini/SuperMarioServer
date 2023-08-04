package Controller.NetworkCommunication.MessageHandler;

import Model.Game.Lobby;
import Model.NetworkCommunication.Message.AddAdminMessage;
import Model.NetworkCommunication.Message.Message;
import MyProject.MyProject;

public class AddAdminHandler implements MessageHandler{


    @Override
    public void handleMessage(Message message) {

        if (message instanceof AddAdminMessage) {
            AddAdminMessage addAdminMessage = (AddAdminMessage) message;
            String lobbyName = addAdminMessage.getLobbyName();
            String target = addAdminMessage.getTargetUser();
            Lobby lobby = MyProject.getInstance().getDatabase().getLobbyMap().get(lobbyName);
            if (!checkSender(addAdminMessage.getSenderUser(), lobby)) {
                // Sender has no Power:)
                return;
            }
            if (!lobby.getCoAdmins().contains(target)) {
                lobby.getCoAdmins().add(target);
            }
        }

    }

    private boolean checkSender(String sender, Lobby lobby) {
        return lobby.getCoAdmins().contains(sender) || lobby.getAdmin().equals(sender);
    }
}

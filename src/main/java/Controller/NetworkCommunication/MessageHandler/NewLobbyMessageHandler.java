package Controller.NetworkCommunication.MessageHandler;

import Model.Game.Lobby;
import Model.NetworkCommunication.Message.Message;
import Model.NetworkCommunication.Message.NewLobbyMessage;
import MyProject.MyProject;

public class NewLobbyMessageHandler implements MessageHandler{


    @Override
    public void handleMessage(Message message) {

        if (message instanceof NewLobbyMessage) {
            NewLobbyMessage lobbyMessage = (NewLobbyMessage) message;
            String lobbyName = lobbyMessage.getSenderUser();
            String lobbyPassword = lobbyMessage.getPassword();
            MyProject.getInstance().getDatabase().getLobbyMap().put(lobbyName, new Lobby(lobbyName, lobbyPassword));
            MyProject.getInstance().getDatabase().getLobbyMap().get(lobbyName).getMembers().add(lobbyName);
        }

    }
}

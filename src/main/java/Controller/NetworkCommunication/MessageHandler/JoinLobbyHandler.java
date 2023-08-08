package Controller.NetworkCommunication.MessageHandler;

import Model.NetworkCommunication.Message.JoinLobbyMessage;
import Model.NetworkCommunication.Message.Message;
import Model.NetworkCommunication.Message.MessageType;
import Model.NetworkCommunication.Message.NewLobbyMemberMessage;
import MyProject.MyProject;

import java.io.IOException;
import java.util.ArrayList;

public class JoinLobbyHandler implements MessageHandler {


    @Override
    public void handleMessage(Message message) {

        if (message instanceof JoinLobbyMessage) {
            JoinLobbyMessage joinLobby = (JoinLobbyMessage) message;
            String lobbyName = joinLobby.getLobbyName();
            String newUser = joinLobby.getSenderUser();
            if (isJoinValid(joinLobby)) {
                joinLobby.setJoined(true);
                joinLobby.setMembers(MyProject.getInstance().getDatabase().getLobbyMap().get(lobbyName).getMembers());
                try {
                    MyProject.getInstance().getDatabase().getClientHandlersMap().get(newUser).sendMessage(joinLobby);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                sendNewMemberMessage(newUser, lobbyName);

            }


        }

    }

    private boolean isJoinValid(JoinLobbyMessage lobbyMessage) {
        String lobbyName = lobbyMessage.getLobbyName();
        String lobbyPassword = lobbyMessage.getLobbyPassword();
        String newMember = lobbyMessage.getSenderUser();

        if (!MyProject.getInstance().getDatabase().getLobbyMap().containsKey(lobbyName)) {
            return false;
        }

        return MyProject.getInstance().getDatabase().getLobbyMap().get(lobbyName).getLobbyPassword().equals(lobbyPassword) &&
                !MyProject.getInstance().getDatabase().getLobbyMap().get(lobbyName).getBlacklist().contains(newMember);
    }

    private void sendNewMemberMessage(String newMember, String lobbyName) {

        ArrayList<String> lobbyMembers = MyProject.getInstance().getDatabase().getLobbyMap().get(lobbyName).getMembers();
        // Send NewLobbyMemberMessage to all Lobby Members:
        for (String member : lobbyMembers) {
            // Continue if Server Wants to send it to himself:
            if (member.equals(newMember)) {
                continue;
            }

            NewLobbyMemberMessage lobbyMemberMessage = new NewLobbyMemberMessage();
            lobbyMemberMessage.setMessageType(MessageType.NEW_LOBBY_MEMBER);
            lobbyMemberMessage.setNewMemberName(newMember);

            if (isNewMemberBlock(member, newMember)) {
                lobbyMemberMessage.setMemberBlock(true);
            }

            try {
                MyProject.getInstance().getDatabase().getClientHandlersMap().get(member).sendMessage(lobbyMemberMessage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }

    private boolean isNewMemberBlock(String member, String newMember) {

        return MyProject.getInstance().getDatabase().getAllUsers().get(member).getBlockList().contains(newMember);

    }

}

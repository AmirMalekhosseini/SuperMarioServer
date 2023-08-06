package Controller.NetworkCommunication.MessageHandler;

import Model.NetworkCommunication.Message.ChatMessage;
import Model.NetworkCommunication.Message.Message;
import Model.OnlineChat.UserChat;
import MyProject.MyProject;
import java.io.IOException;
import java.util.ArrayList;

public class ChatMessageHandler implements MessageHandler{
    @Override
    public void handleMessage(Message message) {

        if (message instanceof ChatMessage) {
            ChatMessage chatMessage = (ChatMessage) message;
            String sender = chatMessage.getSenderUser();
            String receiver = chatMessage.getTargetUser();
            String messageContext = chatMessage.getContext();
            addMessages(messageContext, sender, receiver);
            if (MyProject.getInstance().getDatabase().getClientHandlersMap().containsKey(receiver)) {
                try {
                    MyProject.getInstance().getDatabase().getClientHandlersMap().get(receiver).sendMessage(chatMessage);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {// Target is Offline
                MyProject.getInstance().getDatabase().getMessageQueueMap().get(receiver).add(chatMessage);
            }
        }

    }

    private void addMessages(String message, String sender, String receiver) {

        UserChat otherChat = new UserChat();
        otherChat.setContext(message);

        // Add for Receiver:
        otherChat.setUserMessage(false);
        if (!MyProject.getInstance().getDatabase().getAllUsers().get(receiver).getUserChatScreens().containsKey(sender)) {
            MyProject.getInstance().getDatabase().getAllUsers().get(receiver).getUserChatScreens().put(sender, new ArrayList<>());
        }
        MyProject.getInstance().getDatabase().getAllUsers().get(receiver).getUserChatScreens().get(sender).add(otherChat);

        // Add for Sender:
        UserChat userChat = new UserChat();
        userChat.setContext(message);
        userChat.setUserMessage(true);
        if (!MyProject.getInstance().getDatabase().getAllUsers().get(sender).getUserChatScreens().containsKey(receiver)) {
            MyProject.getInstance().getDatabase().getAllUsers().get(sender).getUserChatScreens().put(receiver, new ArrayList<>());
        }
        MyProject.getInstance().getDatabase().getAllUsers().get(sender).getUserChatScreens().get(receiver).add(userChat);

    }

}

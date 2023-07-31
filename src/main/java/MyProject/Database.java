package MyProject;

import Model.Game.OnlineUser;
import Model.NetworkCommunication.ClientHandler;
import Model.NetworkCommunication.Message.Message;
import Model.NetworkCommunication.Message.MessageType;
import Model.NetworkCommunication.MessageHandler.MessageHandler;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class Database {

    private Map<String, OnlineUser> allUsers;
    private Map<String, ClientHandler> clientHandlersMap;
    private Map<String, ClientHandler> onlineClientsMap;
    private Map<ClientHandler, BlockingQueue<Message>> messageQueueMap;
    private Map<MessageType, MessageHandler> messageHandlerMap;

    public Database() {

        allUsers = new ConcurrentHashMap<>();
        clientHandlersMap = new ConcurrentHashMap<>();
        onlineClientsMap = new ConcurrentHashMap<>();
        messageQueueMap = new ConcurrentHashMap<>();
        messageHandlerMap = new ConcurrentHashMap<>();

    }


    public Map<String, ClientHandler> getClientHandlersMap() {
        return clientHandlersMap;
    }

    public void setClientHandlersMap(Map<String, ClientHandler> clientHandlersMap) {
        this.clientHandlersMap = clientHandlersMap;
    }

    public Map<String, ClientHandler> getOnlineClientsMap() {
        return onlineClientsMap;
    }

    public void setOnlineClientsMap(Map<String, ClientHandler> onlineClientsMap) {
        this.onlineClientsMap = onlineClientsMap;
    }

    public Map<MessageType, MessageHandler> getMessageHandlerMap() {
        return messageHandlerMap;
    }

    public void setMessageHandlerMap(Map<MessageType, MessageHandler> messageHandlerMap) {
        this.messageHandlerMap = messageHandlerMap;
    }

    public Map<ClientHandler, BlockingQueue<Message>> getMessageQueueMap() {
        return messageQueueMap;
    }

    public void setMessageQueueMap(Map<ClientHandler, BlockingQueue<Message>> messageQueueMap) {
        this.messageQueueMap = messageQueueMap;
    }

    public Map<String, OnlineUser> getAllUsers() {
        return allUsers;
    }

    public void setAllUsers(Map<String, OnlineUser> allUsers) {
        this.allUsers = allUsers;
    }
}

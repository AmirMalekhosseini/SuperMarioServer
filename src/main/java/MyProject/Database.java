package MyProject;

import Controller.NetworkCommunication.MessageHandlerCreator;
import Controller.OnlineStorePack.StorePackCreator;
import Model.Game.OnlineUser;
import Model.NetworkCommunication.ClientHandler;
import Model.NetworkCommunication.Message.Message;
import Model.NetworkCommunication.Message.MessageType;
import Model.NetworkCommunication.MessageHandler.MessageHandler;
import Model.OnlineStorePack.Pack;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class Database {

    private Map<String, OnlineUser> allUsers;
    private Map<String, ClientHandler> clientHandlersMap;
    private Map<ClientHandler, BlockingQueue<Message>> messageQueueMap;
    private Map<MessageType, MessageHandler> messageHandlerMap;
    private ArrayList<Pack> packs;
    public StorePackCreator storePackCreator;

    public Database() {

        allUsers = new ConcurrentHashMap<>();
        clientHandlersMap = new ConcurrentHashMap<>();
        messageQueueMap = new ConcurrentHashMap<>();
        messageHandlerMap = MessageHandlerCreator.getInstance().createMessageHandler();
        storePackCreator = new StorePackCreator();
        packs = storePackCreator.createInitPack();

    }


    public Map<String, ClientHandler> getClientHandlersMap() {
        return clientHandlersMap;
    }

    public void setClientHandlersMap(Map<String, ClientHandler> clientHandlersMap) {
        this.clientHandlersMap = clientHandlersMap;
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

    public ArrayList<Pack> getPacks() {
        return packs;
    }

    public void setPacks(ArrayList<Pack> packs) {
        this.packs = packs;
    }
}

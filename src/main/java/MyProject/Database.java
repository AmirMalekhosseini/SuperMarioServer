package MyProject;

import Controller.NetworkCommunication.MessageHandler.MessageHandlerCreator;
import Controller.OnlineStorePack.StorePackCreator;
import Controller.Utils.HibernateUtils;
import Model.Game.Lobby;
import Model.Game.OnlineUser;
import Model.NetworkCommunication.ClientHandler;
import Model.NetworkCommunication.Message.Message;
import Model.NetworkCommunication.Message.MessageType;
import Controller.NetworkCommunication.MessageHandler.MessageHandler;
import Model.OnlineStorePack.Pack;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class Database {

    private Map<String, OnlineUser> allUsers;
    private Map<String, ClientHandler> clientHandlersMap;
    private Map<String, ArrayList<Message>> messageQueueMap;
    private Map<MessageType, MessageHandler> messageHandlerMap;
    private Map<String, Lobby> lobbyMap;
    private ArrayList<Pack> packs;

    public Database() {

        allUsers = new ConcurrentHashMap<>();
        clientHandlersMap = new ConcurrentHashMap<>();
        messageQueueMap = new ConcurrentHashMap<>();
        messageHandlerMap = MessageHandlerCreator.getInstance().createMessageHandler();
        lobbyMap = new ConcurrentHashMap<>();
        packs = StorePackCreator.getInstance().createInitPack();
        addUsers(Objects.requireNonNull(getAllOnlineUsers()));

    }

    private void addUsers(List<OnlineUser> users) {

        for (OnlineUser user : users) {
            allUsers.put(user.getUsername(), user);
        }


    }

    private List<OnlineUser> getAllOnlineUsers() {
        try (Session session = HibernateUtils.getInstance().getSessionFactory().getCurrentSession()) {
            session.beginTransaction();

            // Create an HQL query to retrieve all OnlineUser objects from the database
            String hql = "FROM OnlineUser";
            Query<OnlineUser> query = session.createQuery(hql, OnlineUser.class);

            // Execute the query and get the list of OnlineUser objects
            List<OnlineUser> onlineUsers = query.list();

            session.getTransaction().commit();
            return onlineUsers;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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

    public Map<String, ArrayList<Message>> getMessageQueueMap() {
        return messageQueueMap;
    }

    public void setMessageQueueMap(Map<String, ArrayList<Message>> messageQueueMap) {
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

    public Map<String, Lobby> getLobbyMap() {
        return lobbyMap;
    }

    public void setLobbyMap(Map<String, Lobby> lobbyMap) {
        this.lobbyMap = lobbyMap;
    }
}

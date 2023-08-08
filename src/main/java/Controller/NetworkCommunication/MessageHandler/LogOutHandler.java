package Controller.NetworkCommunication.MessageHandler;

import Controller.Utils.HibernateUtils;
import Model.Game.OnlineUser;
import Model.Item.Online.Bag;
import Model.NetworkCommunication.ClientHandler;
import Model.NetworkCommunication.Message.LogOutMessage;
import Model.NetworkCommunication.Message.Message;
import MyProject.MyProject;
import org.hibernate.Session;

import java.util.ArrayList;

public class LogOutHandler implements MessageHandler {
    @Override
    public void handleMessage(Message message) {
        if (message instanceof LogOutMessage) {
            LogOutMessage logOutMessage = (LogOutMessage) message;
            ClientHandler handler = MyProject.getInstance().getDatabase().getClientHandlersMap().get(logOutMessage.getTargetUser());
            OnlineUser user = MyProject.getInstance().getDatabase().getAllUsers().get(logOutMessage.getSenderUser());
            user.setUsername(logOutMessage.getSenderUser());
            user.getUserData().getUserHighScore().setUsername(logOutMessage.getSenderUser());
            try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
                OnlineUser testUser = session.get(OnlineUser.class, logOutMessage.getSenderUser());
                if (testUser == null) {// User is not in Database
                    ArrayList<Bag> userBags = new ArrayList<>(user.getUserBags());
                    for (Bag bag : userBags) {
                        session.save(bag);
                    }
                    session.beginTransaction();
                    session.save(user);
                    session.getTransaction().commit();
                } else {// User is Already in Database:
                    System.out.println("already");
                    ArrayList<Bag> userBags = new ArrayList<>(user.getUserBags());
                    for (Bag bag : userBags) {
                        session.merge(bag);
                    }
                    session.beginTransaction();
                    session.merge(user);
                    session.getTransaction().commit();
                }

            }
            MyProject.getInstance().getDatabase().getClientHandlersMap().remove(logOutMessage.getSenderUser());
            MyProject.getInstance().getDatabase().getClientHandlersMap().put("", handler);
        }
    }
}

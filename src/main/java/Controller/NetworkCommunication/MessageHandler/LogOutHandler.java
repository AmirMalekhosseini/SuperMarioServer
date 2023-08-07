package Controller.NetworkCommunication.MessageHandler;

import Controller.Utils.HibernateUtils;
import Model.Game.OnlineUser;
import Model.NetworkCommunication.ClientHandler;
import Model.NetworkCommunication.Message.LogOutMessage;
import Model.NetworkCommunication.Message.Message;
import MyProject.MyProject;
import org.hibernate.Session;

public class LogOutHandler implements MessageHandler {
    @Override
    public void handleMessage(Message message) {
        if (message instanceof LogOutMessage) {
            LogOutMessage logOutMessage = (LogOutMessage) message;
            ClientHandler handler = MyProject.getInstance().getDatabase().getClientHandlersMap().get(logOutMessage.getTargetUser());
            OnlineUser test = MyProject.getInstance().getDatabase().getAllUsers().get(logOutMessage.getSenderUser());
            test.setUsername(logOutMessage.getSenderUser());
            test.getUserData().getUserHighScore().setUsername(logOutMessage.getSenderUser());
            try (Session session = HibernateUtils.getInstance().getSessionFactory().openSession()) {
                session.beginTransaction();
                session.save(test);
                session.getTransaction().commit();
                System.out.println("hihihi");
            }
            MyProject.getInstance().getDatabase().getClientHandlersMap().remove(logOutMessage.getSenderUser());
            MyProject.getInstance().getDatabase().getClientHandlersMap().put("", handler);
        }
    }
}

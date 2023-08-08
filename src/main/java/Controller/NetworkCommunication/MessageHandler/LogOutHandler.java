package Controller.NetworkCommunication.MessageHandler;

import Controller.Database.SaveData;
import Model.NetworkCommunication.ClientHandler;
import Model.NetworkCommunication.Message.LogOutMessage;
import Model.NetworkCommunication.Message.Message;
import MyProject.MyProject;

public class LogOutHandler implements MessageHandler {
    @Override
    public void handleMessage(Message message) {
        if (message instanceof LogOutMessage) {
            LogOutMessage logOutMessage = (LogOutMessage) message;
            ClientHandler handler = MyProject.getInstance().getDatabase().getClientHandlersMap().get(logOutMessage.getTargetUser());
            String username = logOutMessage.getSenderUser();
            SaveData.getInstance().saveToDatabase(username);
            MyProject.getInstance().getDatabase().getClientHandlersMap().remove(username);
            MyProject.getInstance().getDatabase().getClientHandlersMap().put("", handler);
        }
    }


}

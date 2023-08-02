package Model.NetworkCommunication.MessageHandler;

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
            MyProject.getInstance().getDatabase().getClientHandlersMap().remove(logOutMessage.getSenderUser());
            MyProject.getInstance().getDatabase().getClientHandlersMap().put("", handler);
        }
    }
}

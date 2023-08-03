package Controller.NetworkCommunication.MessageHandler;

import Model.NetworkCommunication.Message.ChooseBagMessage;
import Model.NetworkCommunication.Message.Message;
import MyProject.MyProject;

public class ChooseBagHandler implements MessageHandler {

    @Override
    public void handleMessage(Message message) {
        if (message instanceof ChooseBagMessage) {
            ChooseBagMessage chooseBagMessage = (ChooseBagMessage) message;
            int bagIndex = chooseBagMessage.getBagIndex();
            boolean isChoose = chooseBagMessage.isChoose();

            if (isChoose) {// Set it Active Bag
                MyProject.getInstance().getDatabase().getAllUsers().get(chooseBagMessage.getSenderUser()).setActiveBag(bagIndex);
            } else {// Remove Active Bag
                MyProject.getInstance().getDatabase().getAllUsers().get(chooseBagMessage.getSenderUser()).removeActiveBag();
            }

        }
    }
}

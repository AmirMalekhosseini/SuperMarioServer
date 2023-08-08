package Controller.NetworkCommunication.MessageHandler;

import Model.Game.OnlineUser;
import Model.NetworkCommunication.Message.BlockMessage;
import Model.NetworkCommunication.Message.Message;
import MyProject.MyProject;

public class BlockMessageHandler implements MessageHandler{


    @Override
    public void handleMessage(Message message) {

        if (message instanceof BlockMessage) {
            BlockMessage blockMessage = (BlockMessage) message;
            handleBlock(blockMessage);
        }

    }

    private void handleBlock(BlockMessage blockMessage) {

        String sender = blockMessage.getSenderUser();
        String target = blockMessage.getTargetUser();
        boolean isBlock = blockMessage.isBlock();
        OnlineUser senderUser = MyProject.getInstance().getDatabase().getAllUsers().get(sender);
        if (isBlock) {
            if (!senderUser.getBlockList().contains(target)) {
                senderUser.getBlockList().add(target);
            }
        } else {
            senderUser.getBlockList().remove(target);
        }

    }

}

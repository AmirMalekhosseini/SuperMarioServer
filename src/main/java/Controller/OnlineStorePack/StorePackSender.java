package Controller.OnlineStorePack;

import Model.NetworkCommunication.Message.MessageType;
import Model.NetworkCommunication.Message.PackMessage;
import Model.OnlineStorePack.Pack;
import MyProject.MyProject;

import java.io.IOException;

public class StorePackSender {

    private static StorePackSender sender;

    private StorePackSender() {
    }

    public static StorePackSender getInstance() {
        if (sender == null) {
            sender = new StorePackSender();
        }
        return sender;
    }

    public void sendPack(Pack pack, boolean isInitPack) {

        PackMessage packMessage = new PackMessage();
        packMessage.setMessageType(MessageType.PACK_MESSAGE);
        packMessage.setInitPack(isInitPack);
        // Add Pack Items:
        for (int i = 0; i < pack.packItems.size(); i++) {
            packMessage.getPackItems().add(pack.packItems.get(i).getClass().getName());
        }

        packMessage.setCount(pack.getCount());
        packMessage.setPackIndex(pack.getPackIndex());
        packMessage.setLevel(pack.getLevel());
        packMessage.setCountPerUser(pack.getCountPerUser());
        packMessage.setPrice(pack.getPrice());
        packMessage.setStartTime(String.valueOf(pack.getStartTime()));
        packMessage.setEndTime(String.valueOf(pack.getEndTime()));
        packMessage.setCurrency(pack.getCurrency().getClass().getName());

        // Send through Server
        try {
            for (String user : MyProject.getInstance().getDatabase().getClientHandlersMap().keySet()) {
                MyProject.getInstance().getDatabase().getClientHandlersMap().get(user).sendMessage(packMessage);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}

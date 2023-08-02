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

    public void sendPack(Pack pack) {

        PackMessage packMessage = new PackMessage();
        packMessage.setMessageType(MessageType.PACK_MESSAGE);
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
        // ToDo: for on all users.
        try {
            MyProject.getInstance().getDatabase().getClientHandlersMap().get("amir").sendMessage(packMessage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}

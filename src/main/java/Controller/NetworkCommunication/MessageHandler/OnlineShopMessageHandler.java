package Controller.NetworkCommunication.MessageHandler;

import Controller.Menu.ShopLogic;
import Model.Item.Coin;
import Model.NetworkCommunication.Message.Message;
import Model.NetworkCommunication.Message.MessageType;
import Model.NetworkCommunication.Message.OnlineShopMessage;
import Model.NetworkCommunication.Message.UpdateShopMessage;
import Model.Object.PackItem;
import Model.OnlineStorePack.Pack;
import MyProject.MyProject;
import java.io.IOException;

public class OnlineShopMessageHandler implements MessageHandler {

    ShopLogic shopLogic = new ShopLogic();

    @Override
    public void handleMessage(Message message) {

        if (message instanceof OnlineShopMessage) {
            OnlineShopMessage shopMessage = (OnlineShopMessage) message;
            String sender = shopMessage.getSenderUser();
            int packIndex = shopMessage.getPackIndex();
            boolean isShopValid = shopLogic.isShopValid(shopMessage);
            shopMessage.setShopValid(isShopValid);
            if (isShopValid) {
                updateData(shopMessage);
                shopMessage.setUserCoinValue(MyProject.getInstance().getDatabase().getAllUsers().get(sender).getUserData().getUserCoinValue());
                shopMessage.setUserDiamondValue(MyProject.getInstance().getDatabase().getAllUsers().get(sender).getUserData().getUserDiamondValue());
                try {
                    sendShopAnswer(shopMessage);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                UpdateShopMessage updateShopMessage = new UpdateShopMessage();
                updateShopMessage.setMessageType(MessageType.UPDATE_SHOP_MESSAGE);
                updateShopMessage.setPackIndex(packIndex);

                try {
                    for (String user : MyProject.getInstance().getDatabase().getClientHandlersMap().keySet()) {
                        MyProject.getInstance().getDatabase().getClientHandlersMap().get(user).sendMessage(updateShopMessage);
                    }

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }

    }

    private void sendShopAnswer(OnlineShopMessage shopMessage) throws IOException {
        MyProject.getInstance().getDatabase().getClientHandlersMap().get(shopMessage.getSenderUser()).sendMessage(shopMessage);
    }

    private void updateData(OnlineShopMessage shopMessage) {

        String sender = shopMessage.getSenderUser();
        int packIndex = shopMessage.getPackIndex();
        Pack pack = MyProject.getInstance().getDatabase().getPacks().get(packIndex);
        int packPrice = pack.getPrice();

        if (MyProject.getInstance().getDatabase().getPacks().get(packIndex).getCurrency().getClass().getName().equals(Coin.class.getName())) {
            int currentCoin = MyProject.getInstance().getDatabase().getAllUsers().get(sender).getUserData().getUserCoinValue();
            MyProject.getInstance().getDatabase().getAllUsers().get(sender).getUserData().setUserCoinValue(currentCoin - packPrice);
        } else {
            int currentDiamond = MyProject.getInstance().getDatabase().getAllUsers().get(sender).getUserData().getUserDiamondValue();
            MyProject.getInstance().getDatabase().getAllUsers().get(sender).getUserData().setUserDiamondValue(currentDiamond - packPrice);
        }

        if (pack.getCount() != -1) {
            int currentCount = pack.getCount();
            pack.setCount(currentCount - 1);
        } else {
            int currentCount = pack.getCountPerPersonLimit().get(sender);
            pack.getCountPerPersonLimit().put(sender, currentCount + 1);
        }

        addItemToUser(shopMessage);

    }

    private void addItemToUser(OnlineShopMessage shopMessage) {

        String sender = shopMessage.getSenderUser();
        int packIndex = shopMessage.getPackIndex();
        Pack pack = MyProject.getInstance().getDatabase().getPacks().get(packIndex);

        for (PackItem packItem : pack.packItems) {
            String packItemName = packItem.getClass().getName();
            int currentCount = MyProject.getInstance().getDatabase().getAllUsers().get(sender).getUserOnlineItems().getOrDefault(packItemName, 0);
            MyProject.getInstance().getDatabase().getAllUsers().get(sender).getUserOnlineItems().put(packItemName, currentCount + 1);
        }

    }


}

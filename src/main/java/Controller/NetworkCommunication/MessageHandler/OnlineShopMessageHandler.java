package Controller.NetworkCommunication.MessageHandler;

import Model.Item.Coin;
import Model.NetworkCommunication.Message.Message;
import Model.NetworkCommunication.Message.MessageType;
import Model.NetworkCommunication.Message.OnlineShopMessage;
import Model.NetworkCommunication.Message.UpdateShopMessage;
import Model.Object.PackItem;
import Model.OnlineStorePack.Pack;
import MyProject.MyProject;

import java.io.IOException;
import java.time.LocalDateTime;

public class OnlineShopMessageHandler implements MessageHandler {


    @Override
    public void handleMessage(Message message) {

        if (message instanceof OnlineShopMessage) {
            OnlineShopMessage shopMessage = (OnlineShopMessage) message;
            String sender = shopMessage.getSenderUser();
            int packIndex = shopMessage.getPackIndex();
            boolean isShopValid = isShopValid(shopMessage);
            shopMessage.setShopValid(isShopValid);
            if (isShopValid) {
                updateData(shopMessage);
                shopMessage.setUserCoinValue(MyProject.getInstance().getDatabase().getAllUsers().get(sender).getUserData().getUserCoinValue());
                shopMessage.setUserDiamondValue(MyProject.getInstance().getDatabase().getAllUsers().get(sender).getUserData().getUserDiamondValue());
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

    private boolean isShopValid(OnlineShopMessage shopMessage) {

        boolean isValid;

        isValid = checkCount(shopMessage) && checkCurrency(shopMessage)
                && checkLevel(shopMessage) && checkTime(shopMessage);

        System.out.println(isValid);

        return isValid;
    }

    private boolean checkCurrency(OnlineShopMessage shopMessage) {

        String sender = shopMessage.getSenderUser();
        int packIndex = shopMessage.getPackIndex();
        int packPrice = MyProject.getInstance().getDatabase().getPacks().get(packIndex).getPrice();

        // Set Currency
        if (MyProject.getInstance().getDatabase().getPacks().get(packIndex).getCurrency().getClass().getName().equals(Coin.class.getName())) {
            // Currency is Coin
            return MyProject.getInstance().getDatabase().getAllUsers().get(sender).getUserData().getUserCoinValue() >= packPrice;
        } else {// Currency is Diamond
            return MyProject.getInstance().getDatabase().getAllUsers().get(sender).getUserData().getUserDiamondValue() >= packPrice;
        }
    }

    private boolean checkCount(OnlineShopMessage shopMessage) {

        String sender = shopMessage.getSenderUser();
        int packIndex = shopMessage.getPackIndex();

        if (MyProject.getInstance().getDatabase().getPacks().get(packIndex).getCount() != -1) {
            // Count is Active:
            return MyProject.getInstance().getDatabase().getPacks().get(packIndex).getCount() >= 0;
        } else {
            // Count per Person is Active:
            if (!MyProject.getInstance().getDatabase().getPacks().get(packIndex).getCountPerPersonLimit().containsKey(sender)) {
                MyProject.getInstance().getDatabase().getPacks().get(packIndex).getCountPerPersonLimit().put(sender, 0);
            }

            int countUser = MyProject.getInstance().getDatabase().getPacks().get(packIndex).getCountPerPersonLimit().get(sender);
            int countPack = MyProject.getInstance().getDatabase().getPacks().get(packIndex).getCount();

            return countUser < countPack;
        }

    }

    private boolean checkLevel(OnlineShopMessage shopMessage) {

        String sender = shopMessage.getSenderUser();
        int packIndex = shopMessage.getPackIndex();

        int userLevel = MyProject.getInstance().getDatabase().getAllUsers().get(sender).getLevel();
        int packLevel = MyProject.getInstance().getDatabase().getPacks().get(packIndex).getLevel();

        return userLevel >= packLevel;
    }

    private boolean checkTime(OnlineShopMessage shopMessage) {

        int packIndex = shopMessage.getPackIndex();

        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime packTime = MyProject.getInstance().getDatabase().getPacks().get(packIndex).getEndTime();

        return !currentTime.isAfter(packTime);
    }
}

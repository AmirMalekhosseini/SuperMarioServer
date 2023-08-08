package Controller.Menu;

import Model.Item.Coin;
import Model.NetworkCommunication.Message.OnlineShopMessage;
import MyProject.MyProject;

import java.time.LocalDateTime;

public class ShopLogic {


    public boolean isShopValid(OnlineShopMessage shopMessage) {

        boolean isValid;

        isValid = checkCount(shopMessage) && checkCurrency(shopMessage)
                && checkLevel(shopMessage) && checkTime(shopMessage);

        System.out.println("Shop is " + isValid);

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
            int countPack = MyProject.getInstance().getDatabase().getPacks().get(packIndex).getCountPerUser();

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

package Controller.NetworkCommunication.MessageHandler;

import Model.NetworkCommunication.ClientHandler;
import Model.NetworkCommunication.Message.BagItemMessage;
import Model.NetworkCommunication.Message.Message;
import MyProject.MyProject;

import java.io.IOException;

public class BagItemHandler implements MessageHandler {
    @Override
    public void handleMessage(Message message) {

        if (message instanceof BagItemMessage) {
            BagItemMessage bagItemMessage = (BagItemMessage) message;
            String itemName = bagItemMessage.getItem();
            String sender = bagItemMessage.getSenderUser();
            int baxIndex = bagItemMessage.getBagIndex();
            boolean isItemAdded = bagItemMessage.isItemAdded();

            if (isItemAdded) {// Add Item to Bag
                if (checkUserHasItem(sender, itemName)) {// Item Added
                    addItemToBag(sender, baxIndex, itemName);
                }

            } else {// Remove Item from Bag
                removeItemFromBag(sender, baxIndex, itemName);
            }


        }

    }

    private boolean checkUserHasItem(String sender, String item) {

        return MyProject.getInstance().getDatabase().getAllUsers().get(sender).getUserOnlineItems().containsKey(item);

    }

    private void addItemToBag(String sender, int bagIndex, String itemName) {

        MyProject.getInstance().getDatabase().getAllUsers().get(sender).getUserBags().get(bagIndex).getBagItems().add(itemName);
        // If Bag is Active:
        if (bagIndex == MyProject.getInstance().getDatabase().getAllUsers().get(sender).getActiveBagIndex()) {
            MyProject.getInstance().getDatabase().getAllUsers().get(sender).getActiveBag().getBagItems().add(itemName);
        }
    }

    private void removeItemFromBag(String sender, int bagIndex, String itemName) {

        MyProject.getInstance().getDatabase().getAllUsers().get(sender).getUserBags().get(bagIndex).getBagItems().remove(itemName);
        // If Bag is Active:
        if (bagIndex == MyProject.getInstance().getDatabase().getAllUsers().get(sender).getActiveBagIndex()) {
            MyProject.getInstance().getDatabase().getAllUsers().get(sender).getActiveBag().getBagItems().remove(itemName);
        }

    }

}

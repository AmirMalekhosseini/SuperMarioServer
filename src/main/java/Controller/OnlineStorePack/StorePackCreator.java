package Controller.OnlineStorePack;

import Model.Item.Coin;
import Model.Item.Online.Diamond;
import Model.Object.StoreSubmitButton;
import Model.OnlineStorePack.Pack;
import MyProject.MyProject;

import java.time.LocalDateTime;

public class StorePackCreator {

    public void createPack(StoreSubmitButton storeSubmitButton) {

        Pack newPack = new Pack(storeSubmitButton.packItems, Integer.parseInt(storeSubmitButton.price.getText()));
        addPackItems(newPack, storeSubmitButton);
        newPack.setPackIndex(storeSubmitButton.getPackIndex());
        setPackToList(newPack);
        MyProject.getInstance().storePackSender.sendPack(newPack);

    }

    private void setPackToList(Pack newPack) {
        if (newPack.getPackIndex() < MyProject.getInstance().packs.size()) {
            // Index already exists, set the object at the desired index:
            MyProject.getInstance().packs.set(newPack.getPackIndex(), newPack);
        } else {
            // Index does not exist, expand the ArrayList:
            int currentSize = MyProject.getInstance().packs.size();
            for (int i = currentSize; i < newPack.getPackIndex(); i++) {
                MyProject.getInstance().packs.add(null);
            }
            MyProject.getInstance().packs.add(newPack);
        }
    }

    private void addPackItems(Pack newPack, StoreSubmitButton button) {

        if (button.unit.getText().equalsIgnoreCase("coin")) {// Coin
            newPack.setCurrency(new Coin());
        } else {// Diamond
            newPack.setCurrency(new Diamond());
        }

        newPack.setCount(Integer.parseInt(button.count.getText()));
        newPack.setCountPerUser(Integer.parseInt(button.countPerUser.getText()));
        newPack.setLevel(Integer.parseInt(button.level.getText()));
        newPack.setStartTime(LocalDateTime.parse(button.startTime.getText()));
        newPack.setEndTime(LocalDateTime.parse(button.endTime.getText()));

    }

}

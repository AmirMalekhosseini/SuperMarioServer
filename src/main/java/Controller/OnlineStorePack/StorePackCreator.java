package Controller.OnlineStorePack;

import Model.Item.Coin;
import Model.Item.Online.Diamond;
import Model.Item.Online.HealItem;
import Model.Item.Online.InvisibleItem;
import Model.Item.Online.SpeedItem;
import Model.Mario.Online.DamageBomb;
import Model.Mario.Online.Hammer;
import Model.Mario.Online.SpeedBomb;
import Model.Object.PackItem;
import Model.Object.StoreSubmitButton;
import Model.OnlineStorePack.Pack;
import MyProject.MyProject;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class StorePackCreator {

    private static StorePackCreator creator;

    private StorePackCreator() {
    }

    public static StorePackCreator getInstance() {
        if (creator == null) {
            creator = new StorePackCreator();
        }
        return creator;
    }

    public void createPack(StoreSubmitButton storeSubmitButton) {

        Pack newPack = new Pack(storeSubmitButton.packItems, Integer.parseInt(storeSubmitButton.price.getText()));
        addPackItems(newPack, storeSubmitButton);
        newPack.setPackIndex(storeSubmitButton.getPackIndex());
        setPackToList(newPack);
        StorePackSender.getInstance().sendPack(newPack);

    }

    public ArrayList<Pack> createInitPack() {


        ArrayList<Pack> storePacks = new ArrayList<>();
        int currentIndex = 0;

        ArrayList<PackItem> packItems = new ArrayList<>();
        packItems.add(new Hammer(0, 0, true));
        Pack hammerPack = new Pack(packItems, 50);
        hammerPack.setStartTime(LocalDateTime.parse("2023-08-01T00:00:00"));
        hammerPack.setEndTime(LocalDateTime.parse("2023-09-01T00:00:00"));
        hammerPack.setCountPerUser(1);
        hammerPack.setCurrency(new Diamond());
        hammerPack.setPackIndex(currentIndex);
        currentIndex++;
        storePacks.add(hammerPack);

        packItems = new ArrayList<>();
        packItems.add(new DamageBomb(0, 0));
        Pack damageBombPack = new Pack(packItems, 20);
        damageBombPack.setStartTime(LocalDateTime.parse("2023-08-01T00:00:00"));
        damageBombPack.setEndTime(LocalDateTime.parse("2023-09-01T00:00:00"));
        damageBombPack.setCurrency(new Coin());
        damageBombPack.setCount(10);
        damageBombPack.setLevel(2);
        damageBombPack.setPackIndex(currentIndex);
        currentIndex++;
        storePacks.add(damageBombPack);

        packItems = new ArrayList<>();
        packItems.add(new SpeedBomb(0, 0));
        Pack speedBombPack = new Pack(packItems, 20);
        speedBombPack.setStartTime(LocalDateTime.parse("2023-08-01T00:00:00"));
        speedBombPack.setEndTime(LocalDateTime.parse("2023-09-01T00:00:00"));
        speedBombPack.setCurrency(new Coin());
        speedBombPack.setCount(10);
        speedBombPack.setLevel(2);
        speedBombPack.setPackIndex(currentIndex);
        currentIndex++;
        storePacks.add(speedBombPack);

        packItems = new ArrayList<>();
        packItems.add(new HealItem(0, 0));
        Pack healPotionPack = new Pack(packItems, 40);
        healPotionPack.setStartTime(LocalDateTime.parse("2023-08-01T00:00:00"));
        healPotionPack.setEndTime(LocalDateTime.parse("2023-09-01T00:00:00"));
        healPotionPack.setCurrency(new Coin());
        healPotionPack.setCount(10);
        healPotionPack.setPackIndex(currentIndex);
        currentIndex++;
        storePacks.add(healPotionPack);

        packItems = new ArrayList<>();
        packItems.add(new InvisibleItem(0, 0));
        Pack invisiblePotionPack = new Pack(packItems, 30);
        invisiblePotionPack.setStartTime(LocalDateTime.parse("2023-08-01T00:00:00"));
        invisiblePotionPack.setEndTime(LocalDateTime.parse("2023-09-01T00:00:00"));
        invisiblePotionPack.setCurrency(new Coin());
        invisiblePotionPack.setCount(10);
        invisiblePotionPack.setPackIndex(currentIndex);
        currentIndex++;
        storePacks.add(invisiblePotionPack);

        packItems = new ArrayList<>();
        packItems.add(new SpeedItem(0, 0));
        Pack speedPotionPack = new Pack(packItems, 25);
        speedPotionPack.setStartTime(LocalDateTime.parse("2023-08-01T00:00:00"));
        speedPotionPack.setEndTime(LocalDateTime.parse("2023-09-01T00:00:00"));
        speedPotionPack.setCurrency(new Coin());
        speedPotionPack.setCount(10);
        speedPotionPack.setPackIndex(currentIndex);
        storePacks.add(speedPotionPack);

        return storePacks;
    }

    private void setPackToList(Pack newPack) {
        if (newPack.getPackIndex() < MyProject.getInstance().getDatabase().getPacks().size()) {
            // Index already exists, set the object at the desired index:
            MyProject.getInstance().getDatabase().getPacks().set(newPack.getPackIndex(), newPack);
        } else {
            // Index does not exist, expand the ArrayList:
            int currentSize = MyProject.getInstance().getDatabase().getPacks().size();
            for (int i = currentSize; i < newPack.getPackIndex(); i++) {
                MyProject.getInstance().getDatabase().getPacks().add(null);
            }
            MyProject.getInstance().getDatabase().getPacks().add(newPack);
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
        System.out.println("asfgkjbaskjfdb");

    }

}

package Model.OnlineStorePack;

import Model.Item.Currency;
import Model.Item.Online.OnlineItems;
import Model.Mario.Online.OnlineWeapon;
import Model.Object.PackItems;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Pack {

    public ArrayList<PackItems> packItems;
    private int price;
    private Currency currency;
    private int count;
    private int countPerUser;
    private int level;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int packIndex;

    public Pack(ArrayList<PackItems> packItems, int price) {
        this.price = price;
        this.packItems = packItems;
    }

    // Set These When Creating Pack in Server and Sends it to Clients.

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCountPerUser() {
        return countPerUser;
    }

    public void setCountPerUser(int countPerUser) {
        this.countPerUser = countPerUser;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPackIndex() {
        return packIndex;
    }

    public void setPackIndex(int packIndex) {
        this.packIndex = packIndex;
    }
}

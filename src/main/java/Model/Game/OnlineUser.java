package Model.Game;

import Model.Item.Online.Bag;
import Model.OnlineChat.UserChat;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class OnlineUser {

    private UserData userData;
    // String: Item Name. // Integer: Item Count. // Item: Online Weapon and Item
    private Map<String, Integer> userOnlineItems;
    private Map<String, ArrayList<UserChat>> userChatScreens;
    private ArrayList<String> userFriends;
    private ArrayList<Bag> userBags;
    private Bag activeBag;
    private int activeBagIndex=-1;

    public OnlineUser() {
        userData = new UserData();
        userOnlineItems = new ConcurrentHashMap<>();
        userChatScreens = new ConcurrentHashMap<>();
        userFriends = new ArrayList<>();
        userBags = new ArrayList<>(3);
        for (int i = 0; i < 3; i++) {
            Bag bag = new Bag();
            userBags.add(bag);
        }
    }

    public void setActiveBag(int baxIndex) {
        activeBag = userBags.get(baxIndex);
        activeBagIndex = baxIndex;
    }

    public void removeActiveBag() {
        activeBag = null;
        activeBagIndex = -1;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public ArrayList<Bag> getUserBags() {
        return userBags;
    }

    public void setUserBags(ArrayList<Bag> userBags) {
        this.userBags = userBags;
    }

    public Bag getActiveBag() {
        return activeBag;
    }

    public void setActiveBag(Bag activeBag) {
        this.activeBag = activeBag;
    }

    public Map<String, Integer> getUserOnlineItems() {
        return userOnlineItems;
    }

    public void setUserOnlineItems(Map<String, Integer> userOnlineItems) {
        this.userOnlineItems = userOnlineItems;
    }

    public int getActiveBagIndex() {
        return activeBagIndex;
    }

    public void setActiveBagIndex(int activeBagIndex) {
        this.activeBagIndex = activeBagIndex;
    }

    public Map<String, ArrayList<UserChat>> getUserChatScreens() {
        return userChatScreens;
    }

    public void setUserChatScreens(Map<String, ArrayList<UserChat>> userChatScreens) {
        this.userChatScreens = userChatScreens;
    }

    public ArrayList<String> getUserFriends() {
        return userFriends;
    }

    public void setUserFriends(ArrayList<String> userFriends) {
        this.userFriends = userFriends;
    }
}

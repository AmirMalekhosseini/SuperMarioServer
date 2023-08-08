package Model.Game;

import Model.Item.Online.Bag;
import Model.OnlineChat.UserChat;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Entity
@Table(name = "online_user")
public class OnlineUser {

    @Id
    @Column(name = "username")
    private String username;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username")
    private UserData userData;
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "user_online_items",
            joinColumns = @JoinColumn(name = "username")
    )
    @MapKeyColumn(name = "item_name")
    @Column(name = "item_count")
    // String: Item Name. // Integer: Item Count. // Item: Online Weapon and Item
    private Map<String, Integer> userOnlineItems;
    @Transient
    private Map<String, ArrayList<UserChat>> userChatScreens;
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "user_friends",
            joinColumns = @JoinColumn(name = "username")
    )
    @Column(name = "friend_username")
    private List<String> userFriends;
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "user_block_list",
            joinColumns = @JoinColumn(name = "username")
    )
    @Column(name = "block_user")
    private List<String> blockList;
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(
            name = "user_bags",
            joinColumns = @JoinColumn(name = "username")
    )
    @Column(name = "bags")
    private List<Bag> userBags;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "active_bag_id")
    private Bag activeBag;
    private int level;
    private int activeBagIndex = -1;

    public OnlineUser() {
        userData = new UserData();
        userOnlineItems = new ConcurrentHashMap<>();
        userChatScreens = new ConcurrentHashMap<>();
        userFriends = new ArrayList<>();
        blockList = new ArrayList<>();
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public ArrayList<Bag> getUserBags() {
        return new ArrayList<>(userBags);
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

    public List<String> getUserFriends() {
        return userFriends;
    }

    public void setUserFriends(List<String> userFriends) {
        this.userFriends = userFriends;
    }

    public List<String> getBlockList() {
        return blockList;
    }

    public void setBlockList(List<String> blockList) {
        this.blockList = blockList;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}

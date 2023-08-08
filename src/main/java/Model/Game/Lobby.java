package Model.Game;

import java.util.ArrayList;

public class Lobby {


    private final String lobbyName;
    private final String lobbyPassword;
    private final String admin;
    private ArrayList<String> coAdmins;
    private ArrayList<String> members;
    private ArrayList<String> blacklist;

    public Lobby(String lobbyName,String password) {
        this.lobbyPassword = password;
        this.lobbyName = lobbyName;
        this.admin = lobbyName;
        coAdmins = new ArrayList<>();
        members = new ArrayList<>();
        blacklist = new ArrayList<>();
    }

    public String getLobbyName() {
        return lobbyName;
    }

    public String getAdmin() {
        return admin;
    }

    public ArrayList<String> getCoAdmins() {
        return coAdmins;
    }

    public void setCoAdmins(ArrayList<String> coAdmins) {
        this.coAdmins = coAdmins;
    }

    public ArrayList<String> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<String> members) {
        this.members = members;
    }

    public String getLobbyPassword() {
        return lobbyPassword;
    }

    public ArrayList<String> getBlacklist() {
        return blacklist;
    }

    public void setBlacklist(ArrayList<String> blacklist) {
        this.blacklist = blacklist;
    }
}

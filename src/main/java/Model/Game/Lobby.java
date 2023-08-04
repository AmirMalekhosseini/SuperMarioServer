package Model.Game;

import java.util.ArrayList;

public class Lobby {


    private final String lobbyName;
    private final String admin;
    private ArrayList<String> coAdmins;
    private ArrayList<String> members;

    public Lobby(String lobbyName) {
        this.lobbyName = lobbyName;
        this.admin = lobbyName;
        coAdmins = new ArrayList<>();
        members = new ArrayList<>();
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
}

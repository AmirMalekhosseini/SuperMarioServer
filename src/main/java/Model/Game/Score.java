package Model.Game;


import javax.persistence.*;

@Entity
public class Score {

    @Id
    private String username;
    private int userScore = 0;

    public Score() {

    }
    public int getUserScore() {
        return userScore;
    }

    public void setUserScore(int userScore) {
        this.userScore = userScore;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

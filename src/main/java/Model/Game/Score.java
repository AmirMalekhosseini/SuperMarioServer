package Model.Game;


import javax.persistence.*;

public class Score {

    private int userScore = 0;

    public Score() {

    }
    public int getUserScore() {
        return userScore;
    }

    public void setUserScore(int userScore) {
        this.userScore = userScore;
    }

}

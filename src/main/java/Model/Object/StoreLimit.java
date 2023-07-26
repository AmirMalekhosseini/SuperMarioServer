package Model.Object;

import java.time.LocalDateTime;

public class StoreLimit {

    private int count;
    private int countPerUser;
    private int level;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean isUnitDiamond;// If False it is Coin.


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

    public boolean isUnitDiamond() {
        return isUnitDiamond;
    }

    public void setUnitDiamond(boolean unitDiamond) {
        isUnitDiamond = unitDiamond;
    }
}
/*

setStartTime(LocalDateTime.of(2023, 7, 1, 0, 0)); // Available from July 1, 2023, 00:00:00
setEndTime(LocalDateTime.of(2023, 7, 31, 23, 59)); // Available until July 31, 2023, 23:59:00
LocalDateTime now = LocalDateTime.now();

 */

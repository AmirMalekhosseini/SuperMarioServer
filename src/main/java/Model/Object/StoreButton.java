package Model.Object;

import MyProject.MyProjectData;

import javax.swing.*;
import java.awt.*;

public class StoreButton extends JButton {


    private int x;
    private int y;
    protected StoreMessageField count;
    protected StoreMessageField countPerUser;
    protected StoreMessageField startTime;
    protected StoreMessageField endTime;
    protected StoreMessageField unit;
    protected StoreMessageField level;

    public StoreButton(int x, int y, JPanel panel) {
        this.x = x;
        this.y = y;
        setBounds(x, y, 70, 40);
        setFont(MyProjectData.getProjectData().getFont10());
        setBackground(Color.BLACK);
        setForeground(Color.WHITE);
        setText("Submit");
        setFocusable(false);
        addMessageFields(panel, x);
        panel.add(this);
    }

    private void addMessageFields(JPanel panel,int x) {

        // Add Count Message Field:

        int xField = x - 120;
        int yField = y;
        count = new StoreMessageField("Count", xField, yField);
        panel.add(count);

        // Add CountPerPerson Message Field:
        xField = xField - 120;
        yField = y;
        countPerUser = new StoreMessageField("CountPerPerson", xField, yField);
        panel.add(countPerUser);

        // Add Start Time Message Field:
        xField = xField - 120;
        yField = y;
        startTime = new StoreMessageField("Start Time", xField, yField);
        panel.add(startTime);

        // Add End Time Message Field:
        xField = xField - 120;
        yField = y;
        endTime = new StoreMessageField("End Time", xField, yField);
        panel.add(endTime);

        // Add Unit Message Field:
        xField = xField - 120;
        yField = y;
        unit = new StoreMessageField("Unit", xField, yField);
        panel.add(unit);

        // Add Level Message Field:
        xField = xField - 120;
        yField = y;
        level = new StoreMessageField("Level", xField, yField);
        panel.add(level);

    }

    @Override
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}

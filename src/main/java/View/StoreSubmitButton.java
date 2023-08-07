package View;

import Controller.OnlineStorePack.StorePackCreator;
import Model.Object.PackItem;
import MyProject.MyProjectData;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StoreSubmitButton extends JButton {


    public ArrayList<PackItem> packItems = new ArrayList<>();
    private int x;
    private int y;
    private int packIndex;
    public StoreMessageField count;
    public StoreMessageField countPerUser;
    public StoreMessageField startTime;
    public StoreMessageField endTime;
    public StoreMessageField unit;
    public StoreMessageField level;
    public StoreMessageField price;

    public StoreSubmitButton(int x, int y, int packIndex, JPanel panel) {
        this.x = x;
        this.y = y;
        this.packIndex = packIndex;
        setBounds(x, y, 70, 40);
        setFont(MyProjectData.getProjectData().getFont10());
        setBackground(Color.BLACK);
        setForeground(Color.WHITE);
        setText("Submit");
        setFocusable(false);
        addMessageFields(panel, x);
        addAction();
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

        // Add Price Message Field:
        xField = xField - 120;
        yField = y;
        price = new StoreMessageField("Price", xField, yField);
        panel.add(price);

    }

    private void addAction() {

        addActionListener(e -> {
            StorePackCreator.getInstance().createPack(this);
        });

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

    public int getPackIndex() {
        return packIndex;
    }

    public void setPackIndex(int packIndex) {
        this.packIndex = packIndex;
    }
}

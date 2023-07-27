package Model.OnlineStorePack;

import Model.Object.ObjectsInGame;
import Model.Object.PackItems;
import MyProject.MyProjectData;
import javax.swing.*;
import java.awt.*;

public class MultiPackButton extends JButton {

    private int x;
    private int y;
    private PackItems storeItem;
    private boolean isItemChoose;

    public MultiPackButton(int x, int y, PackItems storeItem, JPanel panel) {
        this.x = x;
        this.y = y;
        setBounds(x, y, 70, 40);
        setFont(MyProjectData.getProjectData().getFont15());
        setBackground(Color.green);
        setForeground(Color.WHITE);
        setText("add");
        setFocusable(false);
        addAction();
        panel.add(this);
        this.storeItem = storeItem;
        panel.add((ObjectsInGame) storeItem);

    }

    private void addAction() {

        addActionListener(e -> {

            if (isItemChoose) {// Button is Red and User Removes Item:
                setBackground(Color.green);
                setText("add");
                setFont(MyProjectData.getProjectData().getFont15());
                isItemChoose = false;
            } else {
                setBackground(Color.red);
                setText("remove");
                setFont(MyProjectData.getProjectData().getFont10());
                isItemChoose = true;
            }

        });

    }

    public PackItems getStoreItem() {
        return storeItem;
    }

    public void setStoreItem(PackItems storeItem) {
        this.storeItem = storeItem;
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

    public boolean isItemChoose() {
        return isItemChoose;
    }

    public void setItemChoose(boolean itemChoose) {
        isItemChoose = itemChoose;
    }
}

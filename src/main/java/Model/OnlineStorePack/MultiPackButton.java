package Model.OnlineStorePack;

import Model.Object.ObjectsInGame;
import Model.Object.PackItem;
import Model.Object.StoreSubmitButton;
import MyProject.MyProjectData;
import javax.swing.*;
import java.awt.*;

public class MultiPackButton extends JButton {

    private int x;
    private int y;
    private PackItem packItem;
    private boolean isItemChoose;

    public MultiPackButton(int x, int y, PackItem packItem, JPanel panel, StoreSubmitButton storeSubmitButton) {
        this.x = x;
        this.y = y;
        setBounds(x, y, 70, 40);
        setFont(MyProjectData.getProjectData().getFont15());
        setBackground(Color.green);
        setForeground(Color.WHITE);
        setText("add");
        setFocusable(false);
        addAction(storeSubmitButton);
        panel.add(this);
        this.packItem = packItem;
        panel.add((ObjectsInGame) packItem);

    }

    private void addAction(StoreSubmitButton storeSubmitButton) {

        addActionListener(e -> {

            if (isItemChoose) {// Button is Red and User Removes Item:
                setBackground(Color.green);
                setText("add");
                setFont(MyProjectData.getProjectData().getFont15());
                storeSubmitButton.packItems.remove(packItem);
                isItemChoose = false;
            } else {
                setBackground(Color.red);
                setText("remove");
                setFont(MyProjectData.getProjectData().getFont10());
                storeSubmitButton.packItems.add(packItem);
                isItemChoose = true;
            }

        });

    }

    public PackItem getPackItem() {
        return packItem;
    }

    public void setPackItem(PackItem packItem) {
        this.packItem = packItem;
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

package Model.Item.Online;

import Model.Item.Currency;
import Model.Object.ObjectsInGame;
import MyProject.MyProjectData;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Diamond extends ObjectsInGame implements Currency {

    private BufferedImage background;
    private int x;
    private int y;
    private int width = 50;
    private int height = 50;

    public Diamond(int x, int y) {
        background = MyProjectData.getProjectData().getDiamond();
        this.x = x;
        this.y = y;
        setSize(width, height);
    }

    public Diamond() {
    }

    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.drawImage(background, 0, 0, width, height, null);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }
}

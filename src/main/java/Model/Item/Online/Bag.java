package Model.Item.Online;

import Model.Object.ObjectsInGame;
import MyProject.MyProjectData;
import javax.persistence.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bags")
public class Bag extends ObjectsInGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Transient
    private final BufferedImage background;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "bag_items",
            joinColumns = @JoinColumn(name = "id")
    )
    @Column(name = "items")
    private List<String> bagItems = new ArrayList<>();

    @Transient
    private int x;
    @Transient
    private int y;
    @Transient
    private int height = 80;
    @Transient
    private int width = 80;

    public Bag(int x, int y) {

        this.setSize(width, height);

        background = MyProjectData.getProjectData().getBag();

        this.x = x;
        this.y = y;

    }

    public Bag() {
        background = MyProjectData.getProjectData().getBag();
    }


    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.drawImage(background, -0, -0, width, height, null);
    }

    public List<String> getBagItems() {
        return bagItems;
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
    public int getHeight() {
        return height;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBagItems(List<String> bagItems) {
        this.bagItems = bagItems;
    }
}

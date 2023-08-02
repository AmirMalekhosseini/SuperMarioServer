package View;

import Model.Item.Online.HealItem;
import Model.Item.Online.InvisibleItem;
import Model.Item.Online.SpeedItem;
import Model.Mario.Online.DamageBomb;
import Model.Mario.Online.Hammer;
import Model.Mario.Online.SpeedBomb;
import Model.Object.StoreSubmitButton;
import Model.OnlineStorePack.MultiPackButton;
import MyProject.MyProjectData;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StoreFrame extends JFrame {


    protected ArrayList<MultiPackButton> multiPackButtons = new ArrayList<>();
    protected StoreSubmitButton healPotionButton;
    protected StoreSubmitButton speedPotionButton;
    protected StoreSubmitButton invisiblePotionButton;
    protected StoreSubmitButton hammerButton;
    protected StoreSubmitButton damageBombButton;
    protected StoreSubmitButton speedBombButton;
    protected StoreSubmitButton multiPackButton;
    protected HealItem healPotionLabel;
    protected InvisibleItem invisiblePotionLabel;
    protected SpeedItem speedPotionLabel;
    protected Hammer hammer;
    protected DamageBomb damageBomb;
    protected SpeedBomb speedBomb;
    protected JPanel panel;
    protected JButton backButton;
    protected MultiPackButton healAddButton;
    protected MultiPackButton speedAddButton;
    protected MultiPackButton invisibleAddButton;
    protected MultiPackButton damageBombAddButton;
    protected MultiPackButton speedBombAddButton;
    protected MultiPackButton hammerAddButton;
    private int currentPackIndex = 0;

    public StoreFrame() {
        super("Store Frame");
        setLayout(null);
        setSize(1000, 900);
        setBackground(Color.RED);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setIconImage(MyProjectData.getProjectData().getGameIcon().getImage());
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 1000, 900);
        panel.setVisible(true);
        setContentPane(panel);

        backButton = new JButton("Back");
        backButton.setBounds(0, 0, 70, 40);
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.setFocusable(false);
        backButton.setFont(MyProjectData.getProjectData().getFont15());
        panel.add(backButton);

        init();
        addInitPacks();
        addMultiPack();
        addButtonAction();

    }

    private void init() {

        hammerButton = new StoreSubmitButton(910, 360, currentPackIndex, panel);
        currentPackIndex++;
        damageBombButton = new StoreSubmitButton(910, 460, currentPackIndex, panel);
        currentPackIndex++;
        speedBombButton = new StoreSubmitButton(910, 560, currentPackIndex, panel);
        currentPackIndex++;
        healPotionButton = new StoreSubmitButton(910, 60, currentPackIndex, panel);
        currentPackIndex++;
        invisiblePotionButton = new StoreSubmitButton(910, 260, currentPackIndex, panel);
        currentPackIndex++;
        speedPotionButton = new StoreSubmitButton(910, 160, currentPackIndex, panel);
        currentPackIndex++;
        multiPackButton = new StoreSubmitButton(880, 780, currentPackIndex, panel);

        hammer = new Hammer(-5, 50, true);
        panel.add(hammer);

        damageBomb = new DamageBomb(0, 150);
        panel.add(damageBomb);

        speedBomb = new SpeedBomb(0, 250);
        panel.add(speedBomb);

        healPotionLabel = new HealItem(10, 350);
        panel.add(healPotionLabel);

        invisiblePotionLabel = new InvisibleItem(10, 450);
        panel.add(invisiblePotionLabel);

        speedPotionLabel = new SpeedItem(10, 550);
        panel.add(speedPotionLabel);

    }

    private void addMultiPack() {

        healAddButton = new MultiPackButton(80, 700, new HealItem(80, 630), panel, multiPackButton);
        speedAddButton = new MultiPackButton(230, 700, new SpeedItem(230, 630), panel, multiPackButton);
        invisibleAddButton = new MultiPackButton(380, 700, new InvisibleItem(380, 630), panel, multiPackButton);
        damageBombAddButton = new MultiPackButton(530, 700, new DamageBomb(530, 630), panel, multiPackButton);
        speedBombAddButton = new MultiPackButton(680, 700, new SpeedBomb(680, 630), panel, multiPackButton);
        hammerAddButton = new MultiPackButton(830, 700, new Hammer(830, 630, true), panel, multiPackButton);

    }

    private void addInitPacks() {

        healPotionButton.packItems.add(new HealItem(0, 0));
        speedPotionButton.packItems.add(new SpeedItem(0, 0));
        invisiblePotionButton.packItems.add(new InvisibleItem(0, 0));
        hammerButton.packItems.add(new Hammer(0, 0));
        damageBombButton.packItems.add(new DamageBomb(0, 0));
        speedBombButton.packItems.add(new SpeedBomb(0, 0));

    }

    private void addButtonAction() {
        // ToDo
    }

}

package View;

import Model.Item.Online.HealItem;
import Model.Item.Online.InvisibleItem;
import Model.Item.Online.SpeedItem;
import Model.Mario.Online.DamageBomb;
import Model.Mario.Online.Hammer;
import Model.Mario.Online.SpeedBomb;
import Model.Object.StoreButton;
import Model.OnlineStorePack.MultiPackButton;
import MyProject.MyProjectData;

import javax.swing.*;
import java.awt.*;

public class StoreFrame extends JFrame {


    protected StoreButton healPotionButton;
    protected StoreButton speedPotionButton;
    protected StoreButton invisiblePotionButton;
    protected StoreButton hammerButton;
    protected StoreButton damageBombButton;
    protected StoreButton speedBombButton;
    protected StoreButton multiPackButton;
    protected HealItem healPotionLabel;
    protected InvisibleItem invisiblePotionLabel;
    protected SpeedItem speedPotionLabel;
    protected Hammer hammer;
    protected DamageBomb damageBomb;
    protected SpeedBomb speedBomb;
    protected JPanel panel;
    protected JButton backButton;
    protected MultiPackButton addHealButton;
    protected MultiPackButton addSpeedButton;
    protected MultiPackButton addInvisibleButton;
    protected MultiPackButton addDamageBombButton;
    protected MultiPackButton addSpeedBombButton;
    protected MultiPackButton addHammerButton;

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
        addMultiPack();
        addButtonAction();

    }

    private void init() {

        healPotionButton = new StoreButton(900, 30, panel);
        speedPotionButton = new StoreButton(900, 130, panel);
        invisiblePotionButton = new StoreButton(900, 230, panel);
        hammerButton = new StoreButton(900, 330, panel);
        damageBombButton = new StoreButton(900, 430, panel);
        speedBombButton = new StoreButton(900, 530, panel);
        multiPackButton = new StoreButton(850, 750, panel);

        healPotionLabel = new HealItem(100, 20);
        panel.add(healPotionLabel);

        speedPotionLabel = new SpeedItem(100, 120);
        panel.add(speedPotionLabel);

        invisiblePotionLabel = new InvisibleItem(100, 220);
        panel.add(invisiblePotionLabel);

        hammer = new Hammer(85, 320, true);
        panel.add(hammer);

        damageBomb = new DamageBomb(85, 420);
        panel.add(damageBomb);

        speedBomb = new SpeedBomb(85, 520);
        panel.add(speedBomb);

    }

    private void addMultiPack() {

        addHealButton = new MultiPackButton(100, 670, new HealItem(100, 600), panel);
        addSpeedButton = new MultiPackButton(250, 670, new SpeedItem(250, 600), panel);
        addInvisibleButton = new MultiPackButton(400, 670, new InvisibleItem(400, 600), panel);
        addDamageBombButton = new MultiPackButton(550, 670, new DamageBomb(550, 600), panel);
        addSpeedBombButton = new MultiPackButton(700, 670, new SpeedBomb(700, 600), panel);
        addHammerButton = new MultiPackButton(850, 670, new Hammer(850, 600, true), panel);

    }

    private void addButtonAction() {
        // ToDo
    }

}

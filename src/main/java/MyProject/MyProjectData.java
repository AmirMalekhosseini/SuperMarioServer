package MyProject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MyProjectData {

    private static MyProjectData projectData;

    protected ImageIcon gameIcon;
    protected ImageIcon gameMenuImage;
    protected BufferedImage sword;
    protected BufferedImage swordFilliped;
    protected BufferedImage fireBall;
    protected BufferedImage shield;
    protected BufferedImage fireMario;
    protected BufferedImage fireMario_Mini;
    protected BufferedImage fireMario_Filliped;
    protected BufferedImage fireMario_Filliped_Mini;

    protected BufferedImage healPotion;
    protected BufferedImage speedPotion;
    protected BufferedImage invisiblePotion;
    protected BufferedImage hammer;
    protected BufferedImage hammerFilliped;
    protected BufferedImage speedBomb;
    protected BufferedImage damageBomb;

    protected BufferedImage blockInAir;
    protected BufferedImage slimeBlockInAir;
    protected BufferedImage emptyBlockInAir;
    protected BufferedImage castle;
    protected BufferedImage checkPoint;
    protected BufferedImage coin;
    protected BufferedImage mushroom;
    protected BufferedImage star;
    protected BufferedImage flowerItem;
    protected BufferedImage coinForStore;
    protected BufferedImage pipe;
    protected BufferedImage pipeHorizontal;
    protected BufferedImage plant;
    protected BufferedImage goompa;
    protected BufferedImage goompa_Filliped;
    protected BufferedImage nukeBird;
    protected BufferedImage nukeBird_Filliped;
    protected BufferedImage birdBomb;
    protected BufferedImage turtle;
    protected BufferedImage turtle_Filliped;
    protected BufferedImage spiny;
    protected BufferedImage spiny_Filliped;
    protected BufferedImage activePrizeInAir;
    protected BufferedImage inActivePrizeInAir;

    protected Font font10;
    protected Font font12;
    protected Font font15;
    protected Font font20;
    protected Font font22;
    protected Font font35;

    private MyProjectData() {

        importImages();
        importMarios();
        importWeapons();
        importFonts();
        importObjectsInGame();

    }

    public static MyProjectData getProjectData() {
        if (projectData == null) {
            projectData = new MyProjectData();
        }
        return projectData;
    }

    public void importObjectsInGame() {



        try {
            String pathBackground = "Objects/BlockInAir.png";
            File fileBackground = new File(pathBackground);
            blockInAir = ImageIO.read(fileBackground);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String pathBackground = "Objects/Slime.png";
            File fileBackground = new File(pathBackground);
            slimeBlockInAir = ImageIO.read(fileBackground);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String pathBackground = "Objects/EmptyBlockInAir.png";
            File fileBackground = new File(pathBackground);
            emptyBlockInAir = ImageIO.read(fileBackground);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String pathBackground = "Objects/Castle.png";
            File fileBackground = new File(pathBackground);
            castle = ImageIO.read(fileBackground);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String pathBackground = "Item/Checkpoint.png";
            File fileBackground = new File(pathBackground);
            checkPoint = ImageIO.read(fileBackground);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String pathBackground = "Item/Coin.png";
            File fileBackground = new File(pathBackground);
            coin = ImageIO.read(fileBackground);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String pathBackground = "Item/CoinForStore.png";
            File fileBackground = new File(pathBackground);
            coinForStore = ImageIO.read(fileBackground);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String pathBackground = "Item/FlowerItem.png";
            File fileBackground = new File(pathBackground);
            flowerItem = ImageIO.read(fileBackground);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String pathBackground = "Item/Mushroom.png";
            File fileBackground = new File(pathBackground);
            mushroom = ImageIO.read(fileBackground);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String pathBackground = "Item/Star.png";
            File fileBackground = new File(pathBackground);
            star = ImageIO.read(fileBackground);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String pathBackground = "Item/Online/HealPotion.png";
            File fileBackground = new File(pathBackground);
            healPotion = ImageIO.read(fileBackground);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String pathBackground = "Item/Online/SpeedPotion.png";
            File fileBackground = new File(pathBackground);
            speedPotion = ImageIO.read(fileBackground);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String pathBackground = "Item/Online/InvisiblePotion.png";
            File fileBackground = new File(pathBackground);
            invisiblePotion = ImageIO.read(fileBackground);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String pathBackground = "Objects/Pipe.png";
            File fileBackground = new File(pathBackground);
            pipe = ImageIO.read(fileBackground);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String pathBackground = "Objects/PipeHorizontal.png";
            File fileBackground = new File(pathBackground);
            pipeHorizontal = ImageIO.read(fileBackground);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String pathBackground = "Enemy/Plant.png";
            File fileBackground = new File(pathBackground);
            plant = ImageIO.read(fileBackground);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String pathBackground = "Enemy/Goompa.png";
            File fileBackground = new File(pathBackground);
            goompa = ImageIO.read(fileBackground);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String pathBackground = "Enemy/GoompaFilliped.png";
            File fileBackground = new File(pathBackground);
            goompa_Filliped = ImageIO.read(fileBackground);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String pathBackground = "Enemy/NukeBird.png";
            File fileBackground = new File(pathBackground);
            nukeBird = ImageIO.read(fileBackground);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String pathBackground = "Enemy/NukeBirdFilliped.png";
            File fileBackground = new File(pathBackground);
            nukeBird_Filliped = ImageIO.read(fileBackground);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String pathBackground = "Enemy/Bomb.png";
            File fileBackground = new File(pathBackground);
            birdBomb = ImageIO.read(fileBackground);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String pathBackground = "Enemy/Turtle.png";
            File fileBackground = new File(pathBackground);
            turtle = ImageIO.read(fileBackground);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String pathBackground = "Enemy/TurtleFilliped.png";
            File fileBackground = new File(pathBackground);
            turtle_Filliped = ImageIO.read(fileBackground);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String pathBackground = "Enemy/Spiny.png";
            File fileBackground = new File(pathBackground);
            spiny = ImageIO.read(fileBackground);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String pathBackground = "Enemy/SpinyFilliped.png";
            File fileBackground = new File(pathBackground);
            spiny_Filliped = ImageIO.read(fileBackground);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String pathBackground = "Objects/PrizeInAir_Active.png";
            File fileBackground = new File(pathBackground);
            activePrizeInAir = ImageIO.read(fileBackground);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String pathBackground = "Objects/PrizeInAir_InActive.png";
            File fileBackground = new File(pathBackground);
            inActivePrizeInAir = ImageIO.read(fileBackground);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void importImages() {

        gameIcon = new ImageIcon("Game/GameIcon.jpeg");
        gameMenuImage = new ImageIcon("Game/GameMenuImage.jpg");

    }

    public void importFonts() {

        font10 = new Font("Comic Sans MS", Font.PLAIN, 10);
        font12 = new Font("Comic Sans MS", Font.PLAIN, 12);
        font15 = new Font("Comic Sans MS", Font.PLAIN, 15);
        font20 = new Font("Comic Sans MS", Font.PLAIN, 20);
        font22 = new Font("Comic Sans MS", Font.PLAIN, 22);
        font35 = new Font("Comic Sans MS", Font.PLAIN, 35);

    }

    public void importMarios() {

        importFireMario();

    }

    public void importWeapons() {

        try {
            String pathBackground = "MarioWeapon/Fireball.png";
            File fileBackground = new File(pathBackground);
            fireBall = ImageIO.read(fileBackground);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String pathBackground = "MarioWeapon/Sword.png";
            File fileBackground = new File(pathBackground);
            sword = ImageIO.read(fileBackground);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String pathBackground = "MarioWeapon/SwordFilliped.png";
            File fileBackground = new File(pathBackground);
            swordFilliped = ImageIO.read(fileBackground);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String pathBackground = "MarioWeapon/Shield.png";
            File fileBackground = new File(pathBackground);
            shield = ImageIO.read(fileBackground);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String pathBackground = "MarioWeapon/Online/Hammer.png";
            File fileBackground = new File(pathBackground);
            hammer = ImageIO.read(fileBackground);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String pathBackground = "MarioWeapon/Online/HammerFilliped.png";
            File fileBackground = new File(pathBackground);
            hammerFilliped = ImageIO.read(fileBackground);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String pathBackground = "MarioWeapon/Online/DamageBomb.png";
            File fileBackground = new File(pathBackground);
            damageBomb = ImageIO.read(fileBackground);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String pathBackground = "MarioWeapon/Online/SpeedBomb.png";
            File fileBackground = new File(pathBackground);
            speedBomb = ImageIO.read(fileBackground);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void importFireMario() {

        try {
            String pathBackground = "mario/FireMario/FireMario.png";
            File fileBackground = new File(pathBackground);
            fireMario = ImageIO.read(fileBackground);

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String pathBackground = "mario/FireMario/FireMario_Mini.png";
            File fileBackground = new File(pathBackground);
            fireMario_Mini = ImageIO.read(fileBackground);

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String pathBackground = "MarioFilliped/FireMario/FireMario.png";
            File fileBackground = new File(pathBackground);
            fireMario_Filliped = ImageIO.read(fileBackground);

        }
        catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String pathBackground = "MarioFilliped/FireMario/FireMario_Mini.png";
            File fileBackground = new File(pathBackground);
            fireMario_Filliped_Mini = ImageIO.read(fileBackground);

        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ImageIcon getGameIcon() {
        return gameIcon;
    }

    public ImageIcon getGameMenuImage() {
        return gameMenuImage;
    }

    public Font getFont10() {
        return font10;
    }

    public Font getFont12() {
        return font12;
    }

    public Font getFont15() {
        return font15;
    }

    public Font getFont20() {
        return font20;
    }

    public Font getFont22() {
        return font22;
    }

    public Font getFont35() {
        return font35;
    }

    public BufferedImage getBlockInAir() {
        return blockInAir;
    }

    public BufferedImage getSlimeBlockInAir() {
        return slimeBlockInAir;
    }

    public BufferedImage getEmptyBlockInAir() {
        return emptyBlockInAir;
    }

    public BufferedImage getCastle() {
        return castle;
    }

    public BufferedImage getCheckPoint() {
        return checkPoint;
    }

    public BufferedImage getHealPotion() {
        return healPotion;
    }

    public BufferedImage getSpeedPotion() {
        return speedPotion;
    }

    public BufferedImage getInvisiblePotion() {
        return invisiblePotion;
    }

    public BufferedImage getCoin() {
        return coin;
    }
    public BufferedImage getMushroom() {
        return mushroom;
    }
    public BufferedImage getStar() {
        return star;
    }
    public BufferedImage getFlowerItem() {
        return flowerItem;
    }
    public BufferedImage getCoinForStore() {
        return coinForStore;
    }
    public BufferedImage getPipe() {
        return pipe;
    }
    public BufferedImage getPipeHorizontal() {
        return pipeHorizontal;
    }
    public BufferedImage getPlant() {
        return plant;
    }
    public BufferedImage getGoompa() {
        return goompa;
    }
    public BufferedImage getNukeBird() {
        return nukeBird;
    }
    public BufferedImage getBirdBomb() {
        return birdBomb;
    }
    public BufferedImage getTurtle() {
        return turtle;
    }
    public BufferedImage getSpiny() {
        return spiny;
    }

    public BufferedImage getGoompa_Filliped() {
        return goompa_Filliped;
    }

    public BufferedImage getNukeBird_Filliped() {
        return nukeBird_Filliped;
    }

    public BufferedImage getTurtle_Filliped() {
        return turtle_Filliped;
    }

    public BufferedImage getSpiny_Filliped() {
        return spiny_Filliped;
    }

    public BufferedImage getActivePrizeInAir() {
        return activePrizeInAir;
    }
    public BufferedImage getInActivePrizeInAir() {
        return inActivePrizeInAir;
    }

    public BufferedImage getFireMario() {
        return fireMario;
    }

    public BufferedImage getFireMario_Mini() {
        return fireMario_Mini;
    }

    public BufferedImage getFireMario_Filliped() {
        return fireMario_Filliped;
    }

    public BufferedImage getFireMario_Filliped_Mini() {
        return fireMario_Filliped_Mini;
    }

    public BufferedImage getShield() {
        return shield;
    }

    public BufferedImage getFireBall() {
        return fireBall;
    }

    public BufferedImage getSword() {
        return sword;
    }

    public BufferedImage getSwordFilliped() {
        return swordFilliped;
    }

    public BufferedImage getHammer() {
        return hammer;
    }

    public BufferedImage getHammerFilliped() {
        return hammerFilliped;
    }

    public BufferedImage getSpeedBomb() {
        return speedBomb;
    }

    public BufferedImage getDamageBomb() {
        return damageBomb;
    }

}

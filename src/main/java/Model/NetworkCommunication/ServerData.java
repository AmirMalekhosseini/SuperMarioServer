package Model.NetworkCommunication;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

public class ServerData {

    // Fields that will be dynamically initialized from the properties file
    private int diamondConversionRate;
    private double levelMultiplier;
    private double speedPotionMultiplier;
    private int speedPotionPeriod;
    private int invisibilityPotionPeriod;
    private int healthPotionHPPercent;
    private double damageBombBlockArea;
    private int damageBombPercent;
    private double speedBombBlockArea;
    private double speedBombMultiplier;
    private int hammerStunPeriod;
    private double swordBlockRange;
    private int swordPercentDamage;
    private double swordPushBackBlock;
    private int swordCoolDown;
    private double marathonMultiplierSpeed;
    private double marathonMultiplierSlowDown;
    private int marathonPeriodSlowDown;
    private double marathonLifeTimeMultiplier;
    private double marathonDistanceMultiplier;
    private int marathonMinLifeTime;
    private int marathonMinDistance;
    private double survivalDamageMultiplier;
    private double survivalEquipmentMultiplier;

    public ServerData() {
        readPropertiesAndInitializeFields();
    }

    private void readPropertiesAndInitializeFields() {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream("ServerData.properties")) {
            properties.load(input);
            for (String propertyName : properties.stringPropertyNames()) {
                String propertyValue = properties.getProperty(propertyName);
                initializeField(propertyName, propertyValue);
            }
        } catch (IOException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void initializeField(String propertyName, String propertyValue)
            throws NoSuchFieldException, IllegalAccessException {
        Field field = this.getClass().getDeclaredField(propertyName);
        field.setAccessible(true);

        // Convert the property value to the correct field type and set it in the class
        try {
            if (field.getType() == int.class || field.getType() == Integer.class) {
                field.setInt(this, Integer.parseInt(propertyValue));
            } else if (field.getType() == double.class || field.getType() == Double.class) {
                field.setDouble(this, Double.parseDouble(propertyValue));
            }
            // Add more type conversions as needed for other data types
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public int getDiamondConversionRate() {
        return diamondConversionRate;
    }

    public void setDiamondConversionRate(int diamondConversionRate) {
        this.diamondConversionRate = diamondConversionRate;
    }

    public double getLevelMultiplier() {
        return levelMultiplier;
    }

    public void setLevelMultiplier(double levelMultiplier) {
        this.levelMultiplier = levelMultiplier;
    }

    public double getSpeedPotionMultiplier() {
        return speedPotionMultiplier;
    }

    public void setSpeedPotionMultiplier(double speedPotionMultiplier) {
        this.speedPotionMultiplier = speedPotionMultiplier;
    }

    public int getSpeedPotionPeriod() {
        return speedPotionPeriod;
    }

    public void setSpeedPotionPeriod(int speedPotionPeriod) {
        this.speedPotionPeriod = speedPotionPeriod;
    }

    public int getInvisibilityPotionPeriod() {
        return invisibilityPotionPeriod;
    }

    public void setInvisibilityPotionPeriod(int invisibilityPotionPeriod) {
        this.invisibilityPotionPeriod = invisibilityPotionPeriod;
    }

    public int getHealthPotionHPPercent() {
        return healthPotionHPPercent;
    }

    public void setHealthPotionHPPercent(int healthPotionHPPercent) {
        this.healthPotionHPPercent = healthPotionHPPercent;
    }

    public double getDamageBombBlockArea() {
        return damageBombBlockArea;
    }

    public void setDamageBombBlockArea(double damageBombBlockArea) {
        this.damageBombBlockArea = damageBombBlockArea;
    }

    public int getDamageBombPercent() {
        return damageBombPercent;
    }

    public void setDamageBombPercent(int damageBombPercent) {
        this.damageBombPercent = damageBombPercent;
    }

    public double getSpeedBombBlockArea() {
        return speedBombBlockArea;
    }

    public void setSpeedBombBlockArea(double speedBombBlockArea) {
        this.speedBombBlockArea = speedBombBlockArea;
    }

    public double getSpeedBombMultiplier() {
        return speedBombMultiplier;
    }

    public void setSpeedBombMultiplier(double speedBombMultiplier) {
        this.speedBombMultiplier = speedBombMultiplier;
    }

    public int getHammerStunPeriod() {
        return hammerStunPeriod;
    }

    public void setHammerStunPeriod(int hammerStunPeriod) {
        this.hammerStunPeriod = hammerStunPeriod;
    }

    public double getSwordBlockRange() {
        return swordBlockRange;
    }

    public void setSwordBlockRange(double swordBlockRange) {
        this.swordBlockRange = swordBlockRange;
    }

    public int getSwordPercentDamage() {
        return swordPercentDamage;
    }

    public void setSwordPercentDamage(int swordPercentDamage) {
        this.swordPercentDamage = swordPercentDamage;
    }

    public double getSwordPushBackBlock() {
        return swordPushBackBlock;
    }

    public void setSwordPushBackBlock(double swordPushBackBlock) {
        this.swordPushBackBlock = swordPushBackBlock;
    }

    public int getSwordCoolDown() {
        return swordCoolDown;
    }

    public void setSwordCoolDown(int swordCoolDown) {
        this.swordCoolDown = swordCoolDown;
    }

    public double getMarathonMultiplierSpeed() {
        return marathonMultiplierSpeed;
    }

    public void setMarathonMultiplierSpeed(double marathonMultiplierSpeed) {
        this.marathonMultiplierSpeed = marathonMultiplierSpeed;
    }

    public double getMarathonMultiplierSlowDown() {
        return marathonMultiplierSlowDown;
    }

    public void setMarathonMultiplierSlowDown(double marathonMultiplierSlowDown) {
        this.marathonMultiplierSlowDown = marathonMultiplierSlowDown;
    }

    public int getMarathonPeriodSlowDown() {
        return marathonPeriodSlowDown;
    }

    public void setMarathonPeriodSlowDown(int marathonPeriodSlowDown) {
        this.marathonPeriodSlowDown = marathonPeriodSlowDown;
    }

    public double getMarathonLifeTimeMultiplier() {
        return marathonLifeTimeMultiplier;
    }

    public void setMarathonLifeTimeMultiplier(double marathonLifeTimeMultiplier) {
        this.marathonLifeTimeMultiplier = marathonLifeTimeMultiplier;
    }

    public double getMarathonDistanceMultiplier() {
        return marathonDistanceMultiplier;
    }

    public void setMarathonDistanceMultiplier(double marathonDistanceMultiplier) {
        this.marathonDistanceMultiplier = marathonDistanceMultiplier;
    }

    public int getMarathonMinLifeTime() {
        return marathonMinLifeTime;
    }

    public void setMarathonMinLifeTime(int marathonMinLifeTime) {
        this.marathonMinLifeTime = marathonMinLifeTime;
    }

    public int getMarathonMinDistance() {
        return marathonMinDistance;
    }

    public void setMarathonMinDistance(int marathonMinDistance) {
        this.marathonMinDistance = marathonMinDistance;
    }

    public double getSurvivalDamageMultiplier() {
        return survivalDamageMultiplier;
    }

    public void setSurvivalDamageMultiplier(double survivalDamageMultiplier) {
        this.survivalDamageMultiplier = survivalDamageMultiplier;
    }

    public double getSurvivalEquipmentMultiplier() {
        return survivalEquipmentMultiplier;
    }

    public void setSurvivalEquipmentMultiplier(double survivalEquipmentMultiplier) {
        this.survivalEquipmentMultiplier = survivalEquipmentMultiplier;
    }
}

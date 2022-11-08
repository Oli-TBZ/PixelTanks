import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.GreenfootSound;
import java.util.List;

/**
 * Write a description of class Battleground here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Battleground extends World
{
    private Actor timerDisplay = new SimpleActor();
    private Actor scoreDisplay = new SimpleActor();
    private Actor gameOverDisplay = new SimpleActor();
    private Actor startDisplay = new SimpleActor();
    private Actor creditsDisplay = new SimpleActor();
    
    private int score;
    private int timeElapsed;
    private int timeCounter; 
    private int bossHp;
    
    private int maxEnemies;
    private int enemyLvl;
    private int timecache;
    
    private int[] enemySpawnlocation;
    
    private GreenfootSound lvlmusic = new GreenfootSound("lvl.mp3");
    private GreenfootSound bossmusic = new GreenfootSound("boss.mp3");
    /**
     * Constructor for objects of class Battleground.
     * 
     */
    public Battleground()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 800, 1);
        prepare();
        act();
        enemySpawnControll();
        updateTimerDisplay();
        addObject(timerDisplay,50,20);
        addObject(scoreDisplay,950,20);
        addObject(startDisplay, 500, 380);
        addObject(creditsDisplay, 500, 420);
        
        startDisplay.setImage(new GreenfootImage("PixelTanks", 70, null, null));
        creditsDisplay.setImage(new GreenfootImage("By Oliver Ammann & Senthil Nagendran", 30, null, null));
        
        this.enemyLvl = 1;
        this.maxEnemies = 1;
        this.score = 0;
        this.bossHp = 4;
    }
    public void act(){
        timeCounter = (timeCounter+1)%55;
        if (timeCounter == 0){
            timeElapsed++;
            updateTimerDisplay();
        }
        if (enemyLvl == 3){
            checkBossStatus();
            lvlmusic.stop();
            bossmusic.playLoop();
        } 
        if (startDisplay.getWorld() != null){
            removeObject(startDisplay);
            removeObject(creditsDisplay);
        }
        updateScoreDisplay();
        enemySpawnControll();
        checkPlayerStatus();
    }
    private void updateTimerDisplay(){
        timerDisplay.setImage(new GreenfootImage("Timer: "+ timeElapsed, 24, null, null));
    }
    private void updateScoreDisplay(){
        if (enemyLvl == 3){
            scoreDisplay.setImage(new GreenfootImage("BOSS", 24, null, null));    
        } else{
            scoreDisplay.setImage(new GreenfootImage("Score: "+ score, 24, null, null));
        }
        
    }
    
    private void enemySpawnControll(){
        if (score < 4){
            enemyLvl = 1;
            maxEnemies = 1;
        } else if (score >= 4 && score < 8){
            enemyLvl = 1;
            maxEnemies = 2;
        } else if (score >= 8 && score < 14){
            enemyLvl = 1;
            maxEnemies = 3;
        } else if (score >= 14 && score < 16){
            enemyLvl = 2;
            maxEnemies = 1;
        } else if (score >= 16 && score < 20){
            enemyLvl = 2;
            maxEnemies = 2;
        } else if (score >= 20 && score < 26){
            enemyLvl = 2;
            maxEnemies = 3;
        } else if (score >= 26){
            enemyLvl = 3;
            maxEnemies = 1;
        }

        if (getObjects(Enemybody.class).size() == 0){
            while (getObjects(Enemybody.class).size() < maxEnemies){
                enemySpawnlocation = getSpawnLocation();
                Enemybody newEnemyBody = new Enemybody(enemyLvl);
                addObject(newEnemyBody, enemySpawnlocation[0],enemySpawnlocation[1]);
                Ghostturret newGhostturret = new Ghostturret(newEnemyBody);
                addObject(newGhostturret,enemySpawnlocation[0],enemySpawnlocation[1]);
                Enemyturret newEnemyTurret = new Enemyturret(newEnemyBody,newGhostturret);
                addObject(newEnemyTurret, enemySpawnlocation[0],enemySpawnlocation[1]);
            }
        }
    }
    public void prepare()
    {
        Playerbody playerbody = new Playerbody();
        addObject(playerbody, 200, 200);
        Playerturret playerturret = new Playerturret();
        addObject(playerturret, 200, 200);
        
        lvlmusic.setVolume(10);
        bossmusic.setVolume(10);
        lvlmusic.playLoop(); 
    }
    public int getTime(){
        return timeElapsed;
    }
    public int[] getSpawnLocation()
    {
        int xCoordinate;
        int yCoordinate;
        if (Greenfoot.getRandomNumber(2) == 1){
            yCoordinate = 800;
            xCoordinate = Greenfoot.getRandomNumber(900-200+1) + 200;
        } else {
            xCoordinate = 1000;
            yCoordinate = Greenfoot.getRandomNumber(700-200+1) + 200;
        }
            
        int[] returnArray = {xCoordinate, yCoordinate};
        return returnArray;
        
    }
    public void checkPlayerStatus(){
        if (getObjects(Playerbody.class).isEmpty()){
            addObject(gameOverDisplay,500,400);
            gameOverDisplay.setImage(new GreenfootImage("GAME OVER", 60, new Color(204,51,0), null));
            bossmusic.stop();
            lvlmusic.stop();
            
            GreenfootSound loss = new GreenfootSound("lost.mp3");
            loss.setVolume(15);
            loss.play();
            Greenfoot.stop();
        }
    }
    public void checkBossStatus(){
        if (bossHp == 0){
            addObject(gameOverDisplay,500,400);
            gameOverDisplay.setImage(new GreenfootImage("YOU WIN", 60, new Color(0,153,0), null));
            bossmusic.stop();
            lvlmusic.stop();
            
            GreenfootSound win = new GreenfootSound("win.mp3");
            win.setVolume(15);
            win.play();
            Greenfoot.stop();
        }
    }
    public void addScore(){
        this.score++;
    }
    public int getLvl(){
        return enemyLvl;
    }
    public void reduceBossHp(){
        bossHp = bossHp-1;
    }
    public int getBossHp(){
        return bossHp;
    }
    public void setBossHp(int hp){
        this.bossHp = hp;
    }
    
}
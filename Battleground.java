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
    // Create SimpleActor intances for multipurpose displays
    private Actor timerDisplay = new SimpleActor();
    private Actor scoreDisplay = new SimpleActor();
    private Actor gameOverDisplay = new SimpleActor();
    private Actor startDisplay = new SimpleActor();
    private Actor creditsDisplay = new SimpleActor();
    
    //set Variables
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
        // Create a new world with 1000x800 cells with a cell size of 1x1 pixels.
        super(1000, 800, 1);
        prepare();
        act();
        enemySpawnControl();
        updateTimerDisplay();
        addObject(timerDisplay,50,20);
        addObject(scoreDisplay,950,20);
        addObject(startDisplay, 500, 380);
        addObject(creditsDisplay, 500, 420);
        
        //Set Start screen
        startDisplay.setImage(new GreenfootImage("PixelTanks", 70, null, null));
        creditsDisplay.setImage(new GreenfootImage("By Oliver Ammann & Senthil Nagendran", 30, null, null));
        
        //set important game variables
        this.enemyLvl = 1;
        this.maxEnemies = 1;
        this.score = 0;
        this.bossHp = 4;
    }
    public void act(){
        timeCounter = (timeCounter+1)%55;
        //Update timer
        if (timeCounter == 0){
            timeElapsed++;
            updateTimerDisplay();
        }
        //check if Lvl = 3 (Bossfight) to check on Boss
        if (enemyLvl == 3){
            checkBossStatus();
            lvlmusic.stop();
            bossmusic.playLoop();
        } 
        //Remove Startscreen
        if (startDisplay.getWorld() != null){
            removeObject(startDisplay);
            removeObject(creditsDisplay);
        }
        updateScoreDisplay();
        enemySpawnControl();
        checkPlayerStatus();
    }
    //function to update timer display
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
    
    //function to control the spawnrate of enemy tanks
    private void enemySpawnControl(){
        //check what score player has and increase difficulty
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

        //check if all enemies have been killed and spawn next wave
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
    //prepare function to spawn player
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
    // public function to get time 
    public int getTime(){
        return timeElapsed;
    }
    //function to generate random spawnlocation for enemies
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
    //function to check if player is still alive or otherwise end game
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
    //check if Boss is still alive or otherwise end game 
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
    //public function to add playerscore + 1
    public void addScore(){
        this.score++;
    }
    //public function to get level + 1
    public int getLvl(){
        return enemyLvl;
    }
    //public function to reduce boss hp in boss stage
    public void reduceBossHp(){
        bossHp = bossHp-1;
    }
    //public function to get bosshp in boss stage
    public int getBossHp(){
        return bossHp;
    }
    //public function to set bosshp (Bugfix, just to be safe)
    public void setBossHp(int hp){
        this.bossHp = hp;
    }
    
}
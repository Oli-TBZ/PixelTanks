import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

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
    private Actor GameOverDisplay = new SimpleActor();
    private int score;
    private int timeElapsed;
    private int timeCounter; 
    
    private int maxEnemies;
    private int timecache;
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
        
        maxEnemies = 1;
        score = -1;
    }
    public void act(){
        timeCounter = (timeCounter+1)%55;
        if (timeCounter == 0){
            timeElapsed++;
            updateTimerDisplay();
        }
        updateScoreDisplay();
        enemySpawnControll();
        checkPlayerStatus();
    }
    private void updateTimerDisplay(){
        timerDisplay.setImage(new GreenfootImage("Timer: "+ timeElapsed, 24, null, null));
    }
    private void updateScoreDisplay(){
        if (getObjects(Enemybody.class).size() < maxEnemies){
            score++;   
        }
        scoreDisplay.setImage(new GreenfootImage("Score: "+ score, 24, null, null));
    }
    
    private void enemySpawnControll(){
        if (getObjects(Enemybody.class).size() < maxEnemies){
            int[] spawnlocation = getSpawnLocation();
            Enemybody newEnemyBody = new Enemybody();
            addObject(newEnemyBody, spawnlocation[0],spawnlocation[1]);
            Ghostturret newGhostturret = new Ghostturret(newEnemyBody);
            addObject(newGhostturret,spawnlocation[0],spawnlocation[1]);
            Enemyturret newEnemyTurret = new Enemyturret(newEnemyBody,newGhostturret);
            addObject(newEnemyTurret, spawnlocation[0],spawnlocation[1]);
        }
    }
    public void prepare()
    {
        Playerbody playerbody = new Playerbody();
        addObject(playerbody, 200, 200);
        Playerturret playerturret = new Playerturret();
        addObject(playerturret, 200, 200);
    }
    public int getTime(){
        return timeElapsed;
    }
    public int[] getSpawnLocation()
    {
        int xCoordinate;
        int yCoordinate;
        if (Greenfoot.getRandomNumber(2) == 1){
            yCoordinate = 790;
            xCoordinate = Greenfoot.getRandomNumber(900-200+1) + 200;
        } else {
            xCoordinate = 990;
            yCoordinate = Greenfoot.getRandomNumber(700-200+1) + 200;
        }
            
        int[] returnArray = {xCoordinate, yCoordinate};
        return returnArray;
        
    }
    public void checkPlayerStatus(){
        if (getObjects(Playerbody.class).isEmpty()){
            addObject(GameOverDisplay,500,400);
            GameOverDisplay.setImage(new GreenfootImage("GAME OVER", 40, null, null));
            Greenfoot.stop();
        }
    }
}
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Subclass of enemy class. The tankbody represent the main part of the enemy tank. Is responsible for most of the movement and logic.
 * 
 * @OliverAmmann & @SenthilNagendran
 * Stable Version 1.1
 */
public class Enemybody extends Enemy
{
    private int lvl;
    private int speed;
    private int playerRotation;
    private int rotationspeed;
    private int hp;
    
    private int drivingInterval;
    private int rotationDiff;
    private int timecache;
    
    private boolean firststart;
    private GreenfootImage image;

    private Actor healthbar = new SimpleActor();
    
    public Enemybody(int lvl){
        this.lvl = lvl;
        this.timecache = 0;  
        this.firststart = true;
        
        //set certain variables depending on active level
        if (lvl == 1){
            image = getImage();
            image.scale(image.getWidth()* 2, image.getHeight()* 2);
            image.setTransparency(0);
            setImage(image);
            
            this.speed = 1;
            this.rotationspeed = 1;
            this.drivingInterval = 3;
        } else if (lvl == 2){
            setImage("enemy_tankv2.png");
            image = getImage();
            image.scale(image.getWidth()* 2, image.getHeight()* 2);
            image.setTransparency(0);
            setImage(image);
            
            this.speed = 2;
            this.rotationspeed = 2;
            this.drivingInterval = 2;
        }
        else if (lvl == 3){
            setImage("enemy_tankv3.png");
            image = getImage();
            image.scale(image.getWidth()* 4, image.getHeight()* 4);
            image.setTransparency(0);
            setImage(image);
            
            this.speed = 2;
            this.rotationspeed = 3;
            this.drivingInterval = 2;    
        }
    }
    public void act()
    {
        //firststart-statement to do certain things only once
        if (firststart){
            turnTowards(getPlayerX(), getPlayerY());
            firststart = false;
            //Bugfix, if enemies spawn on top of each other, get new spawn location
            if (isTouching(Enemybody.class)){
                int [] replaceLocation = ((Battleground) getWorld()).getSpawnLocation();
                setLocation(replaceLocation[0],replaceLocation[1]);
            }
            
            getImage().setTransparency(255);
            healthbar.getImage().setTransparency(0);
            getWorld().addObject(healthbar, getX(), getY() - 30);
            
            //bugfix: set boss hp to 4 again to be safe
            ((Battleground) getWorld()).setBossHp(4);
        }
        if(lvl == 3){
            //create healthbar for boss level
            healthbar.getImage().setTransparency(255);
            updateHealth();
        }
        
        //function to let enemy tanks drive in intervals, turn towards player and drive again
        if (timecache + drivingInterval < getTime()){
            rotationDiff = playerRotation - getRotation();
            if (rotationDiff < -179){
                rotationDiff += 360;
            }
            if (rotationDiff > 180){
                rotationDiff -= 360;
            }
            if (rotationDiff > 0){
                turn(rotationspeed);
            }
            else{
                turn(-rotationspeed);
            }
            if (rotationDiff < rotationspeed && rotationDiff > -rotationspeed){
                timecache = getTime();
            }
        } else{
            move(speed);
        }
        
    }
    
    //set playerdirection function (used by turret to give this class info)
    public void setPlayerDirection(int rotation){
        playerRotation = rotation;
    }
    public int getLvl(){
        return this.lvl;
    }
    //function to update healthbar in boss level
    private void updateHealth(){
        healthbar.setLocation(getX(), getY() - 30);
        if (((Battleground) getWorld()).getBossHp() == 4){
            healthbar.setImage(new GreenfootImage("****", 35, new Color(0,153,51), null));
        } else if (((Battleground) getWorld()).getBossHp() == 3) {
            healthbar.setImage(new GreenfootImage("***", 35, new Color(255,153,51), null)); 
        } else if (((Battleground) getWorld()).getBossHp() == 2) {
            healthbar.setImage(new GreenfootImage("**", 35, new Color(204,102,0), null));
        } else if (((Battleground) getWorld()).getBossHp() == 1) {
            healthbar.setImage(new GreenfootImage("*", 35, new Color(204,51,0), null));
        } else if (((Battleground) getWorld()).getBossHp() == 0) {
            healthbar.setImage(new GreenfootImage("0-0", 35, new Color(255,51,0), null));
        }
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemybody here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemybody extends Enemy
{
    private int playerRotation;
    private int rotationspeed;
    
    private int drivingInterval;
    private int rotationDiff;
    private int timecache;
    
    private boolean firststart;
    
    
    public Enemybody(){
        GreenfootImage image = getImage();
        image.scale(image.getWidth()* 2, image.getHeight()* 2);
        setImage(image);
        
        rotationspeed = 1;
        drivingInterval = 3;
        timecache = 0;
        
        firststart = true;
    }
    /**
     * Act - do whatever the Enemybody wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (firststart){
            turnTowards(getPlayerX(), getPlayerY());
            firststart = false;
        }
        
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
            move(1);
        }
    }
    
    public void setPlayerDirection(int rotation){
        playerRotation = rotation;
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemyturret here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemyturret extends Enemy
{
    private Enemybody relatedTankBody;
    private Ghostturret relatedGhostturret;
    
    private int rotationspeed;
    
    private boolean firststart;
    
    private int cooldown;
    private int lastShotTime;
    
    private GreenfootSound shot = new GreenfootSound("shot.mp3");
    
    public Enemyturret(Enemybody tankBody, Ghostturret ghostturret){
        GreenfootImage image = getImage();
        image.scale(image.getWidth()* 2, image.getHeight()* 2);
        setImage(image);
        
        relatedTankBody = tankBody;
        relatedGhostturret = ghostturret;
        
        rotationspeed = 1;
        
        lastShotTime = 2;
        cooldown = 3;
        
        shot.setVolume(15);
        
        firststart = true;
    }
    
    /**
     * Act - do whatever the Enemyturret wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (relatedTankBody.getWorld() != null){
            if (firststart){
                turnTowards(getPlayerX(), getPlayerY());
                firststart = false;
                lastShotTime = getTime();
            }
            setLocation(relatedTankBody.getX(),relatedTankBody.getY());
            aim();
        } else {
            getWorld().removeObject(this);
        }
    }
    
    private void aim(){
        int targetRotation = relatedGhostturret.getPlayerDirection();
        relatedTankBody.setPlayerDirection(targetRotation);
        int rotationDiff = targetRotation - getRotation();
        
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
            setRotation(targetRotation);
            fire();
        }
    }
    
    private void fire(){
        if (lastShotTime + cooldown <= getTime()){
            Enemybullet enemybullet = new Enemybullet(relatedTankBody.getX(),relatedTankBody.getY(), getRotation());
            getWorld().addObject(enemybullet,relatedTankBody.getX(),relatedTankBody.getY());
            lastShotTime = getTime();
            shot.play();
        }
    }
}


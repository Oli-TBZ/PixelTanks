import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class turret here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Playerturret extends Player
{
    private int cooldown;
    private int lastShotTime;
    private GreenfootSound shot = new GreenfootSound("shot.mp3");
    
    public Playerturret(){
        GreenfootImage image = getImage();
        image.scale(image.getWidth()* 2, image.getHeight()* 2);
        setImage(image);
        
        shot.setVolume(15);
        
        lastShotTime = -2;
        cooldown = 2;
    }
    /**
     * Act - do whatever the turret wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
    if (!getWorld().getObjects(Playerbody.class).isEmpty()){
        setLocation(getPlayerX(), getPlayerY());
        turn();
        aim();
        if (Greenfoot.isKeyDown("space")){
                fire();
        }
    } else {
        getWorld().removeObject(this);
    }
    }
    public void fire(){
        if (lastShotTime + cooldown <= getTime()){
            Playerbullet playerbullet = new Playerbullet(getPlayerX(), getPlayerY(), getRotation());
            getWorld().addObject(playerbullet,getPlayerX(), getPlayerY());
            lastShotTime = getTime();
            
            shot.play();
        }
    }
}

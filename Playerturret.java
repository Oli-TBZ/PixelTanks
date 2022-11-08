import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Subclass of player class. The playerturret follows the X coordinates of the player-Tankbody to stay in position.
 * It can turn together with the body or turn seperatly. Also it checks the time to implement a cooldown to shoot.
 * 
 * @OliverAmmann & @SenthilNagendran
 * Stable Version 1.1
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
    public void act()
    {
    //check if playerbody is still alive or otherwise remove this class
    if (!getWorld().getObjects(Playerbody.class).isEmpty()){
        setLocation(getPlayerX(), getPlayerY());
        
        //turn function and aim function. So if the tank turns the turret turns too.
        turn();
        aim();
        if (Greenfoot.isKeyDown("space")){
                fire();
        }
    } else {
        getWorld().removeObject(this);
    }
    }
    //create new instance of playerbullet and fire
    public void fire(){
        if (lastShotTime + cooldown <= getTime()){
            Playerbullet playerbullet = new Playerbullet(getPlayerX(), getPlayerY(), getRotation());
            getWorld().addObject(playerbullet,getPlayerX(), getPlayerY());
            lastShotTime = getTime();
            
            shot.play();
        }
    }
}

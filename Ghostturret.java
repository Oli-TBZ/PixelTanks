import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Subclass of enemy class. The enemyturret always looks in the players direction / has the right rotation-angle to kill the player
 * this information gets passed on to te real turret for a delayed turn of turret
 * 
 * @OliverAmmann & @SenthilNagendran
 * Stable Version 1.1
 */
public class Ghostturret extends Enemy
{
    private Enemybody relatedTankbody;
    
    public Ghostturret(Enemybody tankbody){
        relatedTankbody = tankbody;
        getImage().setTransparency(0);
    }
    
    /**
     * Act - do whatever the ghostturret wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        //check if relatedTankbody is still alive or otherwise remove class
        if (relatedTankbody.getWorld() != null){
            setLocation(relatedTankbody.getX(), relatedTankbody.getY());
            turnTowards(getPlayerX(), getPlayerY());
        } else{
            getWorld().removeObject(this);
        }
    }
    
    //function to get direction of player for other classes
    public int getPlayerDirection(){
        return getRotation();
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class testEntity here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class testEntity extends PassableEntity
{  
    public testEntity()
    {
        presence = 10;
        damage = 10;
        damageRef = 100;
        mortal = true;
        accelRate = 0.2;
        decelRate = 0.1;
        maxSpeed = 1;
        reboundMomentum = 0.08;
        manualDecel = false;
    }
    public void actExtended()
    {
        super.actExtended();
        //if(canSee(PersistentStorage.getSyncedPlayer())) System.out.println("cansee");
        if(getDistanceBetween(PersistentStorage.getSyncedPlayer(), this) < 600)
        {
            if(canSee(PersistentStorage.getSyncedPlayer()))
            {
                pointTowards(PersistentStorage.getSyncedPlayer());
                accelerate();
            }
        }
    }
    public void addedToEtherworld(){};
}

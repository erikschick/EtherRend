import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.Random;

/**
 * Write a description of class ActorExtended here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class ActorExtended extends Actor
{
    double realYloc, realXloc;
    boolean remove = false;
    boolean pausable = true;
    
    KeyListener keylistener = PersistentStorage.keylistener;
    
    Random randgenerator = new Random();
    public final void act()
    {
        if(pausable && !PersistentStorage.gamePaused)
        {
            actExtended();
        }
        checkRemove();
    }
    
    public final double getRealX()
    {
        return realXloc;
    }
    
    public final double getRealY()
    {
        return realYloc;
    }
    
    public final void addedToWorld(World world)
    {
        realXloc = getX();
        realYloc = getY();
        addedToWorldExtended();
    }
    
    public abstract void addedToWorldExtended();

    public abstract void actExtended();
    
    /*public boolean mouseClickedActor(Actor actorTemp)
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        int actorXradius = actorTemp.getImage().getWidth() / 2;
        int actorYradius = actorTemp.getImage().getHeight() / 2;
        if(mouse != null)
        {
            if(mouse.getX() > actorTemp.getX() - actorXradius && mouse.getX() < actorTemp.getX() + actorXradius && mouse.getY() > actorTemp.getY() - actorYradius && mouse.getY() < actorTemp.getY() + actorYradius)
            {
                return true;
            }
        }
        return false;
    }*/
    
    public final boolean objectExists(Object objToLookFor)
    {
        if(objToLookFor != null)
        {
            if(getWorld().getObjects(null).contains(objToLookFor))
            {
                return true;
            }
        }
        return false;
    }
    
    public final void moveDouble(double xTempMove, double yTempMove)
    {
        setRealLocation(realXloc + xTempMove, realYloc + yTempMove);
    }
    
    public final void setRealLocation(double xTempLoc, double yTempLoc)
    {
        realXloc = xTempLoc;
        realYloc = yTempLoc;
        setLocation((int)realXloc, (int)realYloc);
    }
    
    public final void moveAtAngleDouble(int angle, double rqstDistance)
    {
        double xMove = (Math.cos(angle));
        double yMove = (Math.sin(angle));
        
        setRealLocation(realXloc + (xMove * rqstDistance), realYloc + (yMove * rqstDistance));
    }
    
    public final double getDistanceBetween(Actor object1, Actor object2)
    {
        if(object1 != null && object2 != null)
        {
            return abs(Math.hypot(object2.getX()-object1.getX(), object2.getY()-object1.getY()));
        }
        return 0;
    }
    
    public final double getAngleBetween(Actor object1, Actor object2)
    {
        if(object1 != null && object2 != null)
        {
            return (Math.toDegrees(Math.atan2(object1.getY()-object2.getY(),object1.getX()-object2.getX())));
        }
        return 0;
    }
    
    public final double abs(double tempNumber)
    {
        if(tempNumber >= 0)
        {
            return tempNumber;
        }
        if(tempNumber < 0)
        {
            return -tempNumber;
        }
        return 0;
    }
    
    public final double randNum(double maxReturnValue)
    {
        return (randgenerator.nextDouble() * maxReturnValue);
    }
    
    public final double randNumMean(double mean, double variance)
    {
        return mean + (randgenerator.nextGaussian() * variance);
    }
    
    public final void checkRemove()
    {
        if(remove)
        {
            getWorld().removeObject(this);
        }
    }
}

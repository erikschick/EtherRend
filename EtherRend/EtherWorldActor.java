import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;
/**
 * Write a description of class EtherWorldActor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class EtherWorldActor extends ActorExtended
{
    EtherWorld etherworld;
    double coordX = 0; double coordY = 0;
    boolean cameraAffected = true;
    public boolean passable = true;
    public void actExtended()
    {
        checkCamera();
        //etherWorldActorAct();
    }
    
    //public abstract void etherWorldActorAct();
    
    public void checkCamera()
    {
        if(cameraAffected)
        {
            setRealLocation(coordX + etherworld.cameraX, coordY + etherworld.cameraY);
        }
    }
    
    public final void addedToWorldExtended()
    {
        etherworld = (EtherWorld) getWorld();
        coordX = getRealX();
        coordY = getRealY();
        addedToEtherworld();
        checkCamera();
    }
    
    public void setCoords(double x, double y)
    {
        coordX = x;
        coordY = y;
    }
    
    public void vectorMove(vector v)
    {
        /*if(this.equals(PersistentStorage.player))
        {
            System.out.println("x: " + v.getXmomentum());
            System.out.println("y: " + v.getYmomentum());
            System.out.println("a: " + v.getAngle());
        }*/
        
        setCoords(coordX + v.getXmomentum(), coordY + v.getYmomentum());
        
        //moveAtAngleDouble(Math.toRadians(v.getAngle()), v.getMomentum());
        
        //moveAtAngleDouble((v.getAngle()), v.getMomentum());
    }
    
    public static List<worldObject> getTilesAt(double x, double y)
    {
        List<worldObject> actorsConfirmed = new ArrayList();
        if(PersistentStorage.etherworld != null)
        {
            EtherWorld e = PersistentStorage.etherworld;
            List<worldObject> objectsInWorld = e.getObjects(worldObject.class);
            for(worldObject a : objectsInWorld)
            {
                int yOffset = a.getImage().getHeight() / 2;
                int xOffset = a.getImage().getWidth() / 2;
                if(a.coordX >= x - xOffset && a.coordX <= x + xOffset && a.coordY >= y - yOffset && a.coordY <= y + yOffset)
                {
                    actorsConfirmed.add(a);
                }
            }
        }
        return actorsConfirmed;
    }
    
    public abstract void addedToEtherworld();
}

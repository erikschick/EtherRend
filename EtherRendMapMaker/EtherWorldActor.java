import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

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
    
    public final void actExtended()
    {
        checkCamera();
        etherWorldActorAct();
    }
    
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
        realXloc = getX();
        realYloc = getY();
        coordX = getRealX();
        coordY = getRealY();
        addedToEtherworld();
    }
    
    public void etherWorldActorAct()
    {
    }
    
    public void addedToEtherworld()
    {
        
    }
}

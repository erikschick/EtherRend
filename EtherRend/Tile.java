import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Tile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Tile extends worldObject
{
    public double terrainSpeedMultiplier = 1;
    
    //public void etherWorldActorAct()
    public void actExtended()
    {
        super.actExtended();
        tileAct();
    }
    
    public abstract void tileAct();
    
    public void addedToEtherworld()
    {
        
    }
    
    public String getObjectType()
    {
        return "tile";
    }
}

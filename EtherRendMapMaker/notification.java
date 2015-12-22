import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class notification here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class notification extends ActorExtended
{
    public void actExtended() 
    {
        NOTFCNact();
    }   
    
    public void addedToWorldExtended()
    {
        NOTFCNinWorld();
    }
    
    public void NOTFCNinWorld()
    {
    }
    
    public abstract void NOTFCNact();
}

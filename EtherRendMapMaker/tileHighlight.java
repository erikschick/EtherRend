import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class tileHighlight here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class tileHighlight extends EtherWorldActor
{
    EtherWorldActor master = null;
    
    public tileHighlight(EtherWorldActor master)
    {
        this.master = master;
    }
    
    public void etherWorldActorAct()
    {
        coordX = master.coordX;
        coordY = master.coordY;
        if(master != PersistentStorage.activeObject)
        {
            remove = true;
        }
    }
}

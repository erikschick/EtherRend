import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class NonpassableTile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class NonpassableTile extends Tile
{
    //passable = false;
    /*@Override
    public boolean passable()
    {
        return false;
    }*/
    
    public void addedToEtherworld()
    {
        super.addedToEtherworld();
        passable = false;
    }
}

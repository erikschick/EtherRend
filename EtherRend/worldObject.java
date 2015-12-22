import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

public abstract class worldObject extends EtherWorldActor
{
    boolean partOfWorld = true;
    public abstract String getObjectType();
    
    public void setData(List<String> data)
    {
        //overwrite this method to save some of the data that is being passed
    }
    
    /*public boolean passable()
    {
        return true;
    }*/
}

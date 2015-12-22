import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class basicShot here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class basicShot extends EtherWorldActor
{
    double xMomentum = 0;
    double yMomentum = 0;
    int decay = 100;
    int hitBoxRadius = 2;
    
    public basicShot(String direction, int charge)
    {
        decay = charge * 4;
        xMomentum = 1;
    }
    
    //public void etherWorldActorAct()
    public void actExtended()
    {
        super.actExtended();
        checkContact();
        move();
        decay();
    }
    
    public void addedToEtherworld()
    {
    }
    
    public void checkContact()
    {
        List<EtherWorldActor> objCols = getContactRadius(hitBoxRadius, EtherWorldActor.class);
        for(int i = 0; i < objCols.size(); i++)
        {
            if(objCols.get(i) instanceof Entity && !(objCols.get(i) instanceof ENTITY_player))
            {
                Entity obj = (Entity)objCols.get(i);
                if(obj.mortal)
                {
                    obj.attackEntity(new Attack("basicShot", 1, 30));
                    remove = true;
                }
            }
            //if(!objCols.get(i).passable())
            if(!objCols.get(i).passable)
            {
                remove = true;
            }
        }
    }
    
    public void decay()
    {
        decay--;
        if(decay < 0)
        {
            remove = true;
        }
    }
    
    public void move()
    {
        coordX = coordX + xMomentum;
    }
}

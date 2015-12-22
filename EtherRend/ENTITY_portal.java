import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.awt.Color;
public class ENTITY_portal extends PassableEntity
{
    String destWorld;
    int destX;
    int destY;
    int r; int g; int b;
    int teleThreshold = 150;
    
    int teleporting = 0;
    
    //HitBox hitbox = new HitBox(this, 30, 30);
    
    ParticleEmitter pem = new ParticleEmitter(15, 10, 30, Color.WHITE, 255, 2.5, -1, 1, -1, 1);
    
    boolean bursted = false;
    
    //public void entityAct()
    public void actExtended()
    {
        super.actExtended();
        checkPlayerStanding();
    }
    
    public void checkPlayerStanding()
    {
        if(getDistanceBetween(this, PersistentStorage.player) < 20)
        {
            teleporting++;
            pem.activate();
        }
        else if(teleporting > 0)
        {
            pem.deactivate();
            teleporting = Math.min(teleporting - 10, 0);
        }
        if(teleporting > teleThreshold - 20 && !bursted)
        {
            bursted = true;
            pem.burstOutward(20, 1, 2.5);
        }
        if(teleporting > teleThreshold)
        {
            teleport();
            pem.deactivate();
        }
    }
    
    public void moveHitBox()
    {
        //hitbox.setRealLocation(getRealX(), getRealY());
    }
    
    public void removeAction()
    {
        //hitbox.remove = true;
    }
    
    public void teleport()
    {
        if(!destWorld.startsWith("worlds/"))
        {
            destWorld = "worlds/" + destWorld;
        }
        Greenfoot.setWorld(new EtherWorld(destWorld, true, destX, destY));
    }
    
    public void setData(List<String> data)
    {
        destWorld = data.get(0);
        teleThreshold = Integer.parseInt(data.get(1));
        destX = Integer.parseInt(data.get(2));
        destY = Integer.parseInt(data.get(3));
        r = Integer.parseInt(data.get(4));
        g = Integer.parseInt(data.get(5));
        b = Integer.parseInt(data.get(6));
        GreenfootImage img = new GreenfootImage(50, 50);
        img.setColor(new Color(r, g, b));
        img.fill();
        setImage(ImageWorker.applyAlphaMap(img, new GreenfootImage("ENTITY_portal.png")));
        
        pem.setColor(new Color(r, g, b));
        pem.deactivate();
    }
    
    public void addedToEtherworld()
    {
        mortal = false;
        if(partOfWorld)
        {
            getWorld().addObject(pem, (int)coordX, (int)coordY);
        }
    }
    
    public int getDamageRefresh()
    {
        return 0;
    }
}

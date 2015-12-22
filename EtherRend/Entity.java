import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * Write a description of class Entity here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Entity extends worldObject
{
    //Variables to overwrite for new entities
    double presence = 10;
    double damage = 10;
    int damageRef = 0;
    boolean mortal = true;
    double accelRate = 0.1;
    double decelRate = 0.1;
    double maxSpeed = 0.1;
    double reboundMomentum = 0.08;
    boolean manualDecel = false;
    /*example:
    * presence = 10;
      damage = 10;
      damageRef = 100;
      mortal = true;
      accelRate = 0.2;
      decelRate = 0.1;
      maxSpeed = 2;
      reboundMomentum = 0.08;
      manualDecel = false;
     */
    ///////////////////////////////////////////
    
    static int sightAccuracy = 4;
    
    double momentumMax = 0;
    
    List<Attack> activeAttacks = new ArrayList();
    static List<Class> nonpassableClasses = Arrays.asList(new Class[] {NonpassableTile.class, NonpassableEntity.class});
    //double xMomentum = 0; double yMomentum = 0;
    vector motion = new vector(0, 0);
    
    String lastDirection = "down";
    
    //public void etherWorldActorAct() 
    public void actExtended()
    {
        super.actExtended();
        refreshActiveAttacks();
        momentumMax = maxSpeed * getTerrainMultiplier();
        entityMove();
        //damageTimer();
        //entityAct();
        checkDestroy();
    }    
    
    private void checkDestroy()
    {
        if(mortal && presence <= 0)
        {
            destroyResponse();
            remove = true;
        }
    }
    
    public void destroyResponse(){}
    
    public void accelerate()
    {
        accelerate(accelRate);
    }
    public void accelerate(double amount)
    {
        double m = motion.getMomentum() + amount;
        if(m > momentumMax) motion.setMomentum(momentumMax);
        else motion.setMomentum(m);
    }
    
    public void pointTowards(EtherWorldActor a) { if(a != null) pointTowards(a.getRealX(), a.getRealY()); }
    public void pointTowards(coord xy) { if(xy != null) pointTowards(xy.x, xy.y);}
    public void pointTowards(double x2, double y2)
    {
        motion.setAngle(getAngleBetween(getRealX(), getRealY(), x2, y2));
    }
    
    public String getDirection()
    {
        if(motion.getXmomentum() > 0 && Math.abs(motion.getXmomentum()) > Math.abs(motion.getYmomentum()))
        {
            lastDirection = "right";
            return "right";
        }
        if(motion.getXmomentum() < 0 && Math.abs(motion.getXmomentum()) > Math.abs(motion.getYmomentum()))
        {
            lastDirection = "left";
            return "left";
        }
        if(motion.getYmomentum() > 0 && Math.abs(motion.getXmomentum()) < Math.abs(motion.getYmomentum()))
        {
            lastDirection = "down";
            return "down";
        }
        if(motion.getYmomentum() < 0 && Math.abs(motion.getXmomentum()) < Math.abs(motion.getYmomentum()))
        {
            lastDirection = "up";
            return "up";
        }
        return lastDirection;
    }
    
    public boolean canSee(EtherWorldActor a) { return canSee(a.coordX, a.coordY); }
    public boolean canSee(coord xy) { return canSee(xy.x, xy.y);}
    public boolean canSee(double x2, double y2)
    {
        double y1 = coordY;
        double x1 = coordX;
        double yDiff = y2 - y1;
        double xDiff = x2 - x1; if(xDiff == 0) xDiff = xDiff + 0.000001; //assures it will not be 0
        double m = yDiff/xDiff;
        //double b = y2 - (m * x2);
        //System.out.println("The function is: y = " + m + "x + " + b);
        
        //y - y1 = ((y2 - y1)/(x2 - x1)) * (x - x1)
        //y = (((y2 - y1)/(x2 - x1)) * (x - x1)) + y1
        
        /*
         * ALSO ITERATE Y, SINCE x CAN HAVE MULTIPLE Y VALUES
         */
        
        for(int x = Math.min((int)x2, (int)x1); x < Math.max((int)x2, (int)x1); x = x + sightAccuracy)
        {
            //int y = (int)((m * x) + b);
            
            int y = (int) ((m * (x - x1)) + y1);
            //int y = (int) ((((y2 - y1)/(x2 - x1)) * (x - x1)) + y1);
            
            //System.out.println("x: " + x + " y: " + y);
            List<worldObject> atTile = getTilesAt(x, y);
            EtherWorld e = PersistentStorage.etherworld;
            if(!atTile.isEmpty())
            {
                for(worldObject cur : atTile)
                //for(int i = 0; i < atTile.size(); i++)
                {
                    //if(!atTile.get(i).passable)
                    if(!cur.passable)
                    {
                        return false;
                    }
                }
            }
        }
        
        if(m >= -1 && m <= 1)
        {
            for(int y = Math.min((int)y2, (int)y1); y < Math.max((int)y2, (int)x1); y = y + 1)
            {
                //int x = (int) (((y - y1) / m) + x1);
                int x = (int)x1;
                List<worldObject> atTile = getTilesAt(x, y);
                EtherWorld e = PersistentStorage.etherworld;
                if(!atTile.isEmpty())
                {
                    for(worldObject cur : atTile)
                    //for(int i = 0; i < atTile.size(); i++)
                    {
                        //if(!atTile.get(i).passable)
                        if(!cur.passable)
                        {
                            return false;
                        }
                    }
                }
            }
        }
        /*for(int y = Math.min((int)y2, (int)y1); y < Math.max((int)y2, (int)x1); y = y + sightAccuracy)
        {
            int x = (int) (((y - y1) / m) + x1);
            List<worldObject> atTile = getTilesAt(x, y);
            EtherWorld e = PersistentStorage.etherworld;
            if(!atTile.isEmpty())
            {
                for(worldObject cur : atTile)
                //for(int i = 0; i < atTile.size(); i++)
                {
                    //if(!atTile.get(i).passable)
                    if(!cur.passable)
                    {
                        return false;
                    }
                }
            }
        }*/
        
        return true;
    }
    
    public double getTerrainMultiplier()
    {
        List tilesBelow = getObjectsAtOffset(0, 0, Tile.class);
        double largestMultiplier = 0;
        for(int i = 0; i < tilesBelow.size(); i++)
        {
            Tile tilebelow = (Tile)tilesBelow.get(i);
            if(tilebelow.terrainSpeedMultiplier > largestMultiplier)
            {
                largestMultiplier = tilebelow.terrainSpeedMultiplier;
            }
        }
        if(tilesBelow.size() == 0)
        {
            return 1;
        }
        return largestMultiplier;
    }
    
    //public abstract int getDamageRefresh();
    
    public void damageReaction()
    {
        //Overwrite this to have it react to damage;
    }
    
    public String getObjectType()
    {
        return "entity";
    }
    
    public void setPresence(double presence)
    {
        this.presence = presence;
    }
    
    public void checkCollision()
    {
        int searchX = (int)(getImage().getWidth() / 2);
        int searchY = (int)(getImage().getHeight() / 2);
        if(motion.getXmomentum() > 0)
        {
            for(int i = 0; i < nonpassableClasses.size(); i++)
            {
                if(getObjectsAtOffset(searchX, searchY - 1, (Class)nonpassableClasses.get(i)).size() > 0 || getObjectsAtOffset(searchX, 0, (Class)nonpassableClasses.get(i)).size() > 0 || getObjectsAtOffset(searchX, -searchY + 1, (Class)nonpassableClasses.get(i)).size() > 0)
                {
                    motion.setXmomentum(-reboundMomentum);
                }
            }
        }
        if(motion.getXmomentum() < 0)
        {
            for(int i = 0; i < nonpassableClasses.size(); i++)
            {
                if(getObjectsAtOffset(-searchX, searchY - 1, (Class)nonpassableClasses.get(i)).size() > 0 || getObjectsAtOffset(-searchX, 0, (Class)nonpassableClasses.get(i)).size() > 0 || getObjectsAtOffset(-searchX, -searchY + 1, (Class)nonpassableClasses.get(i)).size() > 0)
                {
                    motion.setXmomentum(reboundMomentum);
                }
            }
        }
        if(motion.getYmomentum() > 0)
        {
            for(int i = 0; i < nonpassableClasses.size(); i++)
            {
                if(getObjectsAtOffset(searchX - 1, searchY, (Class)nonpassableClasses.get(i)).size() > 0 || getObjectsAtOffset(0, searchY, (Class)nonpassableClasses.get(i)).size() > 0 || getObjectsAtOffset(-searchY + 1, searchY, (Class)nonpassableClasses.get(i)).size() > 0)
                {
                    motion.setYmomentum(-reboundMomentum);
                }
            }
        }
        if(motion.getYmomentum() < 0)
        {
            for(int i = 0; i < nonpassableClasses.size(); i++)
            {
                if(getObjectsAtOffset(searchX - 1, -searchY, (Class)nonpassableClasses.get(i)).size() > 0 || getObjectsAtOffset(0, -searchY, (Class)nonpassableClasses.get(i)).size() > 0 || getObjectsAtOffset(-searchY + 1, -searchY, (Class)nonpassableClasses.get(i)).size() > 0)
                {
                    motion.setYmomentum(reboundMomentum);
                }
            }
        }
    }
    
    public void entityMove()
    {
        if(motion.getMomentum() != 0)
        {
            if(motion.getMomentum() > momentumMax) motion.setMomentum(momentumMax);
            //if(motion.getMomentum() < decelRate) motion.setMomentum(0);
            
            if(!manualDecel)
            {
                if(Math.abs(motion.getXmomentum()) < decelRate) motion.setXmomentum(0);
                if(Math.abs(motion.getYmomentum()) < decelRate) motion.setYmomentum(0);
                if(motion.getXmomentum() > 0) motion.setXmomentum(motion.getXmomentum() - decelRate);
                if(motion.getXmomentum() < 0) motion.setXmomentum(motion.getXmomentum() + decelRate);
                if(motion.getYmomentum() > 0) motion.setYmomentum(motion.getYmomentum() - decelRate);
                if(motion.getYmomentum() < 0) motion.setYmomentum(motion.getYmomentum() + decelRate);
            }
            checkCollision();
            
            vectorMove(motion);
        }
    }
    
    public void refreshActiveAttacks()
    {
        for(int i = 0; i < activeAttacks.size(); i++)
        {
            activeAttacks.get(i).cooldown();
            if(activeAttacks.get(i).cooldown <= 0)
            {
                activeAttacks.remove(i);
            }
        }
    }
    
    public void addAttack(Attack attack)
    {
        activeAttacks.add(attack);
    }
    
    public void attackEntity(Attack attack)
    {
        boolean attackValid = true;
        if(activeAttacks.size() == 0)
        {
            presence = presence - attack.getDamage();
            damageReaction();
            activeAttacks.add(attack);
        }
        else{
            for(int i = 0; i < activeAttacks.size(); i++)
            {
                if(Attack.sameAttack(activeAttacks.get(i), attack))
                {
                    attackValid = false;
                    break;
                }
            }
            if(attackValid)
            {
                activeAttacks.add(attack);
                presence = presence - attack.getDamage();
                damageReaction();
            }
        }
    }
    
    public void addedToEtherworld()
    {
        
    }
}

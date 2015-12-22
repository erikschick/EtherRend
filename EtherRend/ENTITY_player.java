import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.*;
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ENTITY_player extends Entity
{
    //double mapCoordX = 0; double mapCoordY = 0;
    //double offsetX = 0; double offsetY = 0;
    //double xMomentum = 0; double yMomentum = 0;
    /*double accelRate = 0.2; double decelRate = 0.1;
    double maxSpeed = 2;
    double momentumMax = 2;*/
    //double stoppedMomentum = 0.08;
    
    //int damageRefreshTime = 100;
    
    List nonpassableClasses = new ArrayList();//Arrays.asList(NonpassableTile.class, NonpassableEntity.class);
    
    String KEY_LEFT = "a";
    String KEY_RIGHT = "d";
    String KEY_UP = "w";
    String KEY_DOWN = "s";
    
    ParticleEmitter pem = new ParticleEmitter(6, 30, 45, Color.WHITE, 200, 3.5, -0.3, 0.3, -0.3, 0.3);
    
    //String lastDirection = "down";
    
    boolean attackstarted = false;
    int charge = 0;
    
    /*double presence = 10;
    double damage = 10;
    int damageRef = 100;
    boolean mortal = true;
    double accelRate = 0.2; double decelRate = 0.1;
    double maxSpeed = 2;
    double momentumMax = 2;
    double reboundMomentum = 0.08;
    boolean manualDecel = true;*/
    
    List<Entity> enemiesNearby = new ArrayList();
    
    public ENTITY_player()
    {
        presence = 10;
        damage = 10;
        damageRef = 100;
        mortal = true;
        accelRate = 0.2;
        decelRate = 0.1;
        maxSpeed = 2;
        reboundMomentum = 0.08;
        manualDecel = false;
    }
    
    public void actExtended()
    {
        super.actExtended();
        if(!PersistentStorage.getSyncedPlayer().equals(this))
        {
            remove = true;
        }
        checkKeys();
        checkMove();
        refreshEnemyList();
        //setRealLocation(400 - offsetX, 300 - offsetY);
        //etherworld.setCamera(mapCoordX - offsetX, mapCoordY - offsetY);
        //etherworld.setCamera(coordX - offsetX, coordY - offsetY);
        //setCoords(coordX -1, coordY -1);
        //moveAtAngleDouble(Math.toRadians(-87), 1);
        
        //pem.coordX = -mapCoordX;
        //pem.coordY = -mapCoordY;
    }    
    
    public void addedToEtherworld()
    {
        if(!actorInWorld(PersistentStorage.playerTargetSelector) && PersistentStorage.playerTargetSelector != null)
        {
            getWorld().addObject(PersistentStorage.playerTargetSelector, 0, 0);
        }
        nonpassableClasses.add(NonpassableTile.class);
        nonpassableClasses.add(NonpassableEntity.class);
        cameraAffected = true;
        
        PersistentStorage.syncPlayer(this);
        
        //setImage(Particle.colorParticle(40, Color.WHITE));
        //getWorld().addObject(pem, (int)-mapCoordX, (int)-mapCoordY);
    }
    
    public void checkKeys()
    {
        if(keymonitor.isKeyJustPressed("numpad5") && !attackstarted)
        {
            attackstarted = true;
        }
        if(attackstarted)
        {
            charge++;
            if(!keymonitor.isKeyDown("numpad5"))
            {
                attackstarted = false;
                attack(charge);
                charge = 0;
            }
        }
        
        if(keymonitor.isKeyJustPressed("tab"))
        //if
        {
            PersistentStorage.playerTarget = getNextEnemy();//getNearestEnemy();
        }
        
        /*if(keymonitor.isKeyJustPressed("u"))
        {
            PathFind pf = new PathFind();
            List<coord> path = pf.FindPath(50, -550, 0, -400);
            for(int i = 0; i < path.size(); i++)
            {
                etherworld.addObject(new markertmp(), (int)(path.get(i).getX()), (int)(path.get(i).getY()));
            }
        }*/
    }
    
    public Entity getNextEnemy()
    {
        //Possibly sort list by distance?
        if(enemiesNearby.size() != 0)
        {
            int index = enemiesNearby.indexOf(PersistentStorage.playerTarget);
            if(index + 1 < enemiesNearby.size())
            {
                return enemiesNearby.get(index + 1);
            }
            else
            {
                return enemiesNearby.get(0);
            }
        }
        return null;
    }
    
    public void refreshEnemyList()
    {
        List<Entity> nearby = getObjectsInRange(380, Entity.class);
        for(int i = 0; i < nearby.size(); i++)
        {
            Entity cur = nearby.get(i);
            if(!enemiesNearby.contains(cur))
            {
                enemiesNearby.add(cur);
            }
        }
        for(int i = 0; i < enemiesNearby.size(); i++)
        {
            Entity cur = enemiesNearby.get(i);
            if(!nearby.contains(cur))
            {
                enemiesNearby.remove(cur);
            }
        }
    }
    
    public void getNearestEnemy()
    {
        List<Entity> nearby = getObjectsInRange(250, Entity.class);
        Entity nearest = nearby.get(0);
        double nearDist = 0;
        for(int i = 0; i < nearby.size(); i++)
        {
            Entity cur = nearby.get(i);
            if(getDistanceBetween(this, cur) > nearDist)
            {
                nearDist = getDistanceBetween(this, cur);
            }
        }
    }
    
    public void checkMove()
    {
        if(keymonitor.isKeyDown(KEY_LEFT))
        {
            motion.setXmomentum(motion.getXmomentum() - accelRate);
        }
        if(keymonitor.isKeyDown(KEY_RIGHT))
        {
            motion.setXmomentum(motion.getXmomentum() + accelRate);
        }
        if(keymonitor.isKeyDown(KEY_DOWN))
        {
            motion.setYmomentum(motion.getYmomentum() + accelRate);
        }
        if(keymonitor.isKeyDown(KEY_UP))
        {
            motion.setYmomentum(motion.getYmomentum() - accelRate);
        }
        
        if(!keymonitor.isKeyDown(KEY_RIGHT) && !keymonitor.isKeyDown(KEY_LEFT))
        {
            if(Math.abs(motion.getXmomentum()) < decelRate) motion.setXmomentum(0);
            //if(motion.getXmomentum() > 0) motion.setXmomentum(motion.getXmomentum() - decelRate);
            //else if(motion.getXmomentum() < 0) motion.setXmomentum(motion.getXmomentum() + decelRate);
        }
        
        if(!keymonitor.isKeyDown(KEY_UP) && !keymonitor.isKeyDown(KEY_DOWN))
        {
            if(Math.abs(motion.getYmomentum()) < decelRate) motion.setYmomentum(0);
            //if(motion.getYmomentum() > 0) motion.setYmomentum(motion.getYmomentum() - decelRate);
            //else if(motion.getYmomentum() < 0) motion.setYmomentum(motion.getYmomentum() + decelRate);
        }
        
        /*
        momentumMax = maxSpeed * getTerrainMultiplier();
        
        if(xMomentum > momentumMax)
        {
            xMomentum = momentumMax;
        }
        if(xMomentum < -momentumMax)
        {
            xMomentum = -momentumMax;
        }
        if(yMomentum > momentumMax)
        {
            yMomentum = momentumMax;
        }
        if(yMomentum < -momentumMax)
        {
            yMomentum = -momentumMax;
        }
        
        checkCollision();
        
        if(xMomentum != 0 && yMomentum != 0)
        {
            {
                while(Math.abs(Math.hypot(xMomentum, yMomentum)) > momentumMax)
                {
                    xMomentum = Math.copySign(Math.abs(xMomentum) * 0.95, xMomentum);
                    yMomentum = Math.copySign(Math.abs(yMomentum) * 0.95, yMomentum);
                }
            }
        }
        
        mapCoordX = mapCoordX - xMomentum;
        mapCoordY = mapCoordY - yMomentum;*/
    }
    
    public void attack(int charge)
    {
        //PersistentStorage.etherworld.addObject(new basicShot(getDirection(), charge), (int)-mapCoordX, (int)-mapCoordY);
        PersistentStorage.etherworld.addObject(new basicShot(getDirection(), charge), (int)coordX, (int)coordY);
    }
    
    /*public int getDamageRefresh()
    {
        return damageRefreshTime;
    }*/
    
    public void setPresence(double presence)
    {
        PersistentStorage.playerPresence = presence;
    }
    
    /*public void attackEntity(double amount) //This is just a forward
    {
        PersistentStorage.damagePlayer(amount);
    }*/
}

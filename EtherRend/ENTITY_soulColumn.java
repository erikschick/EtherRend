import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
/**
 * Write a description of class ENTITY_soulColumn here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ENTITY_soulColumn extends NonpassableEntity
{
    ParticleEmitter soul = new ParticleEmitter(9, 20, 40, Color.WHITE, 300, 2.5, -0.9, 0.1, -0.4, 0.2, "PARTICLE_gradientStar.png");
    double soulAngle = 0;
    double soulAngleIncreaseRate = 0.2;
    double soulAngleIncreaseRateDesired = 0.2;
    double soulDistance = 25;
    double soulDistanceDesired = 25;
    
    double soulFrequency = 9;
    double soulDecayRate = 2.5;
    
    int maxPresence = 10;
    
    int counter_soulDistance = 5;
    int playerDamageRadius = 60;
    double damageAmount = 1;
    
    final double MIN_SOUL_WEIGHT_X = 0.01;
    final double MAX_SOUL_WEIGHT_X = 0.2;
    final double MIN_SOUL_WEIGHT_Y = 0.01;
    final double MAX_SOUL_WEIGHT_Y = 0.2;
    
    //public void entityAct() 
    
    public ENTITY_soulColumn()
    {
        setPresence(maxPresence);
    }
    
    public void actExtended()
    {
        super.actExtended();
        runCounters();
        checkPlayer();
        spinSoulAngle();
        runSoulParticle();
    }
    
    public void runCounters()
    {
        counter_soulDistance++;
        soulFrequency = (25 - ((presence / maxPresence) * 16));
        soul.setFrequency((int)soulFrequency);
        soulDecayRate = (2.5 + ((maxPresence - presence) / maxPresence) * 4.5);
        soul.setDecayRate(soulDecayRate);
    }
    
    public void checkPlayer()
    {
        /*if(getDistanceBetween(this, PersistentStorage.player) < playerDamageRadius)
        {
            PersistentStorage.damagePlayer(damageAmount);
        }*/
    }
    
    public void spinSoulAngle()
    {
        //soulAngleIncreaseRate = (soulAngleIncreaseRate * 0.15) + (randNum(soulAngleIncreaseRateDesired * 2) * 0.85);
        soulAngleIncreaseRate = (soulAngleIncreaseRate * 0.45) + (eMath.randNumMean(soulAngleIncreaseRateDesired / 4, soulAngleIncreaseRateDesired * 2) * 0.55);
        if(counter_soulDistance >= 10)
        {
            counter_soulDistance = 0;
            soulDistance = (soulDistance * 0.9) + (eMath.randNumMean(soulDistanceDesired, soulDistanceDesired / 2) * 0.1);
            if(soulDistance < 5) soulDistance = 32;
        }
        soulAngle = soulAngle + soulAngleIncreaseRate;
        if(soulAngle >= 360)
        {
            soulAngle = soulAngle - 360;
        }

        if(eMath.getQuadrant(soulAngle) == 1){
            soul.setWeight(-MIN_SOUL_WEIGHT_X, -MAX_SOUL_WEIGHT_X, MIN_SOUL_WEIGHT_Y, MAX_SOUL_WEIGHT_Y);}
        if(eMath.getQuadrant(soulAngle) == 2){
            soul.setWeight(MIN_SOUL_WEIGHT_X, MAX_SOUL_WEIGHT_X, MIN_SOUL_WEIGHT_Y, MAX_SOUL_WEIGHT_Y);}
        if(eMath.getQuadrant(soulAngle) == 3){
            soul.setWeight(MIN_SOUL_WEIGHT_X, MAX_SOUL_WEIGHT_X, -MIN_SOUL_WEIGHT_Y, -MAX_SOUL_WEIGHT_Y);}
        if(eMath.getQuadrant(soulAngle) == 4){
            soul.setWeight(-MIN_SOUL_WEIGHT_X, -MAX_SOUL_WEIGHT_X, -MIN_SOUL_WEIGHT_Y, -MAX_SOUL_WEIGHT_Y);}
    }
    
    public void runSoulParticle()
    {
        double xMult = (Math.cos(soulAngle));
        double yMult = (Math.sin(soulAngle));
        if(soulAngle > 90 && soulAngle <= 180)
        {
            xMult = -xMult;
        }
        if(soulAngle > 180 && soulAngle <= 270)
        {
            xMult = -xMult;
            yMult = -yMult;
        }
        if(soulAngle > 270 && soulAngle <= 360)
        {
            yMult = -yMult;
        }
        
        soul.coordX = coordX + (xMult * soulDistance);
        soul.coordY = coordY + (yMult * soulDistance);
    }
    
    public void destroyResponse()
    {
        soul.remove = true;
    }
    
    public void damageReaction()
    {
        soul.burstOutward((int)(10 + (0.4 * (maxPresence - presence))), eMath.randBetween(0.8, 1) * 0.1 * (maxPresence - presence + 1), eMath.randBetween(1.6, 2.3) * 0.1 * (maxPresence - presence + 1), 8 - (0.5 * (maxPresence - presence + 1)), 255 + ((maxPresence - presence) * 10));
    }
    
    public void addedToEtherworld()
    {
        setPresence(maxPresence);
        etherworld.addObject(soul, (int)coordX, (int)coordY);
    }
    
    public int getDamageRefresh()
    {
        return 10;
    }
}

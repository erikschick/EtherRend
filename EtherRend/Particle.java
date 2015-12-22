import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
/**
 * Based off of the work of Spilli
 * http://www.greenfoot.org/scenarios/5705
 */
public class Particle extends EtherWorldActor
{
    double decay = 300;
    double decayRate = 1.5;
    double weightX = 0;
    double weightY = 0;
    int size = 10;
    Color col = Color.WHITE;
    String imageFile = "PARTICLE_gradientStar.png";
    
    public Particle()
    {
        setImage(new GreenfootImage(1, 1));
    }
    
    public Particle(int size, Color col, double lifeSpan, double decayRate)
    {
        this(size, col, lifeSpan, decayRate, 0, 0, "PARTICLE_gradientStar.png");
    }
    
    public Particle(int size, Color col, double lifeSpan, double decayRate, double weightX, double weightY, String imageFile)
    {
        this.decay = lifeSpan;
        this.decayRate = decayRate;
        this.weightX = weightX;
        this.weightY = weightY;
        this.imageFile = imageFile;
        setImage(colorParticle(size, col));
    }
    
    public void setLife(double life) { decay = life; }
    
    public void setDecayRate(double rate) { decayRate = rate; }
    
    public void setWeight(double weightX, double weightY) { this.weightX = weightX; this.weightY = weightY; }
    
    public void setVisual(int size, Color col) { setImage(colorParticle(size, col)); }
    
    public void setSize(int size) { setImage(colorParticle(size, col)); }
    
    public void setColor(Color col) { setImage(colorParticle(size, col)); }
    
    //public void etherWorldActorAct()
    public void actExtended()
    {
        super.actExtended();
        drift();
        fade();
    }    
    
    public void drift()
    {
        coordX = coordX + weightX;
        coordY = coordY + weightY;
    }
    
    public void fade()
    {
        decay = decay-decayRate;
        getImage().setTransparency((int)Math.max(0,Math.min(decay, 255)));
        if(decay < 0)
        {
            remove = true;
        }
    }
    
    public GreenfootImage colorParticle(int size, Color col)
    {
        GreenfootImage gi = new GreenfootImage(imageFile);
        gi.scale(size, size);
        for(int xp = 0; xp < gi.getWidth(); xp++)
        {
            for(int yp = 0; yp < gi.getHeight(); yp++)
            {
                Color c = gi.getColorAt(xp, yp);
                gi.setColorAt(xp, yp, new Color(col.getRed(), col.getGreen(), col.getBlue(), c.getAlpha()));
            }
        }
        return gi;
    }
    
    public void addedToEtherworld()
    {
        
    }
}

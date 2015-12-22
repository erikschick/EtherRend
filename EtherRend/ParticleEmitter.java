import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
/**
 * Write a description of class ParticleEmitter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ParticleEmitter extends EtherWorldActor
{
    int count = 0;
    
    boolean active = true;
    
    int frequency;
    double weightXmin;
    double weightYmin;
    double weightXmax;
    double weightYmax;
    double weightMultiplier = 1;
    int sizeMin;
    int sizeMax;
    Color col;
    double lifeSpan;
    double decayRate;
    List<String> fileNames = new ArrayList();
    
    public ParticleEmitter()
    {
        setImage(new GreenfootImage(1, 1));
    }
    
    public ParticleEmitter(int frequency, int sizeMin, int sizeMax, Color col, double lifeSpan, double decayRate, double weightXmin, double weightXmax, double weightYmin, double weightYmax)
    {
        setImage(new GreenfootImage(1, 1));
        this.frequency = frequency;
        this.weightXmin = weightXmin;
        this.weightYmin = weightYmin;
        this.weightXmax = weightXmax;
        this.weightYmax = weightYmax;
        this.sizeMin = sizeMin;
        this.sizeMax = sizeMax;
        this.col = col;
        this.lifeSpan = lifeSpan;
        this.decayRate = decayRate;
        this.fileNames = fileNames;
        List<String> stringaslist = new ArrayList();
        stringaslist.add("PARTICLE_gradientStar.png");
        setFileNames(stringaslist);
    }
    
    public ParticleEmitter(int frequency, int sizeMin, int sizeMax, Color col, double lifeSpan, double decayRate, double weightXmin, double weightXmax, double weightYmin, double weightYmax, String fileName)
    {
        setImage(new GreenfootImage(1, 1));
        this.frequency = frequency;
        this.weightXmin = weightXmin;
        this.weightYmin = weightYmin;
        this.weightXmax = weightXmax;
        this.weightYmax = weightYmax;
        this.sizeMin = sizeMin;
        this.sizeMax = sizeMax;
        this.col = col;
        this.lifeSpan = lifeSpan;
        this.decayRate = decayRate;
        this.fileNames = fileNames;
        List<String> stringaslist = new ArrayList();
        stringaslist.add(fileName);
        setFileNames(stringaslist);
    }
    
    public ParticleEmitter(int frequency, int sizeMin, int sizeMax, Color col, double lifeSpan, double decayRate, double weightXmin, double weightXmax, double weightYmin, double weightYmax, List<String> fileNames)
    {
        setImage(new GreenfootImage(1, 1));
        this.frequency = frequency;
        this.weightXmin = weightXmin;
        this.weightYmin = weightYmin;
        this.weightXmax = weightXmax;
        this.weightYmax = weightYmax;
        this.sizeMin = sizeMin;
        this.sizeMax = sizeMax;
        this.col = col;
        this.lifeSpan = lifeSpan;
        this.decayRate = decayRate;
        this.fileNames = fileNames;
    }
    
    public void setWeightMultiplier(double weightMultiplier) { this.weightMultiplier = weightMultiplier; }
    
    public void setFrequency(int frequency) { this.frequency = frequency; }
    
    public void setLife(double life) { this.lifeSpan = life; }
    
    public void setDecayRate(double decayRate) { this.decayRate = decayRate; }
    
    public void setWeight(double weightXmin, double weightXmax, double weightYmin, double weightYmax) { this.weightXmin = weightXmin; this.weightXmax = weightXmax; this.weightYmin = weightYmin; this.weightYmax = weightYmax; }
    
    public void setSize(int sizeMin, int sizeMax) { this.sizeMin = sizeMin; this.sizeMax = sizeMax; }
    
    public void setColor(Color col) { this.col = col; }
    
    //public void etherWorldActorAct()
    public void actExtended()
    {
        super.actExtended();
        if(active)
        {
            count--;
            if(count < 0)
            {
                PersistentStorage.etherworld.addObject(new Particle(
                    (int)eMath.randBetween(sizeMin, sizeMax), col, lifeSpan, decayRate, 
                    eMath.randBetween(weightXmin, weightXmax) * weightMultiplier, eMath.randBetween(weightYmin, weightYmax) * weightMultiplier, getRandomFileName()),
                    (int)coordX, (int)coordY);
                count = frequency;
            }
        }
    }
    
    public String getRandomFileName()
    {
        if(fileNames.size() == 0) return "PARTICLE_gradientStar.png";
        else return fileNames.get((int)eMath.randNum(fileNames.size() - 1));
    }
    
    public void setFileNames(List<String> fileNames)
    {
        this.fileNames = fileNames;
    }
    
    public void burst(int burstCount)
    {
        for(int i = 0; i < burstCount; i++)
        {
            PersistentStorage.etherworld.addObject(new Particle(
                (int)eMath.randBetween(sizeMin, sizeMax), col, lifeSpan, decayRate, 
                eMath.randBetween(weightXmin, weightXmax) * weightMultiplier, eMath.randBetween(weightYmin, weightYmax) * weightMultiplier, getRandomFileName()),
                (int)coordX, (int)coordY);
        }
    }
    
    public void burstOutward(int burstSections)
    {
        burstOutward(burstSections, (weightXmin + weightYmin) / 2 * weightMultiplier, (weightXmax + weightYmax) / 2 * weightMultiplier);
    }
    
    public void burstOutward(int burstSections, double velocityMin, double velocityMax)
    {
        burstOutward(burstSections, velocityMin, velocityMax, decayRate, lifeSpan);
    }
    
    public void burstOutward(int burstSections, double velocityMin, double velocityMax, double decayRate, double lifeSpan)
    {
        double angleSection = 360/(double)burstSections;
        for(int i = 0; i < burstSections; i++)
        {
            PersistentStorage.etherworld.addObject(new Particle(
                (int)eMath.randBetween(sizeMin, sizeMax), col, lifeSpan, decayRate, 
                Math.cos(angleSection * i) * eMath.randBetween(velocityMin, velocityMax) * weightMultiplier,
                Math.sin(angleSection * i) * eMath.randBetween(velocityMin, velocityMax) * weightMultiplier, getRandomFileName()),
                (int)coordX, (int)coordY);
        }
    }
    
    
    public void activate() { if(!active) active = true; }
    public void deactivate() { if(active) active = false; }
    
    public void addedToEtherworld()
    {
        
    }
}

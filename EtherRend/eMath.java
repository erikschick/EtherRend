import java.util.Random;
public class eMath  
{
    public static double randNum(double maxReturnValue)
    {
        //return (randgenerator.nextDouble() * maxReturnValue);
        return Math.random() * maxReturnValue;
    }
    
    public static double randNumMean(double mean, double variance)
    {
        return mean + (new Random().nextGaussian() * variance);
    }
    
    public static double randBetween(double min, double max)
    {
        return min + (randNum(max-min));
    }
    
    /**
     * Returns the quadrant of a given angle (1, 2, 3, 4).
     * 
     * Returns 0 if out of bounds.
     */
    public static int getQuadrant(double angle)
    {
        if(angle >= 0 && angle < 90) return 1;
        if(angle >= 90 && angle < 180) return 2;
        if(angle >= 180 && angle < 270) return 3;
        if(angle >= 270 && angle < 360) return 4;
        return 0;
    }
}

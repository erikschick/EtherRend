public class vector  
{
    double momentum;
    double angle; //THIS IS A DEGREE VALUE
    double xMomentum;
    double yMomentum;
    public vector(double momentum, double angle)
    {
        this.momentum = momentum;
        checkNegativeMomentum();
        this.angle = angle;
        checkAngle();
        xMomentum = momentum * Math.cos(Math.toRadians(angle));
        yMomentum = momentum * Math.sin(Math.toRadians(angle));
    }
    
    public void setMomentum(double m) //angle is currently not correct
    {
        momentum = m;
        checkNegativeMomentum();
        //System.out.println("momentum set to " + m);
        xMomentum = Math.copySign(momentum * Math.cos(Math.toRadians(angle)), xMomentum);
        yMomentum = Math.copySign(momentum * Math.sin(Math.toRadians(angle)), yMomentum);
        //setXmomentum(momentum * Math.cos(Math.toRadians(angle)));
        //setYmomentum(momentum * Math.sin(Math.toRadians(angle)));
    }
    
    public void setXmomentum(double m)
    {
        xMomentum = m;
        momentum = Math.sqrt(Math.pow(xMomentum, 2) + Math.pow(yMomentum, 2));
        checkNegativeMomentum();
        //System.out.println("r = " + momentum);
        //System.out.println("x = " + xMomentum);
        //System.out.println("angle = " + Math.toDegrees(Math.acos(xMomentum / momentum)));
        if(momentum != 0) angle = Math.toDegrees(Math.acos(xMomentum / momentum));
        //checkAngle();
    }
    
    public void setYmomentum(double m)
    {
        yMomentum = m;
        momentum = Math.sqrt(Math.pow(xMomentum, 2) + Math.pow(yMomentum, 2));
        checkNegativeMomentum();
        if(momentum != 0) angle = Math.toDegrees(Math.asin(yMomentum / momentum));
        //checkAngle();
    }
    
    /**
     * sets the angle to the given angle in DEGREES. Not in radians.
     */
    public void setAngle(double a)
    {
        angle = a;
        checkAngle();
        xMomentum = momentum * Math.cos(Math.toRadians(angle));
        yMomentum = momentum * Math.sin(Math.toRadians(angle));
    }
    
    /**
     * returns the angle in DEGREES. Not radians.
     */
    public double getAngle() {return angle;}
    public double getMomentum() {return momentum;}
    public double getXmomentum() {return xMomentum;}
    public double getYmomentum() {return yMomentum;}
    
    private void checkAngle()
    {
        if(angle < 0) angle = angle + 360;
    }
    
    private void checkNegativeMomentum()
    {
        if(momentum < 0)
        {
            if(angle >= 180) angle = angle - 180;
            else angle = angle + 180;
            momentum = Math.abs(momentum);
        }
    }
}

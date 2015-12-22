import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
/**
 * Write a description of class NOTFCN_tooltip here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NOTFCN_tooltip extends notification
{
    int decayTime = 100;
    double riseRate = 2;
    boolean risen = false;
    public NOTFCN_tooltip(int decayTime, String textToDisplay)
    {
        this(decayTime, textToDisplay, 0);
    }
    public NOTFCN_tooltip(int decayTime, String textToDisplay, int fontSize)
    {
        this.decayTime = decayTime;
        Graphics img = getImage().getAwtImage().createGraphics();
        img.setColor(Color.BLACK);
        int stringOffsetY = 5;
        if(fontSize == 0)
        {
            for(int i = 0; i < 100; i++)
            {
                if(40 - i > 0)
                {
                    img.setFont(new Font("Comic Sans MS", Font.PLAIN, 40 - i));
                    FontMetrics fm = img.getFontMetrics();
                    if(fm.stringWidth(textToDisplay) <= 190 && fm.getHeight() <= 40)
                    {
                        fontSize = 40 - i;
                        stringOffsetY = fm.getHeight();
                        img.drawString(textToDisplay, 10, (getImage().getHeight()/2) + (stringOffsetY / 2));
                        break;
                    }
                }
            }
            img.setFont(new Font("Comic Sans MS", Font.PLAIN, fontSize));
        }
        else
        {
            img.setFont(new Font("Comic Sans MS", Font.PLAIN, fontSize));
            FontMetrics fm = img.getFontMetrics();
            int resizeX = fm.stringWidth(textToDisplay) + 20;
            int resizeY = fm.getHeight() + 10;
            GreenfootImage newimg = getImage();
            newimg.scale(resizeX, resizeY);
            setImage(newimg);
            //img = getImage().getAwtImage().createGraphics();
            getImage().setFont(new Font("Comic Sans MS", Font.PLAIN, fontSize));
            getImage().drawString(textToDisplay, 10, fm.getHeight());
        }
    }
    
    public void NOTFCNinWorld()
    {
        setRealLocation(800 - (getImage().getWidth()/2), 600 + (getImage().getHeight()/2));
    }
    
    public void NOTFCNact()
    {
        if(!risen)
        {
            if((abs((600 - (getImage().getHeight()/2)) - realYloc) < riseRate))
            {
                setRealLocation(getRealX(), 600 - (getImage().getHeight()/2));
                risen = true;
            }
            else if(realYloc > 600 - getImage().getHeight())
            {
                setRealLocation(getRealX(), getRealY() - riseRate);
            }
        }
        if(risen)
        {
            decayTime--;
        }
        if(decayTime <= 0)
        {
            setRealLocation(getRealX(), getRealY() + riseRate);
            if(getRealY() > 600 + (getImage().getHeight()/2))
            {
                remove = true;
            }
        }
    }
}

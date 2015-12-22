import greenfoot.*;
import java.awt.*;
public class ImageWorker  
{
    public static GreenfootImage applyAlphaMap(GreenfootImage image, GreenfootImage alphaSource)
    {
        alphaSource.scale(image.getWidth(), image.getHeight());
        for(int x = 0; x < image.getWidth(); x++)
        {
            for(int y = 0; y < image.getWidth(); y++)
            {
                Color c = image.getColorAt(x, y);
                int pixelAlpha = alphaSource.getColorAt(x, y).getAlpha();
                image.setColorAt(x, y, new Color(c.getRed(), c.getGreen(), c.getBlue(), pixelAlpha));
            }
        }
        return image;
    }
}

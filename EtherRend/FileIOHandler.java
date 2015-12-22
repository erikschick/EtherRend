import greenfoot.*;
import java.util.List;
import java.util.ArrayList;
import java.io.*;
public class FileIOHandler  
{
    public static List readText(String reqfile)// throws IOException
    {
        FileInputStream fis = null;
        BufferedReader r = null;
        List lines = new ArrayList();
        try
        {
            if(reqfile != null)
            {
                fis = new FileInputStream(reqfile);
                r = new BufferedReader(new InputStreamReader(fis));
            
                String linetmp;
                while((linetmp = r.readLine()) != null)
                {
                    lines.add(linetmp);
                }
            }
        }
        catch(IOException e)
        {
            System.err.println("Caught IOException: " + e.getMessage());
        }
        return lines;
    }  
    
    public static String readText(String reqfile, int row)// throws IOException
    {  
        if(row < readText(reqfile).size())
        {
            return (String)readText(reqfile).get(row-1);
        }
        else
        return null;
    }
}

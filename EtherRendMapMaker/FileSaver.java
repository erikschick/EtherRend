import java.io.*;
import javax.swing.*;
import java.awt.Frame;
public class FileSaver 
{
    public static void newFile(String[] linesToWrite)
    {
        JFileChooser fc = new JFileChooser();
        File directory = new File(".");
        File currentPath = null;
        try { currentPath = new File(directory.getCanonicalPath()); }
        catch(Exception e) {}
        if(currentPath != null) fc.setCurrentDirectory(currentPath);
        Frame fcframe = new Frame();
        fc.setFileFilter(new javax.swing.filechooser.FileFilter()
            {   public boolean accept(File f)
                {
                    if(f.isDirectory()) return true;
                    String[] extension = {".ethrmap"};
                    for(int i = 0; i < extension.length; i++)
                    {
                        if(f.getName().toLowerCase().endsWith(extension[i])) return true;
                    }
                    return false;
                }
                public String getDescription()
                {
                    return "ethrmap files";
                }
            });
        int returnVal = fc.showSaveDialog(fcframe);
        File selFile = null;
        if(returnVal == JFileChooser.APPROVE_OPTION)
        {
            selFile = fc.getSelectedFile();
            PersistentStorage.currentFile = selFile.toString();
        }
        if(selFile != null)
        {
            saveFile(selFile.toString(), linesToWrite);
        }
    }
    
    public static boolean saveFile(String filename, String[] linesToWrite)
    {  
        try{
            if(!filename.endsWith(".ethrmap"))
            {
                filename = filename + ".ethrmap";
            }
            File workingFile = new File(filename);
            if(!workingFile.exists())
            {
                int save = JOptionPane.showConfirmDialog(null, "File doesn't exist, do you make a new file?", "Make new file?", JOptionPane.YES_NO_OPTION);
                if(save == JOptionPane.YES_OPTION)
                {
                    FileWriter fstream = new FileWriter(workingFile, false);
                    BufferedWriter writer = new BufferedWriter(fstream);
                    for(int i = 0; i < linesToWrite.length; i++)
                    {
                        if(linesToWrite[i] != null)
                        {
                            writer.write(linesToWrite[i]);
                            writer.newLine();
                        }
                    }
                    writer.close();
                }
            }
            else
            {
                int overwrite = JOptionPane.showConfirmDialog(null, "File exists, do you wish to overwrite?", "Overwrite?", JOptionPane.YES_NO_OPTION);
                if(overwrite == JOptionPane.YES_OPTION)
                {
                    FileWriter fstream = new FileWriter(workingFile, false);
                    BufferedWriter writer = new BufferedWriter(fstream);
                    for(int i = 0; i < linesToWrite.length; i++)
                    {
                        if(linesToWrite[i] != null)
                        {
                            writer.write(linesToWrite[i]);
                            writer.newLine();
                        }
                    }
                    writer.close();
                }
            }
        }
        catch (Exception e)
        {
            System.err.println("Error: " + e.getMessage());
            return false;
        }
        return true;
    }
    
    private static void checkDirectory(String directory)
    {
        try{
            new File(directory).mkdir();
        }
        catch (Exception e)
        {
            System.err.println("Error: " + e.getMessage());
        }
    }
}

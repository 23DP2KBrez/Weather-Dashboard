package lv.rvt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileManager 
{
    public static String openTextFile(String path) 
    {
        File file = new File(path);
        StringBuilder text = new StringBuilder();  // Use StringBuilder for efficiency
        
        try (BufferedReader br = new BufferedReader(new FileReader(file)))  // try-with-resources
        {
            String temp;
            while ((temp = br.readLine()) != null)
            {
                text.append(temp).append(System.lineSeparator());  // Preserves line breaks
            }
        } 
        catch (FileNotFoundException e) 
        {
            System.out.println("File not found: " + e.getMessage());
            e.printStackTrace();
        }
        catch (IOException e) 
        {
            System.out.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        }
        
        return text.toString();  // Return the accumulated text
    }
}
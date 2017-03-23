/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author CHiBEX
 */
public class HelperUtil {
    public static int parseInt(String str)
    {
        int num = 0;
        try{
            num = Integer.parseInt(str.trim());
        }catch(NumberFormatException nfe){}
        
        return num;
    }
    
    public static double parseBouble(String str)
    {
        double num = 0;
        try{
            num = Double.parseDouble(str.trim());
        }catch(NumberFormatException nfe){}
        
        return num;
    }
    
    public static boolean isImageExt(String ext)
    {
        String []exts = {"png", "gif", "jpeg", "jpg"};
        
        for(String ex : exts)
        {
            if(ex.equalsIgnoreCase(ext))
                return true;
        }
        
        return false;
    }
}

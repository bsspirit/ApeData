package org.ape.data.core.util;

import java.io.BufferedReader;
import java.io.FileReader;

public class IoTest  
{  
    public static void main(String[] args)  
    {  
        String s;  
        s=calculate();  
        System.out.println(s);  
    }  
  
    static String calculate()  
    {  
        StringBuffer sb=new StringBuffer("");  
        try{  
            FileReader reader = new FileReader("D:\\111.txt");  
            BufferedReader br = new BufferedReader(reader);  
            String s = null;  
            while((s = br.readLine()) != null) {  
                sb.append(s+'\n');  
            }  
            br.close();  
            reader.close();  
       }catch(Exception e){  
            e.printStackTrace();  
       }  
        return sb.toString();  
    }  
}  
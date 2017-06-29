package dms.manger.util;
import java.util.*;
import java.io.*;
 
public class Config
{
   Properties configFile;
   InputStream input = null;
   
   public Config(String fp)
   {
	configFile = new Properties();
	String filePath = fp;
	
	try {
		input = new FileInputStream(fp);
		configFile.load(input);
	}catch(Exception eta){
	    eta.printStackTrace();
	}
   }
 
   public String getProperty(String key)
   {
	String value = this.configFile.getProperty(key);
	return value;
   }
}
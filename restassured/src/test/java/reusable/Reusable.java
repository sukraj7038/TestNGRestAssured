package reusable;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class Reusable {

	public static String projectpath = System.getProperty("user.dir");
	
	
	public static String readpropertyfile(String key) {
		String value = null;
		
		
		
		Properties prop = new Properties();
		try {
		    //load a properties file from class path, inside static method - its used to third party applications
		    //prop.load(App.class.getClassLoader().getResourceAsStream("config.properties"));

		    prop.load(new FileInputStream(projectpath + "\\TestData.properties"));
		
		    value = prop.getProperty(key);
		    
		    
		} 
		catch (IOException ex) {
		    ex.printStackTrace();
		}
		
		return value;
		
	}
	
}

package pratice.genericUtilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileUtility {
	private Properties pLib;
	
	public void initProperty() {
		try {
			FileInputStream fis = new FileInputStream("./readDatatestNGXml.xml");
			pLib = new Properties();
			try {
				pLib.load(fis);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * This method fetch the value from properties file
	 * @param key
	 * @return String
	 */
	public String getDataPropertyFile(String key) {
		return pLib.getProperty(key);
	}
	
	
	

}

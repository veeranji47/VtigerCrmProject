package com.genericUtilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileUtility {
	FileInputStream fis;
	Properties propertyUtil;

	/**
	 * This method is used to get the value from the properties file
	 * @param propertiesPath
	 * 
	 */
	public void propertiesInit(String propertiesPath) {

		try {
			fis = new FileInputStream(propertiesPath);
			propertyUtil = new Properties();
			propertyUtil.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}
	public String getDataFromPropertiesFile(String key) {
		return propertyUtil.getProperty(key);
		
	}

}

package com.genericUtilities;

public interface IConstantPath {
	
	String jdbc_url = "jdbc:mysql://localhost/3306/advsel";
	String excelPath = System.getProperty("user.dir")+"/src/test/resources/testScriptData.xlsx";
	String propertiesPath = System.getProperty("user.dir")+"/src/test/resources/commonData.properties";
	

}

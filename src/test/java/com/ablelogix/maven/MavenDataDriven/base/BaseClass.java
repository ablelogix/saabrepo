package com.ablelogix.maven.MavenDataDriven.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.ablelogix.maven.MavenDataDriven.utility.FilePath;
import com.ablelogix.maven.MavenDataDriven.utility.ReadDB;


public class BaseClass implements FilePath {
	
	public static Properties CONFIG = null;
	public static Properties OR = null;

	public ReadDB testsuiteDB = null;
	public ReadDB testcaseDB = null;
	
	public void initDB(){
		testsuiteDB = new ReadDB("ABLELOGIX_SuitesList");
		testcaseDB = new ReadDB("ABLELOGIX_TestcasesList");
	}
	
	
	public BaseClass(){
		CONFIG = new Properties();
		
		//fileinput stream
		File fileConfig = new File(FilePath.configFilePath);
		FileInputStream finConfig = null;
		try {
			finConfig = new FileInputStream(fileConfig);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			CONFIG.load(finConfig);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//or.properties
		OR = new Properties();
		
		//fileinput stream
		File fileOR = new File(FilePath.ORfilePath);
		FileInputStream finOR = null;
		try {
			finOR = new FileInputStream(fileOR);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			OR.load(finOR);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}

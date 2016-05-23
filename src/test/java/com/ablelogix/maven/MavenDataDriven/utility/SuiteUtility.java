package com.ablelogix.maven.MavenDataDriven.utility;

import java.util.ArrayList;

public class SuiteUtility {
	
	public static boolean check2runUtility(ReadDB dbObj,String tableName, String toRun,String testSuite){
		if(dbObj.retrieveToRunFlag(tableName, toRun, testSuite)){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean writeResult(ReadDB dbObj,String tableName, String ColName,String rowName,String Result){
		return dbObj.writeResult(tableName, ColName, rowName, Result);
	}
	
	//update the testrecord table 
	public static void WriteResultTestRowUtility(ReadDB dbObject, String tableName, String ColName, int rowNum, String Result){			
		dbObject.writeTestDataResult(tableName, ColName, rowNum, Result);		 	
	}
		
	
	public static boolean checkToRunTestCaseUtility(ReadDB dbObject, String tableName, String ToRun, String testcaseName){
		return dbObject.retrieveToRunTestCaseFlag(tableName,ToRun,testcaseName);
	}
	
	public static void WriteTestCaseResultUtility(ReadDB dbObject, String tableName, String ColName, String testcaseName, String Result){
		dbObject.writeTestcaseResult(tableName, ColName, testcaseName, Result);
	}
	
	public static ArrayList checkToRunUtilityOfData(ReadDB dbObject, String tableName, String ColName){
		return dbObject.retrieveToRunFlagTestData(tableName,ColName);		 	

	}

}

package com.ablelogix.maven.MavenDataDriven.testcases.addemp;

import org.testng.SkipException;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeSuite;

import com.ablelogix.maven.MavenDataDriven.base.BaseClass;
import com.ablelogix.maven.MavenDataDriven.utility.ReadDB;
import com.ablelogix.maven.MavenDataDriven.utility.SuiteUtility;


public class AddEmpSuite extends BaseClass{
	
	ReadDB dbObject = null;
	String tableName = null;
	
	String SuiteName = null;
	String ToRunColumnName = null;
	
  @BeforeSuite
  public void beforeSuite() {
	  System.out.println("FIRE @BeforeSuite ***********************");
	  SuiteName = "AddEmpSuite";
	  initDB();
	  tableName = "ablelogix_SuitesList";
	  ToRunColumnName = "suitetorun";
	  dbObject = testsuiteDB;
	  
	  if(!(SuiteUtility.check2runUtility(dbObject, tableName, ToRunColumnName, SuiteName))){
		System.out.println("inside skip test suite");
		SuiteUtility.writeResult(dbObject, tableName, "skipped_executed", SuiteName, "Skipped Suite");
		throw new SkipException(SuiteName+"'s SuiteToRun Flag Is 'N' Or Blank. So Skipping Execution Of "+SuiteName);  
	  }
	  
	  System.out.println("before write result *****");
	  SuiteUtility.writeResult(dbObject, tableName, "skipped_executed", SuiteName, "Execute Test Suite");
	  System.out.println("after write result *****");

	  
  }

}

package com.ablelogix.maven.MavenDataDriven.testcases.login;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.asserts.SoftAssert;

import com.ablelogix.maven.MavenDataDriven.utility.ReadDB;
import com.ablelogix.maven.MavenDataDriven.utility.SuiteUtility;
import com.ablelogix.maven.MavenDataDriven.webdriver.WebDriverInteraction;


public class LoginSuiteESSUser extends LoginSuite {
	
  WebDriverInteraction webObj = null;
  
//testcase related
	ReadDB dbObject_TestCase = null;
	String TestCaseTableName = null;	
	String ToRunColumnNameTestCase = null;
	String testcaseName = null; 
	String ColName ="PASS_FAIL_SKIP";
	
//test data related
	ReadDB dbObject_TestRow = null;
	ArrayList TestDataToRun=null;
	String tableRowName = "AbleLogix_LoginSuiteEssUser";
	String ToRunColumnNameTestData = "data2run";
	static int DataSet=-1;
	static boolean Testskip=false;
	
	static boolean Testfail=false;

	SoftAssert s_assert =null;
	static boolean TestCasePass=true;


	

  
  @BeforeTest
  public void initSetup(){
	  dbObject_TestCase = new ReadDB(TestCaseTableName);
	  ToRunColumnNameTestCase= "case2run";
	  TestCaseTableName = "ablelogix_TestcasesList";
	  testcaseName = "LoginSuiteESSUser";
	  
	  dbObject_TestRow = new ReadDB(tableRowName);

	  
	  if(!(SuiteUtility.checkToRunTestCaseUtility(dbObject_TestCase, TestCaseTableName, ToRunColumnNameTestCase, testcaseName))){
		  System.out.println("update the testcase column PASS_FAIL_SKIP = SKIP TESTCASE");
		  SuiteUtility.WriteTestCaseResultUtility(dbObject_TestCase, TestCaseTableName, ColName, testcaseName, "Skip Test Case");
		  throw new SkipException(testcaseName+"'s CaseToRun Flag Is 'N' Or Blank. So Skipping Execution Of "+testcaseName);
	  }
	  
	  SuiteUtility.WriteTestCaseResultUtility(dbObject_TestCase, TestCaseTableName, ColName, testcaseName, "Execute Test Case");
	  
	  TestDataToRun = SuiteUtility.checkToRunUtilityOfData(dbObject_TestCase, tableRowName, ToRunColumnNameTestData);
	  
	  System.out.println("array List is ...."+TestDataToRun);
  }//end of method
  
  
  
  
  @BeforeClass
  public void init(){
	  webObj = new WebDriverInteraction(); 
  }
  
  @Test(dataProvider = "dp")
  public void testLoginAdmin(Object serialno, Object username,Object password,Object expectedResult,Object data2run,Object pass_fail) {
	  System.out.println("serial no ....."+serialno);
	  System.out.println("username ......."+username);
	  System.out.println("password ........."+password);
	  System.out.println("expectedresult ....."+expectedResult);
	  System.out.println("data2run ........"+data2run);
	  System.out.println("pass_fail ....."+pass_fail);

	  DataSet++;
	  
	  if(!(TestDataToRun.get(DataSet).equals("Y"))){
		  System.out.println("skip the test data......");
		  Testskip=true;
		   throw new SkipException("DataToRun for row number "+DataSet+" Is No Or Blank. So Skipping Its Execution.");
	  }
	  
	  
	  webObj.doLogin(username.toString(), password.toString());
	  
	  String actualresult = webObj.checkEssUser();
	  
	  System.out.println("actual test result is ...$$#################......."+actualresult);
	  
	  if(!(actualresult.equalsIgnoreCase((String) expectedResult))){
		  Testfail = true;
			s_assert.assertEquals(actualresult, expectedResult, actualresult+" And "+expectedResult+" Not Match");
	  }
	  
	  if(Testfail==true){
			//At last, test data assertion failure will be reported In testNG reports and It will mark your test data, test case and test suite as fail.
			s_assert.assertAll();		
	  }
	  
	  
  }//end of method
  
  @BeforeMethod
  public void beforeMethod() {
	webObj.launch_browser();  
  }

  @AfterMethod
  public void afterMethod() {
	  
	  if(Testskip==true){
			int rowNumber = DataSet+1;
			System.out.println("Data set value ....(SKIP)........."+(rowNumber));
			SuiteUtility.WriteResultTestRowUtility(dbObject_TestRow, tableRowName, "pass_fail_skip", rowNumber, "Skipped TestData");
	  }else
	  if(Testfail==true){
			int rowNumber = DataSet+1;
			System.out.println("Data set value ....(FAIL)........."+(rowNumber));
			//To make object reference null after reporting In report.
			s_assert = null;
			//Set TestCasePass = false to report test case as fail In excel sheet.
			TestCasePass=false;	
			SuiteUtility.WriteResultTestRowUtility(dbObject_TestRow, tableRowName, "pass_fail_skip", rowNumber, "Failed TestData");
	 }else{
	    	int rowNumber = DataSet+1;
			System.out.println("Data set value ....(PASS)........."+(rowNumber));
			SuiteUtility.WriteResultTestRowUtility(dbObject_TestRow, tableRowName, "pass_fail_skip", rowNumber, "Passed TestData");
	 }
	  	  //last make both flags as false for next data set.
		Testskip=false;
		Testfail=false;
	  webObj.closebrowser();
  }


  @AfterTest
   public void closeBrowser(){	
		if(TestCasePass){
			//Add_Log.info(TestCaseName+" : Reporting test case as PASS In excel.");
			SuiteUtility.WriteTestCaseResultUtility(dbObject_TestCase, TestCaseTableName, "pass_fail_skip", testcaseName, "PASS TESTCASE");
		}
		else{
			//Add_Log.info(TestCaseName+" : Reporting test case as FAIL In excel.");
			SuiteUtility.WriteTestCaseResultUtility(dbObject_TestCase, TestCaseTableName, "pass_fail_skip", testcaseName, "FAIL TESTCASE");
			
		}		
	}
  
  
  @DataProvider
  public Object[][] dp() {
	  String tableName = "AbleLogix_LoginSuiteEssUser";
	  ReadDB dbObj = new ReadDB(tableName);
	  int colCount = dbObj.getColCount();
	  int rowCount = dbObj.getRowCount();
	  ArrayList <Object[]> result = dbObj.readCompleteData(tableName, colCount);
	  Object [][] data = dbObj.convert2Array(result, rowCount, colCount);
	  return data;
  }
  
  
}

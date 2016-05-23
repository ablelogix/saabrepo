package com.ablelogix.maven.MavenDataDriven.testcases.addemp;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import com.ablelogix.maven.MavenDataDriven.utility.ReadDB;
import com.ablelogix.maven.MavenDataDriven.utility.Sync;
import com.ablelogix.maven.MavenDataDriven.webdriver.WebDriverInteraction;


public class TestFnameEmpty extends AddEmpSuite{
 WebDriverInteraction webObj = null; 
 
  @Test(dataProvider = "dp")
  public void fnameEmpty(Object serialno,String username,String password,String fname,String lname,String location,String data2run,String pass_fail_skip) {
	  webObj.doLogin(username, password);
	  Sync.wait4sometime();
	  
	  Assert.assertTrue(webObj.isAdminDisplayed());
	  Assert.assertTrue(webObj.gotoAddEmployeesLink());
	  webObj.isFnameEmpty(fname, lname, location);
	  Sync.wait4sometime();
	  Assert.assertTrue(webObj.isRequiredDisplayed());
	  
	  
	/*  if(!(){
	  }else{
		  System.out.println("logged in successfully");
		  );
		  
	  }*/
	  
  }
  
  @BeforeMethod
  public void beforeMethod() {
	  webObj.launch_browser();
  }

  @AfterMethod
  public void afterMethod() {
	  webObj.closebrowser();
	  
  }


  @DataProvider
  public Object[][] dp() {
	  String tableName = "AbleLogix_AddEmp";
	  ReadDB dbObj = new ReadDB(tableName);
	  int colCount = dbObj.getColCount();
	  int rowCount = dbObj.getRowCount();
	  ArrayList <Object[]> result = dbObj.readCompleteData(tableName, colCount);
	  Object [][] data = dbObj.convert2Array(result, rowCount, colCount);
	  return data;
	  
  }
  @BeforeClass
  public void beforeClass() {
	  webObj= new WebDriverInteraction();
  }

  @AfterClass
  public void afterClass() {
	  webObj = null;
  }

  @BeforeTest
  public void beforeTest() {
  }

  @AfterTest
  public void afterTest() {
  }

}

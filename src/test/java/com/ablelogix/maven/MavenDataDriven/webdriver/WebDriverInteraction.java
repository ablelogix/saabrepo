package com.ablelogix.maven.MavenDataDriven.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.Select;

import com.ablelogix.maven.MavenDataDriven.base.BaseClass;
import com.ablelogix.maven.MavenDataDriven.utility.FilePath;





public class WebDriverInteraction extends BaseClass implements FilePath {
	
	WebDriver driver;
	
	public WebDriverInteraction(){
		super();
	}
	
	public boolean isAdminDisplayed(){
		return driver.findElement(By.xpath("//*[@id='menu_admin_viewAdminModule']/b")).isDisplayed();
	}

	public void launch_browser(){
		
		System.out.println("inside launch browser....."+CONFIG.getProperty("browsername"));
		if(CONFIG.getProperty("browsername").equals("firefox")){
			driver = new FirefoxDriver();
		}else
		if(CONFIG.getProperty("browsername").equals("chrome")){
			System.setProperty("webdriver.chrome.driver", FilePath.chromedriverpath);
			driver = new ChromeDriver();
		}else
		if(CONFIG.getProperty("browsername").equals("IE")){
			System.setProperty("webdriver.ie.driver", FilePath.IEdriverpath);
			driver = new InternetExplorerDriver();
		}else
		if(CONFIG.getProperty("browsername").equals("opera")){
			System.setProperty("webdriver.opera.driver", FilePath.operadriverpath);
			driver = new OperaDriver();
		}
		
		driver.get(CONFIG.getProperty("initURL"));
		driver.manage().window().maximize();

	}//end of method


public void doLogin(String username,String password){
	WebElement usernameElm = driver.findElement(By.xpath(OR.getProperty("usernameXPATH")));
	usernameElm.sendKeys(username);
	
	WebElement passwordElm = driver.findElement(By.xpath(OR.getProperty("passwordXPATH")));
	passwordElm.sendKeys(password);
	
	WebElement submitbtn = driver.findElement(By.xpath(OR.getProperty("submitXPATH")));
	submitbtn.click();
}

public boolean isTextPresent(String message){
	boolean flag;
	String source = driver.getPageSource();
	if(source.contains(message)){
		flag=true;
	}else{
		flag=false;
	}
	return flag;
}


public String checkAdmin(){
	boolean flag= false;
	String actualResult="";
	flag = isTextPresent(CONFIG.getProperty("welcomeadminText"));
	
	if(flag){
		actualResult = CONFIG.getProperty("welcomeadminText");
	}
	else{
		actualResult="";
	}
	return actualResult;
	
	
}

//check ess user
public String checkEssUser(){
	boolean flag= false;
	String actualResult="";
	flag = isTextPresent(CONFIG.getProperty("welcomeessuserText"));
	
	if(flag){
		actualResult = CONFIG.getProperty("welcomeessuserText");
	}
	else{
		actualResult="";
	}
	return actualResult;
	
	
}


public void closebrowser(){
	driver.close();
}

public boolean gotoAddEmployeesLink(){
	boolean flag = false;
	WebElement welcomeElm = driver.findElement(By.xpath("//*[@id='welcome']"));
	WebElement pimElm = driver.findElement(By.xpath("//*[@id='menu_pim_viewPimModule']/b"));
	WebElement addEmpElm = driver.findElement(By.xpath("//*[@id='menu_pim_addEmployee']"));
	if(welcomeElm.isDisplayed()){
		Actions actionPIM = new Actions(driver);
		actionPIM.moveToElement(pimElm).build().perform();
		
		//Sync.wait4sometime();
		System.out.println("admin element visible.......");
		
			Actions actionAddEmp = new Actions(driver);
			actionAddEmp.moveToElement(addEmpElm).build().perform();
			//Sync.wait4sometime();
			addEmpElm.click();
			//Sync.wait4sometime();
			boolean flagAddEmpText = isTextPresent(CONFIG.getProperty("addempText"));
			
			if(flagAddEmpText){
				flag = true;
			}
			else{
				flag = false;
			}
		
		
	}
	
	return flag;
	
}

public void isFnameEmpty(String fname,String lname,String location){
	driver.findElement(By.xpath(OR.getProperty("fnameXPATH"))).sendKeys(fname);
	driver.findElement(By.xpath(OR.getProperty("lnameXPATH"))).sendKeys(lname);
	WebElement locationElm = driver.findElement(By.xpath(OR.getProperty("locationXPATH")));
	Select locationList = new Select(locationElm);
	locationList.selectByVisibleText(location);
	driver.findElement(By.xpath(OR.getProperty("saveBtnXPATH"))).click();
}

public boolean isRequiredDisplayed(){
	boolean flag = false;
	flag = isTextPresent(CONFIG.getProperty("requiredText"));
	return flag;
}

}//end of class

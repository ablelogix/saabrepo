package com.ablelogix.maven.MavenDataDriven.utility;

public interface FilePath {
	public static final String configFilePath = System.getProperty("user.dir")+"/src/test/java/com/ablelogix/maven/MavenDataDriven/config/config.properties";
	public static final String ORfilePath= System.getProperty("user.dir") + "/src/test/java/com/ablelogix/maven/MavenDataDriven/config/OR.properties";
	public static final String chromedriverpath=System.getProperty("user.dir")+"/src/test/java/com/ablelogix/maven/MavenDataDriven/drivers/chromedriver.exe";
	public static final String operadriverpath=System.getProperty("user.dir")+"/src/test/java/com/ablelogix/maven/MavenDataDriven/drivers/operadriver.exe";
	public static final String IEdriverpath=System.getProperty("user.dir")+"/src/test/java/com/ablelogix/maven/MavenDataDriven/drivers/IEDriverServer.exe";
}

package VersionControlDemo;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.selenium.Eyes;


public class Demo {
	
	public Eyes eyes = new Eyes();
	public String AppName = "GitHub";
	public String TestName = "VersionConrolDemo";  
	RemoteWebDriver driver;
	
	@BeforeMethod
	public void setUpBeforMethod() throws MalformedURLException {
		System.out.println("--------------test-----------------");
//		System.err.println(System.getenv("APPLITOOLS_API_KEY"));
//		eyes.setApiKey(System.getenv("APPLITOOLS_API_KEY"));
		eyes.setApiKey("8dYllwT3bXG1KzF6jTrpSLmmouv6naOJsopRyA2NUSc110");
		final String sauceUser = System.getenv("SAUCE_USER");
		final String sauceKey = System.getenv("SAUCE_KEY");
		String url = "http://" + sauceUser + ":" + sauceKey + "@ondemand.saucelabs.com:80/wd/hub";
		
		
		DesiredCapabilities caps = DesiredCapabilities.chrome();
		caps.setCapability("platform", "Windows 10");
		caps.setCapability("version", "68.0");
		caps.setCapability("screenResolution", "1400x1050");

//		driver = new RemoteWebDriver(new URL(url), caps);
		
		driver = new ChromeDriver();
		
		String batchId  = System.getenv("APPLITOOLS_BATCH_ID");
//		System.out.println(System.getenv("APPLITOOLS_BATCH_ID"));
		String batchName = "VersionControlDemo";
		BatchInfo batchInfo = new BatchInfo(batchName); 
		
		batchInfo.setId(batchId);
		eyes.setBatch(batchInfo);
		eyes.addProperty("Commit Hash", System.getenv("APPLITOOLS_BATCH_ID"));
		eyes.addProperty("Build Number",System.getenv("BUILD_NUMBER_DEMO"));
	}
	
	
	@Test
	public void Test() {
		try {
//			System.out.println("in test method");
			driver.get("https://github.com/");
			eyes.open(driver, AppName, TestName, new RectangleSize(1000, 800));
			
			//js injection
			 JavascriptExecutor js = (JavascriptExecutor) driver;
			 js.executeScript("document.querySelector(\"body > div.application-main > div.py-6.py-sm-8.jumbotron-codelines > div > div > div.mx-auto.col-sm-8.col-md-5.hide-sm > div > form > button\").style.backgroundColor = '#125221'");

			eyes.checkWindow();
			eyes.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			driver.quit();
			eyes.abortIfNotClosed();
		}
	}

}

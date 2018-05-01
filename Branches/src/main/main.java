package main;

import java.awt.List;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.glassfish.jersey.client.RequestProcessingInitializationStage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.FileLogger;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.StdoutLogHandler;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.StitchMode;
import com.applitools.eyes.selenium.fluent.Target;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;   

public class main {
	private static Eyes eyes = new Eyes();
	private static String AppName = "GiyhubIntegration";
	private static String TestName = "Test_Github_Integration");  

	public static void main(String[] args) throws URISyntaxException, ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		eyes.setApiKey(System.getenv("APPLITOOLS_API_KEY"));
		
		final String sauceUser = System.getenv("SAUCE_USER");
		final String sauceKey = System.getenv("SAUCE_KEY");
		String url = "http://" + sauceUser + ":" + sauceKey + "@ondemand.saucelabs.com:80/wd/hub";

		DesiredCapabilities caps = DesiredCapabilities.chrome();
		caps.setCapability("platform", "Windows 10");
		caps.setCapability("version", "65.0");

		RemoteWebDriver driver = new RemoteWebDriver(new URL(url), caps);
		
		String batchId   = System.getenv("APPLITOOLS_BATCH_ID");
		String batchName = null;
		BatchInfo batchInfo = new BatchInfo(batchName); 
		batchInfo.setId(batchId);
		eyes.setBatch(batchInfo);
		MainBranch(driver);

	}

	private static void MainBranch(WebDriver driver) {
		WebDriver driver = new ChromeDriver();
		try {
			eyes.open(driver, AppName, TestName, new RectangleSize(900, 600));
			driver.get("https://applitools.com/helloworld/");
			eyes.checkWindow();
			eyes.setStitchMode(StitchMode.CSS);
			

			eyes.close(false);
			System.out.println("Main Branch");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			driver.quit();
			eyes.abortIfNotClosed();
		}

	}



}

//curl -c output.txt -d "AppName=TestBranches4&SourceBranch=B1&TargetBranch=default" -X POST https://eyes.applitools.com/api/baselines/copybranch?accesskey=97LpK5zFPRImwmKu4AUAJzfZ1w9tDlK7m2K2CQEFuUo0110 

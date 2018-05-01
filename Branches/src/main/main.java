package main;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.applitools.eyes.BatchInfo;

import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.StitchMode;

public class main {
	
	private static Eyes eyes = new Eyes();
	private static String AppName = "GiyhubIntegration";
	private static String TestName = "Test_Github_Integration";  

	public static void main(String[] args) throws URISyntaxException, ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		System.out.println("--------------test-----------------");
		eyes.setApiKey(System.getenv("APPLITOOLS_API_KEY"));
		final String sauceUser = System.getenv("SAUCE_USER");
		final String sauceKey = System.getenv("SAUCE_KEY");
		String url = "http://" + sauceUser + ":" + sauceKey + "@ondemand.saucelabs.com:80/wd/hub";

		DesiredCapabilities caps = DesiredCapabilities.chrome();
		caps.setCapability("platform", "Windows 10");
		caps.setCapability("version", "65.0");

		RemoteWebDriver driver = new RemoteWebDriver(new URL(url), caps);
		
		String batchId   = System.getenv("APPLITOOLS_BATCH_ID");
		String batchName = "BatchName";
		BatchInfo batchInfo = new BatchInfo(batchName); 
		batchInfo.setId(batchId);
		eyes.setBatch(batchInfo);
		MainBranch(driver);

	}

	private static void MainBranch(WebDriver driver) {
		
		try {
			eyes.open(driver, AppName, TestName, new RectangleSize(800, 600));
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

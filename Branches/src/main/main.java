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
	private static String AppName = "TestBranches7";
	private static String TestName = "Branches" + DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  

	public static void main(String[] args) throws URISyntaxException, ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		eyes.setApiKey("ioRRQOF5YBYU6NIwbe3tuDFCX8H109mmenarZo8arSlbA110");
		 
		BatchInfo batch = new BatchInfo("BranchsTest2");
		eyes.setBatch(batch);
		MainBranch();
		CheckBranch("mybranch", true);

	}

	private static void MainBranch() {
		WebDriver driver = new ChromeDriver();
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

	private static void CheckBranch(String BranchName, boolean click) {
		WebDriver driver = new ChromeDriver();
		
		try {
			//eyes.setParentBranchName("default");
			eyes.setBranchName(BranchName);
			eyes.open(driver, AppName, TestName, new RectangleSize(800, 600));

			driver.get("https://applitools.com/helloworld/");
			// Thread.sleep(500);
			if (click) {
				driver.findElement(By.tagName("button")).click();
			}
			eyes.checkWindow();

			eyes.close(false);

			System.out.println(BranchName);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			driver.quit();
			eyes.abortIfNotClosed();

		}

	}

	private static void MargeBranches(String Source, String Target) throws ClientProtocolException, IOException {
		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(
				"https://eyes.applitools.com/api/baselines/copybranch?accesskey=97LpK5zFPRImwmKu4AUAJzfZ1w9tDlK7m2K2CQEFuUo0110");

		// Request parameters and other properties.
		ArrayList<NameValuePair> params = new ArrayList<NameValuePair>(3);
		params.add(new BasicNameValuePair("AppName", AppName));
		params.add(new BasicNameValuePair("SourceBranch", Source));
		params.add(new BasicNameValuePair("TargetBranch", Target));
		httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

		// Execute and get the response.
		HttpResponse response = httpclient.execute(httppost);
		// HttpEntity entity = response.getEntity();

		System.out.println(response.getStatusLine());
	}

}

//curl -c output.txt -d "AppName=TestBranches4&SourceBranch=B1&TargetBranch=default" -X POST https://eyes.applitools.com/api/baselines/copybranch?accesskey=97LpK5zFPRImwmKu4AUAJzfZ1w9tDlK7m2K2CQEFuUo0110 


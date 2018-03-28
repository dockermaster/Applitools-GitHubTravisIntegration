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
		eyes.setBatch(batch);
		CheckBranch("mybranch", true);

	}
	
	
	private static void CheckBranch(String BranchName, boolean click) {
		WebDriver driver = new ChromeDriver();
		
		try {
			//eyes.setParentBranchName("default");
			eyes.setBranchName(BranchName);
			eyes.open(driver, AppName, TestName, new RectangleSize(800, 600));

			driver.get("https://applitools.com/helloworld/");
			
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

	
}




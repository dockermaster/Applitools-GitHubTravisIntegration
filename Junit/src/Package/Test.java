package Package;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.selenium.Eyes;


class Test {
	public Eyes eyes = new Eyes();
	public String AppName = "GiyhubIntegration";
	public String TestName = "Test_Github_Integration";  
	RemoteWebDriver driver;
	
    @BeforeEach
    public void initialize() throws MalformedURLException {
		System.out.println("--------------test-----------------");
		System.err.println(System.getenv("APPLITOOLS_API_KEY"));
		eyes.setApiKey(System.getenv("APPLITOOLS_API_KEY"));
		final String sauceUser = System.getenv("SAUCE_USER");
		final String sauceKey = System.getenv("SAUCE_KEY");
		String url = "http://" + sauceUser + ":" + sauceKey + "@ondemand.saucelabs.com:80/wd/hub";
		
		
		DesiredCapabilities caps = DesiredCapabilities.chrome();
		caps.setCapability("platform", "Windows 10");
		caps.setCapability("version", "65.0");

		driver = new RemoteWebDriver(new URL(url), caps);
		
		String batchId   = System.getenv("APPLITOOLS_BATCH_ID");
		System.out.println(System.getenv("APPLITOOLS_BATCH_ID"));
		String batchName = "BatchName";
		BatchInfo batchInfo = new BatchInfo(batchName); 
		batchInfo.setId(batchId);
		eyes.setBatch(batchInfo);
     }
    
	@org.junit.jupiter.api.Test
	void test() {
		try {
			System.out.println("in test method");
			eyes.open(driver, AppName, TestName, new RectangleSize(900, 600));
			driver.get("https://applitools.com/helloworld/");
			
			eyes.checkWindow();
			System.out.println("in test method 2");
			eyes.close();
//			System.out.println("Main Branch");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			driver.quit();
			eyes.abortIfNotClosed();
		}
	}

}
